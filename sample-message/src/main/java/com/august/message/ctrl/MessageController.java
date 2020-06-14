package com.august.message.ctrl;

import com.august.message.rmi.MessageRmi;
import com.august.message.rmi.dto.SendRequest;
import com.august.message.rmi.dto.SendResp;
import com.august.message.service.MessageService;
import com.august.website.dto.Resp;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by lenovo on 2020/6/14.
 */
@Api(description = "消息服务")
@Slf4j
@RestController
@RequestMapping("/message")
public class MessageController implements MessageRmi {

    @Autowired
    MessageService messageService;

    @Override
    public SendResp send(SendRequest sendRequest) {
        log.info(sendRequest.toString());
        SendResp sendResp = new SendResp();
        sendResp.setSucces(1);
        sendResp.setSource("短信");
        sendResp.setMsg(sendRequest.getMsg());
        return sendResp;
    }

    @GetMapping("/baidu")
    public Resp sendBd() {
        return messageService.sendBaidu();
    }
}
