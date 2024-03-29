package com.bake.BakeFLowBackend.dto.response;


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

    public static <T> BaseResponse<T> success(String message, T data){
        return new SuccessResponse<>(message, data);
    }

    public static <T> BaseResponse<T> error(String message, T data){
        return new ErrorResponse<>(message, data);
    }
}
