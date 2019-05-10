package com.august.redisson;

/**
 * 分布式锁回调
 */
public interface DistributedLockCallback<T> {
    T process();
}
