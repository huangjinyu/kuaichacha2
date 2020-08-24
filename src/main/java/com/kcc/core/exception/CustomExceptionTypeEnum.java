package com.kcc.core.exception;

public enum CustomExceptionTypeEnum {
    UNKNOWN_ERROR(500, "未知异常，请联系管理员"),
    NOT_FOUND(404, "该接口不存在"), // 别名 NO_HANDLER_FOUND_ERROR
    USER_INPUT_ERROR(400, "用户输入错误"),
    SERVER_ERROR(500, "服务器异常"),
    OTHER_ERROR(999, "未知异常"),
    UNAUTHORIZED(401, "未认证"),
    FORBIDDEN(403, "没有访问权限");

    //异常类型编码
    private Integer code;
    //异常类型描述
    private String desc;

    CustomExceptionTypeEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
