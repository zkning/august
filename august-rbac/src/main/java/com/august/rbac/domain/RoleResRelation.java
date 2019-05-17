package com.august.rbac.domain;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 用户角色关联
 * Created by lenovo on 2017/11/11.
 */
@Data
@TableName("t_rbac_role_resource_relation")
public class RoleResRelation extends Exampletable {

    @ApiModelProperty(value = "角色id")
    @TableField("role_id")
    private Long roleId;

    @ApiModelProperty(value = "资源id")
    @TableField("res_id")
    private Long resId;
}
