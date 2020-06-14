package com.august.website.dto;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * Created by zkning on 2020/6/14.
 */
public class Request implements Serializable {

    @ApiModelProperty(value = "用户ID", hidden = true)
    private String userId;
}
