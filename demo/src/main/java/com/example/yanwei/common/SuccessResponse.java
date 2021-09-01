package com.example.yanwei.common;

public class SuccessResponse {

    public static <T> BaseResponse<T> success() {
        BaseResponse<T> response = new BaseResponse<>();
        response.setStatusCode(200);
        return response;
    }




}
