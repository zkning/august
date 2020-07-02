package com.ning.website.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 分页数据
 *
 * @author zkning
 */
@Data
public class Pager<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer pageSize;
    private Integer pageNo;
    private Long totalElements;
    private List<T> content;
    public Pager(){
    }
    public Pager(Integer pageNo, Integer pageSize) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
    }
}
