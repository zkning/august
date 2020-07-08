package com.ning.message.controller;

import com.ning.website.dto.Resp;
import com.ning.message.api.dto.SendRequest;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by lenovo on 2020/6/14.
 */
@Api(description = "消息服务")
@Slf4j
@RestController
@RequestMapping("/message")
public class MessageController {

    public Resp send(SendRequest sendRequest) {
        return Resp.SUCCESS();
    }
}
