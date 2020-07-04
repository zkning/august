package com.ning.staff.controller;

import com.august.message.api.dto.SendRequest;
import com.august.message.api.dto.SendResp;
import com.ning.staff.client.MessageClient;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by lenovo on 2020/6/14.
 */
@Slf4j
@RestController
@RequestMapping("/staff")
public class StaffController {

    @Autowired
    MessageClient messageClient;

    @ApiOperation(value = "注册")
    @GetMapping("/registry")
    public SendResp registry() {
        log.info("员工注册成功触发消息推送，注册时间:{}", System.currentTimeMillis());

        SendRequest sendRequest = new SendRequest();
        sendRequest.setMsg("hello word!");
        sendRequest.setUsername("admnistrator");
        SendResp sendResp = messageClient.send(sendRequest);
        return sendResp;
    }
}
