package com.example.backEndService.exception;

import com.example.backEndService.base.NoDataBaseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlerController {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<NoDataBaseResponse> handleException(Exception e) {
        e.printStackTrace();
        return new ResponseEntity<>(new NoDataBaseResponse(0, "Internal server Error"), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<NoDataBaseResponse> handleException(ApplicationException e) {
        e.printStackTrace();
        return new ResponseEntity<>(new NoDataBaseResponse(e.getCode(), e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
