package com.august.commons.dingtalk;

import com.august.commons.utils.HttpClientUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;

/**
 * 钉钉
 */
@Slf4j
public class DingTalkUtil {

    /**
     * 添加预警通知
     * @param content 消息体
     * @param atAll   是否@全部
     * @return 返回结果
     * @throws
     */
    public static String addDingTalk(String content, boolean atAll, String url, String... mobiles) {
        try {
            List mobileList = Arrays.asList(mobiles);
            String ret = HttpClientUtil.doPost(url, new TextMessage(content, mobileList, atAll).toJsonString());
            log.debug("钉钉预警返回:{}", ret);
            return ret;
        } catch (Exception e) {
            log.error("钉钉预警异常", e);
        }
        return "";
    }
}
