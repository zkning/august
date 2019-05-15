package com.august.thymelef.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class Menu implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    // 父菜单ID，一级菜单为0
    private Long parentId;

    // 菜单名称
    private String name;

    // 编号
    private String code;

    // 菜单URL
    private String url;

    // 类型  1=菜单 2=按钮
    private Integer type;

    // 菜单图标
    private String icon;

    // 排序
    private Integer sort;

    // 创建时间
    private Date createTime;

    // 修改时间
    private Date modifiedTime;

    private List<Menu> child = new ArrayList<>();
}
