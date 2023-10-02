package org.learn.bookstore.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.learn.bookstore.commons.dto.PageDTO;
import org.learn.bookstore.commons.dto.web.request.AddBookWebRequest;
import org.learn.bookstore.commons.dto.web.response.BookDetailsWebResponse;
import org.learn.bookstore.commons.dto.web.response.BookListWebResponse;
import org.learn.bookstore.commons.entity.Book;
import org.learn.bookstore.commons.entity.Genre;
import org.learn.bookstore.commons.system.StandardSystemException;
import org.learn.bookstore.dao.AuthorRepo;
import org.learn.bookstore.dao.BookRepo;
import org.learn.bookstore.dao.GenreRepo;
import org.learn.bookstore.service.BookService;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.learn.bookstore.commons.enums.SystemErrorCode.AUTHOR_NOT_FOUND;

/**
 * @author abhinavagarwal
 * <p>
 * Created On: 01-Oct-2023
 */

@Service
@Slf4j
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepo bookRepo;
    private final GenreRepo genreRepo;
    private final AuthorRepo authorRepo;

    @Override
    public BookListWebResponse getAll(int pageNo, int pageSize) {
        if (pageSize < 0)
            return null;
        Map<Long, Genre> genreById = genreRepo.findAll()
                .stream()
                .collect(Collectors.toMap(Genre::getId, Function.identity()));
        Page<Book> bookPage = bookRepo.findAll(PageRequest.of(pageNo, pageSize, Sort.by("name").ascending()));
        List<BookDetailsWebResponse> bookDetails = bookPage.get()
                .map(book -> {
                    BookDetailsWebResponse bookDetail = new BookDetailsWebResponse();
                    BeanUtils.copyProperties(book, bookDetail);
                    bookDetail.setGenre(genreById.get(book.getGenreId()).getName());
                    return bookDetail;
                })
                .collect(Collectors.toList());
        PageDTO pageDTO = new PageDTO()
                .setPageNo(pageNo)
                .setPageSize(pageSize)
                .setTotalPages(bookPage.getTotalPages())
                .setTotalElements(bookPage.getTotalElements());
        return new BookListWebResponse().setBooks(bookDetails).setPage(pageDTO);
    }

    @Override
    public void add(AddBookWebRequest webRequest) {
        if (!authorRepo.existsById(webRequest.getAuthorId()))
            throw new StandardSystemException("Invalid author info", AUTHOR_NOT_FOUND);
        Book book = new Book();
        BeanUtils.copyProperties(webRequest, book);
        String requestGenre = webRequest.getGenre();
        if (StringUtils.isNotBlank(requestGenre)) {
            Genre genre = genreRepo.findByNameIgnoreCase(requestGenre);
            if (Objects.isNull(genre)) {
                genre = new Genre();
                genre.setName(requestGenre);
                genre = genreRepo.save(genre);
            }
            book.setGenreId(genre.getId());
        }
        bookRepo.save(book);
    }
}
