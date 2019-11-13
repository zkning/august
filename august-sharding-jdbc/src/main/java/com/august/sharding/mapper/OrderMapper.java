package com.august.sharding.mapper;
import com.august.sharding.domain.Order;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OrderMapper extends BaseMapper<Order> {

    /**
     * 查询订单
     * @param userId
     * @return
     */
    List<Order> findByUserId(@Param("userId") Long userId);
    List<Order> selectByUserId(@Param("userId") Long userId);
}
