package org.learn.bookstore.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.learn.bookstore.commons.system.StandardSystemException;
import org.learn.bookstore.commons.system.StandardSystemResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

/**
 * @author abhinavagarwal
 * <p>
 * Created On: 01-Oct-2023
 */

@Slf4j
@RestControllerAdvice
public class AdviceController {

    @ExceptionHandler(StandardSystemException.class)
    public ResponseEntity<StandardSystemResponse<Void>> StandardSystemExceptionHandler(
            StandardSystemException exception, WebRequest request) {
        log.error("Error occurred in API {}", request.getDescription(true));
        log.error("Error Messages: {}", exception.getMessages(), exception);
        return ResponseEntity.status(exception.getHttpStatus())
                .body(StandardSystemResponse.error(exception.getMessage(), exception.getErrorCode()));
    }
}
