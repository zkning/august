package com.ning.message.ctrl;

import com.ning.message.api.MessageApi;
import com.ning.message.api.dto.SendRequest;
import com.ning.message.api.dto.SendResp;
import com.ning.message.service.MessageService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by lenovo on 2020/6/14.
 */
@Api(description = "消息服务")
@Slf4j
@RestController
@RequestMapping("/message")
public class MessageController implements MessageApi {

    @Autowired
    MessageService messageService;

    @Override
    public SendResp send(SendRequest sendRequest) {
        log.info("消息接口被调用,请求参数:{}", sendRequest.toString());
        SendResp sendResp = new SendResp();
        sendResp.setSucces(1);
        sendResp.setSource("短信");
        sendResp.setMsg(sendRequest.getMsg());
        return sendResp;
    }
}
