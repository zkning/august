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

    /**
     * 查询用户消息
     * */
    List<Message> list(Page page, @Param("userId") Long userId, @Param("type") Integer type);

    /**
     * 未读的列表
     */
    List<Message> selectByUserIdAndType(@Param("userId") Long userId, @Param("type") Integer type);

    int ready(@Param("ids") List<Long> ids);

    int readyAll(@Param("userId") Long userId, @Param("type") Integer type);

    List<Message> selectByUserIdAndState(@Param("userId") Long userId, @Param("state") Integer state);
}