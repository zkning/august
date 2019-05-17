package com.august.rbac.domain;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.List;

/**
 * Created by lenovo on 2017/11/11.
 */
@Data
@TableName("t_rbac_res")
public class Res extends Exampletable {

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
    @TableField("res_type")
    private Integer resType;

    @ApiModelProperty(value = "扩展数据")
    private String extra;

    /**
     * 下级菜单
     */
    @TableField(exist = false)
    @ApiModelProperty(value = "下级菜单")
    private List<Res> children;

}
