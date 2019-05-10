package com.august.redisson;

import org.redisson.api.RLock;

import java.util.concurrent.TimeUnit;

/**
 *  zkning
 *  redisson lock template
 */
public interface RedissonDistributedLock {

    /**
     * 公平锁
     *
     * @param var1
     * @return
     */
    RLock getFairLock(String var1);

    /**
     * 非公平锁
     *
     * @param var1
     * @return
     */
    RLock getLock(String var1);

    /**
     * 使用分布式锁，使用锁默认超时时间。
     *
     * @param callback
     * @param fairLock 是否使用公平锁
     * @return
     */
    <T> T lock(String lockName, DistributedLockCallback<T> callback, boolean fairLock);

    /**
     * 使用分布式锁。自定义锁的超时时间
     *
     * @param callback
     * @param leaseTime 锁超时时间。超时后自动释放锁。
     * @param timeUnit
     * @param fairLock  是否使用公平锁
     * @param <T>
     * @return
     */
    <T> T lock(String lockName, DistributedLockCallback<T> callback, long leaseTime, TimeUnit timeUnit, boolean fairLock);

    /**
     * 尝试分布式锁，自定义等待时间、超时时间。
     *
     * @param callback
     * @param waitTime  获取锁最长等待时间
     * @param leaseTime 锁超时时间。超时后自动释放锁。
     * @param timeUnit
     * @param fairLock  是否使用公平锁
     * @param <T>
     * @return
     */
    <T> T lock(String lockName, DistributedLockCallback<T> callback, long waitTime, long leaseTime, TimeUnit timeUnit, boolean fairLock);

}
