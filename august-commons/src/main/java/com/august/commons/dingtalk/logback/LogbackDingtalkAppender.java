package com.august.commons.dingtalk.logback;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.classic.spi.IThrowableProxy;
import ch.qos.logback.classic.spi.ThrowableProxyUtil;
import ch.qos.logback.core.UnsynchronizedAppenderBase;
import ch.qos.logback.core.status.ErrorStatus;
import com.august.commons.dingtalk.DingtalkChatbotClient;
import com.august.commons.dingtalk.SendResult;
import com.august.commons.dingtalk.message.MarkdownMessage;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Getter
@Setter
@Slf4j
/**
 * 钉钉日志appender
 * @since 2018/7/20
 */
public class LogbackDingtalkAppender extends UnsynchronizedAppenderBase<ILoggingEvent> {

    public static final String DINGTALK_MARKER = "[DINGTALK]";
    private static ExecutorService pool = new ThreadPoolExecutor(1, 20, 10, TimeUnit.SECONDS,
            new SynchronousQueue<Runnable>(), new ThreadFactoryBuilder().setNameFormat("DingtalkChatbotLogAppender-" + "-%d").build(), new ThreadPoolExecutor.CallerRunsPolicy());


    private String webhook;


    private String marker;


    @Override
    public void start() {
        int errors = 0;


        if (marker == null) {
            marker = DINGTALK_MARKER;
        }
        if (webhook == null) {
            addStatus(new ErrorStatus("No webhook set for the appender named \"" + name + "\".", this));
            errors++;
        }


        if (errors == 0) {
            super.start();
        }
    }

    ;

    @Override
    protected void append(ILoggingEvent evt) {
        if (!isStarted()) {
            return;
        }
        pool.submit(() -> {
            MarkdownMessage message = new MarkdownMessage();

            String evtMessage = evt.getFormattedMessage();
            if (StringUtils.contains(evtMessage, marker)) {
                evtMessage = StringUtils.replace(evtMessage, marker, "");
            }
            message.add(MarkdownMessage.getHeaderText(1, evtMessage));

            message.setTitle(evtMessage);
            IThrowableProxy tp = evt.getThrowableProxy();
            if (tp != null) {
                message.add(MarkdownMessage.getReferenceText(ThrowableProxyUtil.asString(tp)));
            }
            try {
                SendResult sendResult = DingtalkChatbotClient.send(webhook, message);
                log.debug("send to dingtalk webhook {},result {}", webhook, sendResult.toString());
            } catch (IOException e) {
                log.warn("send to dingtalk webhook {} fail", webhook, e);
            }
        });

    }

}
