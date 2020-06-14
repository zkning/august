package com.august.staff.client;

import com.august.message.rmi.MessageRmi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * Created by lenovo on 2020/6/14.
 */
@FeignClient(name = "sample-message", path = "message")
public interface MessageRmiClient extends MessageRmi {
}
