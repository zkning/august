package com.august.thymelef.utils;

import com.alibaba.fastjson.JSONObject;
import com.august.thymelef.consts.StatusCodeEnum;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 响应
 *
 * @author zkning
 */
public class Resp<T> implements Serializable {
    private static final long serialVersionUID = 665474083431171685L;
    @ApiModelProperty(value = "响应码")
    private Integer code;

    @ApiModelProperty(value = "响应消息")
    private String message;

    @ApiModelProperty(value = "响应结果")
    private T result;

    public Resp() {
    }

    public Resp(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Resp(Integer code, String message, T result) {
        this.code = code;
        this.message = message;
        this.result = result;
    }


    public static Resp getInstance(Integer code, String message, Object result) {
        return new Resp(code, message, result);
    }

    public static Resp getInstance(Integer code, String message) {
        return new Resp(code, message, null);
    }

    public static Resp SUCCESS() {
        return new Resp(StatusCodeEnum.SUCCESS.code, StatusCodeEnum.SUCCESS.message, null);
    }

    public static Resp SUCCESS(Object result) {
        return new Resp(StatusCodeEnum.SUCCESS.code, StatusCodeEnum.SUCCESS.message, result);
    }

    public static Resp FAILURE() {
        return new Resp(StatusCodeEnum.SYSTEM_ERROR.code, StatusCodeEnum.SYSTEM_ERROR.message, null);
    }

    public static Resp FAILURE(String message) {
        return new Resp(StatusCodeEnum.SYSTEM_ERROR.code, message, null);
    }

    public static Resp FAILURE(Object result) {
        return new Resp(StatusCodeEnum.SYSTEM_ERROR.code, StatusCodeEnum.SYSTEM_ERROR.message, result);
    }

    public static Resp FAILURE(Exception ex) {
        return new Resp(StatusCodeEnum.SYSTEM_ERROR.code, ex.getMessage(), null);
    }

    public static Resp FAILURE(Integer code, String message) {
        return new Resp(code, message, null);
    }

    public static Resp SYSTEMEXCEPTION(Exception ex) {
        return new Resp(StatusCodeEnum.SYSTEM_ERROR.code, StatusCodeEnum.SYSTEM_ERROR.message, ex);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    /**
     * 检查是否成功
     */
    public Boolean checkSuccess() {
        return StatusCodeEnum.SUCCESS.code.equals(this.code);
    }

    /**
     * toJsonString
     */
    public String toJsonString() {
        return JSONObject.toJSONString(this);
    }
}
