package com.august.staff.ctrl;

import com.august.message.api.dto.SendRequest;
import com.august.message.api.dto.SendResp;
import com.august.staff.rmi.MessageRmi;
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
    MessageRmi messageRmi;

    @ApiOperation(value = "推送消息")
    @GetMapping("/send")
    public SendResp send() {
        SendRequest sendRequest = new SendRequest();
        sendRequest.setMsg("hello word!");
        sendRequest.setUsername("admnistrator");
        SendResp sendResp = messageRmi.send(sendRequest);
        return sendResp;
    }
}
