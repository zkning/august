package com.august.rbac.dto;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

@Data
public class UpdatePwdDTO {

    @NotBlank(message = "旧密码不能为空")
    private String password;

    @NotBlank(message = "新密码不能为空")
    private String newPassword;

    @NotBlank(message = "新密码不能为空")
    private String newPassword2;
}
