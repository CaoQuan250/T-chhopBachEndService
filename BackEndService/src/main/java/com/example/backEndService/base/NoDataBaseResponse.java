package com.example.backEndService.base;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NoDataBaseResponse {
    private Integer code;
    private String message;

    public NoDataBaseResponse(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public NoDataBaseResponse(){
        this.code = 200;
        this.message = "Success";
    }
}
