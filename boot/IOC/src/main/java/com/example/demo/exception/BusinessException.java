package com.example.demo.exception;

public class BusinessException extends RuntimeException{
    private Integer code;
    public BusinessException(int code,String msg) {
        super(msg);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode() {
        this.code = code;
    }
}
