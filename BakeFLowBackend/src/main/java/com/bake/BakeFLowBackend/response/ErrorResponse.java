package com.bake.BakeFLowBackend.response;

public class ErrorResponse<T> extends BaseResponse<T>{
    public ErrorResponse(String message, T data) {
        super(false, message, null);
    }
}
