package com.example.yanwei.common;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author yanwei
 */
public class BaseResponse<T> implements Response {

    @JsonProperty("statusCode")
    private Integer statusCode = null;

    @JsonProperty("data")
    private T data = null;

    @JsonProperty("message")
    private String message = null;

    public T getData() {
        return null;
    }


    public BaseResponse statusCode(Integer statusCode) {
        this.statusCode = statusCode;
        return this;
    }

    public BaseResponse data(T data) {
        this.data = data;
        return this;
    }

    public BaseResponse message(String message) {
        this.message = message;
        return this;
    }





    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
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
