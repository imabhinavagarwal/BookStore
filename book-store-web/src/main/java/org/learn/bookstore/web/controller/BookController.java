package org.learn.bookstore.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.learn.bookstore.commons.dto.web.request.AddBookWebRequest;
import org.learn.bookstore.commons.dto.web.response.BookListWebResponse;
import org.learn.bookstore.commons.system.StandardSystemResponse;
import org.learn.bookstore.service.BookService;
import org.springframework.web.bind.annotation.*;

import static org.learn.bookstore.commons.constant.BookStoreConstants.API_V1_PREFIX;

/**
 * @author abhinavagarwal
 * <p>
 * Created On: 01-Oct-2023
 */
@RestController
@RequestMapping(API_V1_PREFIX + "/book")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @PostMapping
    public StandardSystemResponse<Void> addBook(
            @RequestBody @Valid AddBookWebRequest webRequest) {
        bookService.add(webRequest);
        return StandardSystemResponse.success(null);
    }

    @GetMapping("/list")
    public StandardSystemResponse<BookListWebResponse> getAllBooks(
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "20") int pageSize,
            @RequestParam(required = false) String searchKey) {
        return StandardSystemResponse.success(bookService.getAll(pageNo, pageSize, searchKey));
    }

}
