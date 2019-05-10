package com.august.redisson;

import com.august.redisson.annotation.DistributedLock;
import com.august.redisson.exception.DistributedLockException;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * redisson lock template
 */
@Component
public class RedissonLockTemplate implements RedissonDistributedLock {

    @Autowired
    private RedissonClient redissonClient;

    @Override
    public RLock getFairLock(String var1) {
        return redissonClient.getFairLock(var1);
    }

    @Override
    public RLock getLock(String var1) {
        return redissonClient.getLock(var1);
    }

    @Override
    public <T> T lock(String key, DistributedLockCallback<T> callback, boolean fairLock) {
        return lock(key, callback, DistributedLock.WAITTIME, DistributedLock.LEASETIME, TimeUnit.SECONDS, fairLock);
    }

    @Override
    public <T> T lock(String key, DistributedLockCallback<T> callback, long leaseTime, TimeUnit timeUnit, boolean fairLock) {
        return lock(key, callback, DistributedLock.WAITTIME, leaseTime, timeUnit, fairLock);
    }

    @Override
    public <T> T lock(String key, DistributedLockCallback<T> callback, long waitTime, long leaseTime, TimeUnit timeUnit, boolean fairLock) {
        RLock rLock;
        if (fairLock) {
            rLock = this.getFairLock(key);
        } else {
            rLock = this.getLock(key);
        }
        try {
            boolean result = rLock.tryLock(waitTime, leaseTime, timeUnit);
            if (result) {
                return callback.process();
            }
            throw new DistributedLockException("Distributed tryLock failure");
        } catch (InterruptedException e) {
            throw new DistributedLockException(e);
        } finally {
            if (null != rLock && rLock.isLocked()) {
                rLock.unlock();
            }
        }
    }
}
