package com.august.rbac.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UnLockDTO {

    @NotNull(message = "请输入密码")
    @ApiModelProperty(value = "登录密码")
    private String password;
}
