package com.august.redisson.annotation;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

/**
 * zkning
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DistributedLock {
    public static final long LEASETIME = 1000L * 60 * 5;
    public static final long WAITTIME = 1;
    public static final TimeUnit TIME_UNIT = TimeUnit.MILLISECONDS;

    // 连接符
    public static final String SEPARATOR = "::";

    /**
     * alias
     *
     * @return
     */
    public String value() default "";

    /**
     * key支持spel表达式
     *
     * @return
     */
    public String key() default "";

    /**
     * 是否公平锁
     *
     * @return
     */
    public boolean fairLock() default true;

    /**
     * 过期时间
     */
    public long leaseTime() default LEASETIME;


    /**
     * 最长等候时间
     */
    public long waitTime() default WAITTIME;

    /**
     * 时间单位
     *
     * @return
     */
    public TimeUnit timeUnit() default TimeUnit.SECONDS;
}
