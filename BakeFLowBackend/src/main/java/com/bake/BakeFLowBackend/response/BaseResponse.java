package com.bake.BakeFLowBackend.response;


import lombok.Data;

@Data
public class BaseResponse <T>{
    private String message;
    boolean success;
    private T data;

    public BaseResponse(boolean success, String message, T data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }
}
