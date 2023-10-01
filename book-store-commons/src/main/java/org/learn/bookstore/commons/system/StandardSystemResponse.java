package org.learn.bookstore.commons.system;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

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

    public StandardSystemResponse(boolean result, T data, String message) {
        this.result = result;
        this.data = data;
        this.message = message;
    }

    public StandardSystemResponse(boolean result, T data) {
        this(result, data, null);
    }

    public static <T> StandardSystemResponse<T> success(T data) {
        return new StandardSystemResponse<>(true, data);
    }

    public static StandardSystemResponse<Void> error(String errorMessage) {
        return new StandardSystemResponse<>(false, null, errorMessage);
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
}
