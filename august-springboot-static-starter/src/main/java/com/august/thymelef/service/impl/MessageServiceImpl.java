package com.august.thymelef.service.impl;

import com.august.thymelef.domain.Message;
import com.august.thymelef.mapper.MessageMapper;
import com.august.thymelef.service.IMessageService;
import com.august.website.utils.Pager;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 消息表  服务实现类
 * </p>
 *
 * @author zkning
 * @since 2019-05-15
 */
@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message> implements IMessageService {

    @Override
    public List<Message> list(Pager pager, Long userId, Integer type) {
        Page page = new Page(pager.getPageNo(), pager.getPageSize());
        List<Message> list = baseMapper.list(page, userId, type);
        pager.setContent(list);
        pager.setTotalElements(page.getTotal());
        return list;
    }
}
