package com.august.website.annotation.trace;


import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Trace {

    /**
     * 业务名称
     * @return
     */
    public String value() default "";

    /**
     * 推送钉钉消息
     * @return
     */
    boolean sendDing() default false;
}
