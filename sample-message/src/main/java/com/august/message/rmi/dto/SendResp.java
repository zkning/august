package com.august.message.rmi.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by lenovo on 2020/6/14.
 */
@Data
public class SendResp {

    /**
     * 是否推送成功
     */
    @ApiModelProperty(value = "是否推送成功")
    private Integer succes;

    /**
     * 发送平台
     */
    @ApiModelProperty(value = "类型")
    private String source;

    // 发送内容
    @ApiModelProperty(value = "发送内容")
    private String msg;
}
