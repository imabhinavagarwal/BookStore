package org.learn.bookstore.web.controller;

import lombok.RequiredArgsConstructor;
import org.learn.bookstore.commons.dto.web.response.BookListWebResponse;
import org.learn.bookstore.commons.system.StandardSystemResponse;
import org.learn.bookstore.service.BookService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.learn.bookstore.commons.constants.BookStoreConstants.API_V1_PREFIX;

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

    @GetMapping("/list")
    public StandardSystemResponse<BookListWebResponse> getAllBooks(
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "20") int pageSize) {
        return StandardSystemResponse.success(bookService.getAllBooks(pageNo, pageSize));
    }

}
