package org.learn.bookstore.commons.enums;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author abhinavagarwal
 * <p>
 * Created On: 01-Oct-2023
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public enum SystemErrorCode {

    AUTHOR_NOT_FOUND(10001);

    private final int errorCode;

    SystemErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }
}
