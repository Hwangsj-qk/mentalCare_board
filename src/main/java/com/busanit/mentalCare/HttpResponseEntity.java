package com.busanit.mentalCare;

import lombok.Getter;
import org.springframework.http.HttpStatus;


public class HttpResponseEntity {
    public static <T> ResponseResult<T> success(T response) {
        return new ResponseResult<>(true, response, null);
    }

    public static <T> ResponseResult<T> success() {
        return success(null);
    }

    public static ResponseResult<?> error(Throwable throwable, HttpStatus status) {
        return new ResponseResult<>(false, null, new ResponseError(throwable, status));
    }

    public static ResponseResult<?> error(String message, HttpStatus status) {
        return new ResponseResult<>(false, null, new ResponseError(message, status));
    }

    @Getter
    public static class ResponseError {
        private final String message;
        private final int status;

        ResponseError(Throwable throwable, HttpStatus status) {
            this(throwable.getMessage(), status);
        }

        ResponseError(String message, HttpStatus status) {
            this.message = message;
            this.status = status.value();
        }

    }

    @Getter
    public static class ResponseResult<T> {
        private final boolean success;
        private final T response;
        private final ResponseError error;

        private ResponseResult(boolean success, T response, ResponseError error) {
            this.success = success;
            this.response = response;
            this.error = error;
        }


    }
}