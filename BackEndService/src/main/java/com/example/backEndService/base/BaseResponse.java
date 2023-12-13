package com.example.backEndService.base;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class BaseResponse<T> implements Serializable {
    private Integer code;
    private String message;
    private T data;


    public BaseResponse() {
    }

    public BaseResponse(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public BaseResponse(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public BaseResponse(T data){
        this.code = 200;
        this.message = "Success";
        this.data=data;
    }

}
