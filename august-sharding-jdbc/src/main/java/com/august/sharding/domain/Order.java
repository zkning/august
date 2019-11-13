package com.august.sharding.domain;

import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("t_order")
public class Order {
    private Long id;
    private String name;
    private Date time;
    private Long userId;
}
