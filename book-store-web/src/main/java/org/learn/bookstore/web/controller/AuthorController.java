package org.learn.bookstore.web.controller;

import lombok.RequiredArgsConstructor;
import org.learn.bookstore.commons.dto.web.request.AddAuthorWebRequest;
import org.learn.bookstore.commons.system.StandardSystemResponse;
import org.learn.bookstore.service.AuthorService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static org.learn.bookstore.commons.constant.BookStoreConstants.API_V1_PREFIX;

/**
 * @author abhinavagarwal
 * <p>
 * Created On: 01-Oct-2023
 */

@RestController
@RequestMapping(API_V1_PREFIX + "/author")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    @PostMapping()
    public StandardSystemResponse<Void> getAllBooks(
            @RequestBody @Valid AddAuthorWebRequest webRequest) {
        authorService.add(webRequest);
        return StandardSystemResponse.success(null);
    }
}
