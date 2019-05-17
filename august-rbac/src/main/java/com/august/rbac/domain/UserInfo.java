package com.august.rbac.domain;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by lenovo on 2017/11/10.
 */
@Data
@NoArgsConstructor
@TableName("t_rbac_user")
public class UserInfo extends Exampletable {

    @JsonIgnore
    @TableField("password")
    private String password;

    @ApiModelProperty(value = "用户名")
    @TableField("user_name")
    private String userName;

    @ApiModelProperty(value = "姓名")
    @TableField("name")
    private String name;

    @ApiModelProperty(value = "手机号码")
    @TableField("mobile")
    private String mobile;

    @ApiModelProperty(value = "邮箱")
    @TableField("email")
    private String email;

    @ApiModelProperty(value = "头像")
    @TableField("avatar")
    private String avatar;

    @ApiModelProperty(value = "所属组别id")
    @TableField("group_id")
    private Long groupId;

    @TableField(exist = false)
    @ApiModelProperty(value = "所属组别名称")
    private String groupName;
}
