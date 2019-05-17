package com.august.rbac.domain;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 用户组
 * Created by lenovo on 2017/11/11.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_rbac_group")
public class Group extends Exampletable implements Comparable<Group> {

    @ApiModelProperty(value = "分组名称")
    @TableField("group_name")
    private String groupName;

    @ApiModelProperty(value = "备注")
    @TableField("remark")
    private String remark;

    @ApiModelProperty(value = "上级id")
    @TableField("pid")
    private Long pid;

    @ApiModelProperty(value = "分组类型")
    @TableField("group_type")
    private Integer groupType;

    @ApiModelProperty(value = "扩展数据")
    @TableField("extra")
    private String extra;

    @ApiModelProperty(value = "是否有效")
    private Integer isValid;

    @TableField(exist = false)
    private List<Group> children;

    @TableField(exist = false)
    private int level;

    @Override
    public int compareTo(Group group) {
        return this.getLevel() - group.getLevel();
    }
}
