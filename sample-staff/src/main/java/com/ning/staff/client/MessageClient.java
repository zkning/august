package com.ning.staff.client;

import com.ning.message.api.MessageApi;
import com.ning.message.api.dto.SendRequest;
import com.ning.message.api.dto.SendResp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

/**
 * Created by zkning on 2020/6/14.
 */
@FeignClient(name = "sample-message", path = "api", fallback = MessageClient.MessageClientFallback.class)
public interface MessageClient extends MessageApi {


    /**
     * feign hystrix fallback impl
     *
     * @author zkning
     */
    @Slf4j
    @Component
    class MessageClientFallback implements MessageClient {

        @Override
        public SendResp send(@RequestBody @Valid SendRequest sendRequest) {
            log.error("message api fallback", sendRequest.toString());
            SendResp sendResp = new SendResp();
            sendResp.setMsg("feign fallback default msg");
            return sendResp;
        }
    }
}


