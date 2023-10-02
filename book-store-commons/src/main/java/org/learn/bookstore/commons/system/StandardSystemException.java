package org.learn.bookstore.commons.system;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.learn.bookstore.commons.enums.SystemErrorCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.apache.http.HttpStatus.SC_BAD_REQUEST;
import static org.apache.http.HttpStatus.SC_INTERNAL_SERVER_ERROR;

/**
 * @author abhinavagarwal
 * <p>
 * Created On: 01-Oct-2023
 */
public class StandardSystemException extends RuntimeException {

    private int httpStatus;
    private List<String> messages;
    private SystemErrorCode errorCode;

    public StandardSystemException(String message) {
        this(message, (Throwable) null);
    }

    public StandardSystemException(String message, int httpStatus) {
        this(message, httpStatus, null);
    }

    public StandardSystemException(String message, SystemErrorCode errorCode) {
        this(message, SC_BAD_REQUEST, errorCode);
    }

    public StandardSystemException(String message, Throwable cause) {
        this(message, cause, SC_INTERNAL_SERVER_ERROR, null);
    }

    public StandardSystemException(String message, int httpStatus, SystemErrorCode errorCode) {
        this(message, null, httpStatus, errorCode);
    }

    public StandardSystemException(String message, Throwable cause, int httpStatus, SystemErrorCode errorCode) {
        super(message, unwrapSystemException(cause));
        this.httpStatus = httpStatus;
        this.errorCode = errorCode;

        this.addMessage(message);
        if (cause instanceof StandardSystemException) {
            this.addMessages(((StandardSystemException) cause).getMessages());
        }
    }

    public int getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(int httpStatus) {
        this.httpStatus = httpStatus;
    }

    public SystemErrorCode getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(SystemErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }

    public StandardSystemException addMessage(String message) {
        if (StringUtils.isBlank(message)) {
            return this;
        }

        if (CollectionUtils.isEmpty(this.messages)) {
            this.messages = Arrays.asList(message);
        } else {
            this.messages.add(message);
        }
        return this;
    }

    public StandardSystemException addMessages(List<String> messages) {
        List<String> nonBlankMessages = messages.stream().filter(StringUtils::isNotBlank).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(nonBlankMessages)) {
            return this;
        }

        if (CollectionUtils.isEmpty(this.messages)) {
            this.messages = new ArrayList<>(messages);
        } else {
            this.messages.addAll(messages);
        }
        return this;
    }

    private static Throwable unwrapSystemException(Throwable cause) {
        if (cause instanceof StandardSystemException && cause.getCause() != null) {
            return cause.getCause();
        } else {
            return cause;
        }
    }
}
