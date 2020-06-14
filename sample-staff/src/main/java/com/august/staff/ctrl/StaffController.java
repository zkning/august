package com.august.staff.ctrl;

import com.august.message.rmi.dto.SendRequest;
import com.august.message.rmi.dto.SendResp;
import com.august.staff.client.MessageRmiClient;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by lenovo on 2020/6/14.
 */
@RestController
@RequestMapping("/staff")
public class StaffController {

    @Autowired
    MessageRmiClient messageRmiClient;

    @ApiOperation(value = "推送消息")
    @GetMapping("/send")
    public SendResp send() {
        SendRequest sendRequest = new SendRequest();
        sendRequest.setMsg("hello word!");
        sendRequest.setUsername("admnistrator");
        SendResp sendResp = messageRmiClient.send(sendRequest);
        return sendResp;
    }
}
