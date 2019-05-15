package com.august.thymelef.service;

import com.august.thymelef.domain.Message;
import com.august.website.utils.Pager;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author zkning
 * @since 2019-05-15
 */
public interface IMessageService extends IService<Message> {


    List<Message> list(Pager pager, Long userId, Integer type);
}
