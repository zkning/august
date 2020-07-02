package com.ning.redisson;

/**
 * 分布式锁回调
 */
public interface DistributedLockCallback<T> {
    T process();
}
