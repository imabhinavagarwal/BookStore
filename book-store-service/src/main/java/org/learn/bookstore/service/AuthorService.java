package org.learn.bookstore.service;

import org.learn.bookstore.commons.dto.web.request.AddAuthorWebRequest;

/**
 * @author abhinavagarwal
 * <p>
 * Created On: 01-Oct-2023
 */
public interface AuthorService {

    void add(AddAuthorWebRequest webRequest);
}
