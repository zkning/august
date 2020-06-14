package com.august.staff.client;

import com.august.message.rmi.MessageRmi;
import com.august.message.rmi.dto.SendRequest;
import com.august.message.rmi.dto.SendResp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

/**
 * Created by zkning on 2020/6/14.
 */
@FeignClient(name = "sample-message", path = "message", fallback = MessageRmiFallback.class)
public interface MessageRmiClient extends MessageRmi {
}


/**
 * feign hystrix fallback impl
 *
 * @author zkning
 */
@Slf4j
@Component
class MessageRmiFallback implements MessageRmiClient {

    @Override
    public SendResp send(@RequestBody @Valid SendRequest sendRequest) {
        log.error("message rmi fallback", sendRequest.toString());
        SendResp sendResp = new SendResp();
        sendResp.setMsg("fallback default msg");
        return sendResp;
    }
}