package com.example.backEndService.base;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class BaseController {
    public <T> ResponseEntity<BaseResponse<T>> createResponse(BaseResponse<T> response,HttpStatus httpStatus){
        return new ResponseEntity<>(response, httpStatus);
    }
    public ResponseEntity<NoDataBaseResponse> createNoDataResponse(NoDataBaseResponse response,HttpStatus httpStatus){
        return new ResponseEntity<>(response, httpStatus);
    }
    public <T> ResponseEntity<BaseResponse<T>> createResponse(BaseResponse<T> response){
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    public ResponseEntity<NoDataBaseResponse> createNoDataResponse(NoDataBaseResponse response){
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
