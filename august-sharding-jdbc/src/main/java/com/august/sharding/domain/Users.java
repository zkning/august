package com.august.sharding.domain;


import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("t_users")
public class Users {

    private Long id;
    private String name;
    private Date time;
}
