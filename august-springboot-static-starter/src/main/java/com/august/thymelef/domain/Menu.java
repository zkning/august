package com.august.thymelef.domain;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@TableName("t_menu")
public class Menu extends Exampletable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "文本")
    private String text;

    @ApiModelProperty(value = "资源编码")
    private String code;

    @ApiModelProperty(value = "国际化")
    private String i18n;

    @ApiModelProperty(value = "link")
    private String link;

    @ApiModelProperty(value = "extraLink")
    @TableField("extra_link")
    private String extraLink;

    @ApiModelProperty(value = "图标")
    private String icon;

    @ApiModelProperty(value = "父级菜单")
    private Long pid;

    @ApiModelProperty(value = "类型")
    private Integer type;

    @ApiModelProperty(value = "扩展数据")
    private String extra;

    /**
     * 下级菜单
     */
    @TableField(exist = false)
    @ApiModelProperty(value = "下级菜单")
    private List<Menu> child;
}
