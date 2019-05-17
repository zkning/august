package com.august.rbac.domain;

import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

@Data
@TableName("t_rbac_dict")
public class Dict extends Exampletable {

    private String text;
    private String value;
    private Long pid;
    private Integer sort;
    private String remark;
}
