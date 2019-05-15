package com.august.thymelef.mapper;

import com.august.thymelef.domain.Message;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * Mapper接口
 * </p>
 *
 * @author zkning
 * @since 2019-05-15
 */
@Mapper
public interface MessageMapper extends BaseMapper<Message> {


    List<Message> list(Page page, @Param("userId") Long userId, @Param("type") Integer type);

}