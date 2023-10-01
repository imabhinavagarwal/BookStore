package org.learn.bookstore.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.learn.bookstore.commons.dto.web.request.AddAuthorWebRequest;
import org.learn.bookstore.commons.entity.Author;
import org.learn.bookstore.dao.AuthorRepo;
import org.learn.bookstore.service.AuthorService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * @author abhinavagarwal
 * <p>
 * Created On: 01-Oct-2023
 */

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepo authorRepo;

    @Override
    public void add(AddAuthorWebRequest webRequest) {
        Author author = new Author();
        BeanUtils.copyProperties(webRequest, author);
        authorRepo.save(author);
    }
}
