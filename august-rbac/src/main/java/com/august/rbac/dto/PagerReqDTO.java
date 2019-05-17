package com.august.rbac.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class PagerReqDTO extends ReqDTO {

    @ApiModelProperty(value = "页码数")
    private int pageSize = 30;

    @ApiModelProperty(value = "页码")
    private int pageNo = 1;
}
