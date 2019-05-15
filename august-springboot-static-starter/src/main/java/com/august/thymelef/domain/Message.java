package com.august.thymelef.domain;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 消息表
 * </p>
 *
 * @author zkning
 * @since 2019-05-15
 */
@Data
public class Message implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     *
     */
    private Long id;

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    /**
     * 1=通知，2=私信
     */
    private Integer type;

    /**
     * 0=未读，1=已读
     */
    private Integer state;
    private Long userId;

    /**
     *
     */
    private Date createTime;

    /**
     *
     */
    private Date lastUpdateTime;

}
