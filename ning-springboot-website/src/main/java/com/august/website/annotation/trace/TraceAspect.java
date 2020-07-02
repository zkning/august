package com.august.website.annotation.trace;

import com.august.website.logback.LogbackDingtalkAppender;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class TraceAspect {

    @Value("${dingtalk.enabled:#{null}}")
    private String dingtalk;

    @Around(value = "@annotation(trace)")
    public Object invoked(ProceedingJoinPoint pjp, Trace trace) throws Throwable {
        try {
            Long currentTime = System.currentTimeMillis();
            MDC.put("trace", currentTime.toString());
            StringBuilder sb = new StringBuilder(">>>>>>>>>>>>>>>>>").append(trace.value());
            if (trace.sendDing()) {
                sb.append(LogbackDingtalkAppender.DINGTALK_MARKER);
            }
            log.info("{}开始执行", sb);
            Object ret = pjp.proceed();
            log.info("{}执行完成,耗时:{}s", sb, (currentTime - System.currentTimeMillis() / 1000.0));
            return ret;
        } finally {
            MDC.clear();
        }
    }
}
