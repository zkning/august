package com.august.rbac.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class SaveRoleResDTO {

    @NotNull(message = "角色id不能为空")
    @ApiModelProperty(value = "角色id",required = true)
    private Long roleId;

    @NotNull(message = "资源id不能为空")
    @ApiModelProperty(value = "资源id",required = true)
    private List<Long> resIds;
}
