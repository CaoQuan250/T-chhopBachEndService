package com.example.backEndService.exception;

public enum ERROR {
    INVALID_EMAIL(1000, "Invalid Email"),
    INVALID_AGE(1001,"You are underage"),
    USERNAME_ALREADY_EXIST(1002,"Username already taken"),
    EMAIL_ALREADY_EXIST(1003,"Email has already been registered"),
    INVALID_MOBILE(1004,"Invalid phone number"),
    ACCOUNT_INACTIVE(1005,"You haven't activate your account check your gmail to activate it"),
    NO_DATA(204,"NO DATA"),
    NO_DATA_FOUND(404,"No data found"),
    NOT_NULL(400,"NOT NULL");

    private int code;
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    ERROR(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
