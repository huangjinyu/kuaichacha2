package com.kcc.core;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 统一返回结果
 *
 * @param <T>
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Result<T> implements Serializable {
    /**
     * 状态码
     */
    private Integer code;
    /**
     * 描述
     */
    private String msg;
    /**
     * 数据
     */
//    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    private List<ResultFieldError> errors;

    /**
     * 方法1：
     * Timestamp d = new Timestamp(System.currentTimeMillis());
     * <p>
     * 方法2：
     * Date date = new Date();
     * Timestamp d = new Timestamp(date.getTime());
     * <p>
     * 获取 timestamp
     * d.getTime()
     */
//    private long timestamp;

    private Date timestamp;

    public static Result success(Object object) {
        Result result = new Result();
        result.setCode(ResultCodeEnum.SUCCESS.getCode());
        result.setMsg("成功");
        result.setData(object);
        return result;
    }

    public static Result success() {
        return Result.success(null);
    }

    public static Result error(Integer code, String msg) {
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        result.setData(null);
        return result;
    }

    public static Result error(Integer code, String msg, Object object) {
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        result.setData(object);
        return result;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public List<ResultFieldError> getErrors() {
        return errors;
    }

    public void setErrors(List<ResultFieldError> errors) {
        this.errors = errors;
    }

//    public long getTimestamp() {
//        return timestamp;
//    }
//
//    public void setTimestamp(long timestamp) {
//        this.timestamp = timestamp;
//    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
