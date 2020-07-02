package com.august.website.consts;

/**
 * 状态码
 * AAABB（20000）
 * AAA 错误码
 * BB 模块
 * 前缀700为自定义错误码（70000）
 *
 * @author zkning
 */
public enum StatusCodeEnum {

    OK(20000, "OK"),

    BAD_REQUEST(40000, "Bad Request"),
    UNAUTHORIZED(40100, "Unauthorized"),
    FORBIDDEN(40300, "Forbidden"),
    NOT_FOUND(40400, "Not Found"),
    Method_Not_Allowed(40500, "Method Not Allowed"),
    REQUEST_TIMEOUT(40800, "Request Timeout"),
    UNSUPPORTED_MEDIA_TYPE(41500, "Unsupported Media Type"),

    INTERNAL_SERVER_ERROR(50000, "Internal Server Error"),
    SERVICE_UNAVAILABLE(50300, "Service Unavailable"),
    GATEWAY_TIMEOUT(50400, "Gateway Timeout"),



    ;
    public Integer code;
    public String message;

    private StatusCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
