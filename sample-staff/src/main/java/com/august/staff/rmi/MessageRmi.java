package com.august.staff.rmi;

import com.august.message.api.MessageApi;
import com.august.message.api.dto.SendRequest;
import com.august.message.api.dto.SendResp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

/**
 * Created by zkning on 2020/6/14.
 */
@FeignClient(name = "sample-message", path = "message", fallback = MessageRmi.MessageRmiFallback.class)
public interface MessageRmi extends MessageApi {


    /**
     * feign hystrix fallback impl
     *
     * @author zkning
     */
    @Slf4j
    @Component
    class MessageRmiFallback implements MessageRmi {

        @Override
        public SendResp send(@RequestBody @Valid SendRequest sendRequest) {
            log.error("message api fallback", sendRequest.toString());
            SendResp sendResp = new SendResp();
            sendResp.setMsg("feign fallback default msg");
            return sendResp;
        }
    }
}


