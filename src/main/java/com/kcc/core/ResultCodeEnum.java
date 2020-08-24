package com.kcc.core;

/**
 * 统一返回结果编码
 *
 * @author kevin
 * @date 2019/7/4 14:10
 */
public enum ResultCodeEnum {
    // ILLEGAL_REQUEST_PARAMETERS
    UNKNOWN_ERROR(500, "未知异常，请联系管理员"),
    NO_HANDLER_FOUND_ERROR(404, "接口不存在"),
    UNAUTHORIZED(401, "未认证"),
    FORBIDDEN(403, "没有访问权限"),
    SUCCESS(200, "成功");

    private Integer code;
    private String msg;

    ResultCodeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}