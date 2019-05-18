package com.august.thymelef.domain;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.Version;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * zkning
 */
@Data
public class Exampletable {
    private Long id;

    @Version
    @ApiModelProperty(value = "版本号")
    private Long version;

    @ApiModelProperty(value = "创建时间")
    @TableField("create_time")
    private Date createTime;

    @ApiModelProperty(value = "修改时间")
    @TableField("last_update_time")
    private Date lastUpdateTime;

    @ApiModelProperty(value = "修改者")
    @TableField("last_update_user")
    private String lastUpdateUser;

    @ApiModelProperty(value = "创建者")
    @TableField("create_user")
    private String createUser;

}
