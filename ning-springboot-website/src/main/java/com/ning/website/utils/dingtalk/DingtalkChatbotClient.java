package com.ning.website.utils.dingtalk;

import com.alibaba.fastjson.JSONObject;
import com.ning.website.utils.HttpClientUtil;
import com.ning.website.utils.dingtalk.message.Message;

import java.io.IOException;

/**
 * Created by dustin on 2017/3/17.
 */
public class DingtalkChatbotClient {

    public static SendResult send(String webhook, Message message) throws IOException {
        String result = HttpClientUtil.doPost(webhook, message.toJsonString());
        SendResult sendResult = new SendResult();
        JSONObject obj = JSONObject.parseObject(result);
        Integer errcode = obj.getInteger("errcode");
        sendResult.setErrorCode(errcode);
        sendResult.setErrorMsg(obj.getString("errmsg"));
        sendResult.setIsSuccess(errcode.equals(0));
        return sendResult;
    }
}


