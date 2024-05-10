package com.pjt.core.common.util;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ApiResponse<T> {
    private int code;
    private String message;
    private T data;

    private ApiResponse(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    private ApiResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public static <T> ApiResponse<T> ok(T data) {
        return new ApiResponse<>(HttpStatus.OK.value(), "SUCCESS", data);
    }

    public static <T> ApiResponse<T> of(HttpStatus status, T data) {
        return new ApiResponse<>(status.value(), "FAIL", data);
    }

    public static <T> ApiResponse<T> dbError() {
        return new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "DB-ERROR");
    }

}
