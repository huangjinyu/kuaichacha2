package com.kcc.core.exception;

public class CustomException extends RuntimeException {
    //异常错误编码
    private Integer code;
    //异常信息
    private String message;

    private CustomException() {
    }

    public CustomException(CustomExceptionTypeEnum exceptionTypeEnum, String message) {
        this.code = exceptionTypeEnum.getCode();
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}