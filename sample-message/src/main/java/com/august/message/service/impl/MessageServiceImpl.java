package com.august.message.service.impl;

import com.august.message.service.MessageService;
import com.august.website.dto.Resp;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Created by zkning on 2020/6/14.
 */
@Slf4j
@Service
public class MessageServiceImpl implements MessageService {

    public static final String url = "www.baidu.com";

    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "sendBaiduFallback")
    @Override
    public Resp sendBaidu() {
        ResponseEntity re = restTemplate.getForEntity(url, Object.class);
        return Resp.SUCCESS();
    }


    public Resp sendBaiduFallback() {
        log.info("send baidu faill {}", System.currentTimeMillis());
        return null;
    }
}
