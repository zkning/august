package com.august.message.api;

import com.august.message.api.dto.SendRequest;
import com.august.message.api.dto.SendResp;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

/**
 * message fegin api
 * Created by zkning on 2020/6/14.
 */
public interface MessageApi {

    @ApiOperation(value = "发送消息", notes = "用户和消息不能为空")
    @PostMapping("/send")
    SendResp send(@RequestBody @Valid SendRequest sendRequest);
}
