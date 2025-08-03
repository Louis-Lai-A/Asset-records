package com.homework.Asset.records.Exception;

// 響應類
public class ErrorResponse {
    private final String code;
    private final String message;

    public ErrorResponse(String code, String message) {
        this.code = code;
        this.message = message;
    }

    // Getter 方法
    public String getCode() { return code; }
    public String getMessage() { return message; }
}
