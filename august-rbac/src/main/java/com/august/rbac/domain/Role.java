package com.august.rbac.domain;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户角色
 * Created by lenovo on 2017/11/11.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_rbac_role")
public class Role extends Exampletable {

    @ApiModelProperty(value = "角色名称")
    @TableField("role_name")
    private String roleName;

    @ApiModelProperty(value = "角色编码")
    @TableField("role_code")
    private String roleCode;

    @ApiModelProperty(value = "该角色拥有的分组id(分组顶级节点)")
    @TableField("group_id")
    private Long groupId;

    @ApiModelProperty(value = "分类")
    @TableField("role_type")
    private Integer roleType;

    @ApiModelProperty(value = "备注")
    @TableField("remark")
    private String remark;
}
