package com.bake.BakeFLowBackend.response;

public class SuccessResponse<T> extends BaseResponse<T>{
    public SuccessResponse(String message, T data) {
        super(true, message, data);
    }
}
