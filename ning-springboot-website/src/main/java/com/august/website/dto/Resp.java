package com.august.website.dto;

import com.alibaba.fastjson.JSONObject;
import com.august.website.consts.StatusCodeEnum;
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
    private String msg;

    @ApiModelProperty(value = "响应结果")
    private T data;

    public Resp() {
    }

    public Resp(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Resp(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }


    public static Resp getInstance(Integer code, String message, Object result) {
        return new Resp(code, message, result);
    }

    public static Resp getInstance(Integer code, String message) {
        return new Resp(code, message, null);
    }

    public static Resp SUCCESS() {
        return new Resp(StatusCodeEnum.OK.code, StatusCodeEnum.OK.message, null);
    }

    public static Resp SUCCESS(Object result) {
        return new Resp(StatusCodeEnum.OK.code, StatusCodeEnum.OK.message, result);
    }

    public static Resp FAILURE() {
        return new Resp(StatusCodeEnum.INTERNAL_SERVER_ERROR.code, StatusCodeEnum.INTERNAL_SERVER_ERROR.message, null);
    }

    public static Resp FAILURE(String message) {
        return new Resp(StatusCodeEnum.INTERNAL_SERVER_ERROR.code, message, null);
    }

    public static Resp FAILURE(Object result) {
        return new Resp(StatusCodeEnum.INTERNAL_SERVER_ERROR.code, StatusCodeEnum.INTERNAL_SERVER_ERROR.message, result);
    }

    public static Resp FAILURE(Exception ex) {
        return new Resp(StatusCodeEnum.INTERNAL_SERVER_ERROR.code, ex.getMessage(), null);
    }

    public static Resp FAILURE(Integer code, String message) {
        return new Resp(code, message, null);
    }

    public static Resp SYSTEMEXCEPTION(Exception ex) {
        return new Resp(StatusCodeEnum.INTERNAL_SERVER_ERROR.code, StatusCodeEnum.INTERNAL_SERVER_ERROR.message, ex);
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

    /**
     * 检查是否成功
     */
    public Boolean checkSuccess() {
        return StatusCodeEnum.OK.code.equals(this.code);
    }

    /**
     * toJsonString
     */
    public String toJsonString() {
        return JSONObject.toJSONString(this);
    }
}
