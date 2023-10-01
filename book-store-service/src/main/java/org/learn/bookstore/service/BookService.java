package org.learn.bookstore.service;

import org.learn.bookstore.commons.dto.web.response.BookListWebResponse;

/**
 * @author abhinavagarwal
 * <p>
 * Created On: 01-Oct-2023
 */
public interface BookService {

    BookListWebResponse getAllBooks(int pageNo, int pageSize);
}
