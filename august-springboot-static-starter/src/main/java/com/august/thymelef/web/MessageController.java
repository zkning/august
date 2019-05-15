package com.august.thymelef.web;

import com.august.thymelef.domain.Message;
import com.august.thymelef.service.IMessageService;
import com.august.website.utils.Pager;
import com.august.website.utils.Resp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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
        return new ModelAndView("app/message/index");
    }

    @GetMapping("/all")
    @ResponseBody
    Resp<Pager<Message>> all(Integer type, Integer pageSize, Integer pageNo) {
        Pager pager = new Pager(1, 30);
        iMessageService.list(pager, 1L, type);
        return Resp.SUCCESS(pager);
    }


    @GetMapping("/detail")
    String detail(String id) {
        log.info("message id :{}", id);
        return "app/message/detail";
    }
}
