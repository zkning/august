package com.ning.message.api.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * Created by lenovo on 2020/6/14.
 */
@Data
public class SendRequest {

    @NotBlank
    @ApiModelProperty(value = "发送内容", required = true)
    private String msg;

    @NotBlank
    @ApiModelProperty(value = "用户名", required = true)
    private String username;
}
