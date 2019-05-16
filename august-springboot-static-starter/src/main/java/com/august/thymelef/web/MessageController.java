package com.august.thymelef.web;

import com.august.thymelef.domain.Message;
import com.august.thymelef.service.IMessageService;
import com.august.website.utils.Pager;
import com.august.website.utils.Resp;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * <p>
 * 消息表  前端控制器
 * </p>
 *
 * @author zkning
 * @since 2019-05-15
 */
@Slf4j
@Controller
@RequestMapping("/message")
public class MessageController {

    @Autowired
    IMessageService iMessageService;

    @GetMapping("/index")
    ModelAndView index() {
        List<Message> msgList = iMessageService.list(1L, 1);
        Integer count = CollectionUtils.isNotEmpty(msgList) ? msgList.size() : 0;
        ModelAndView modelAndView = new ModelAndView("app/message/index");
        modelAndView.addObject("count", count);
        return modelAndView;
    }

    @GetMapping("/all")
    @ResponseBody
    Resp<Pager<Message>> all(Integer type, Integer pageNo, Integer pageSize) {
        Pager pager = new Pager(pageNo, pageSize);
        iMessageService.list(pager, 1L, type);
        return Resp.SUCCESS(pager);
    }


    @GetMapping("/detail")
    ModelAndView detail(String id) {
        ModelAndView modelAndView = new ModelAndView("app/message/detail");
        modelAndView.addObject("id", id);
        return modelAndView;
    }

    @GetMapping("/selectById")
    @ResponseBody
    Resp selectById(String id) {
        Message msg = iMessageService.selectById(id);
        return Resp.SUCCESS(msg);
    }

    @PostMapping("/del")
    @ResponseBody
    Resp<Message> del(@RequestBody List<Long> ids) {
        Boolean ret = iMessageService.deleteBatchIds(ids);
        return Resp.SUCCESS();
    }

    @PostMapping("/ready")
    @ResponseBody
    Resp ready(@RequestBody List<Long> ids) {
        Integer ret = iMessageService.ready(ids);

        // 查询未读数据
        List<Message> msgList = iMessageService.list(1L, 1);
        Integer count = CollectionUtils.isNotEmpty(msgList) ? msgList.size() : 0;
        return Resp.SUCCESS(count);
    }

    @PostMapping("/readyAll")
    @ResponseBody
    Resp readyAll() {
        Integer ret = iMessageService.readyAll(1L);

        // 查询未读数据
        List<Message> msgList = iMessageService.list(1L, 1);
        Integer count = CollectionUtils.isNotEmpty(msgList) ? msgList.size() : 0;
        return Resp.SUCCESS(count);
    }

    @PostMapping("/unReady")
    @ResponseBody
    Resp unReady() {
        List ret = iMessageService.unReady(1L, 0);
        return Resp.SUCCESS(CollectionUtils.isNotEmpty(ret) ? ret.size() : 0);
    }
}
