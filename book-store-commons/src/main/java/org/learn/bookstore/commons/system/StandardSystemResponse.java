package org.learn.bookstore.commons.system;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.learn.bookstore.commons.enums.SystemErrorCode;

/**
 * @author abhinavagarwal
 * <p>
 * Created On: 01-Oct-2023
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StandardSystemResponse<T> {

    private boolean result;
    private T data;
    private String message;
    private SystemErrorCode errorCode;

    public StandardSystemResponse(boolean result, T data, String message, SystemErrorCode errorCode) {
        this.result = result;
        this.data = data;
        this.message = message;
        this.errorCode = errorCode;
    }

    public StandardSystemResponse(boolean result, T data) {
        this(result, data, null, null);
    }

    public static <T> StandardSystemResponse<T> success(T data) {
        return new StandardSystemResponse<>(true, data);
    }

    public static StandardSystemResponse<Void> error(String errorMessage) {
        return new StandardSystemResponse<>(false, null, errorMessage, null);
    }

    public static StandardSystemResponse<Void> error(String errorMessage, SystemErrorCode errorCode) {
        return new StandardSystemResponse<>(false, null, errorMessage, errorCode);
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public SystemErrorCode getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(SystemErrorCode errorCode) {
        this.errorCode = errorCode;
    }
}
