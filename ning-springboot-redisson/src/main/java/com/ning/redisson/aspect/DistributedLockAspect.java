package com.ning.redisson.aspect;


import com.ning.redisson.RedissonLockTemplate;
import com.ning.redisson.annotation.DistributedLock;
import com.ning.redisson.exception.DistributedLockException;
import com.ning.redisson.utils.SPELUtil;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
public class DistributedLockAspect {

    @Autowired
    RedissonLockTemplate redissonLockTemplate;
    LocalVariableTableParameterNameDiscoverer discoverer = new LocalVariableTableParameterNameDiscoverer();

    @Around(value = "@annotation(distributedLock)")
    public Object invoked(ProceedingJoinPoint pjp, DistributedLock distributedLock) throws Throwable {
        if (StringUtils.isBlank(distributedLock.key())) {
            throw new DistributedLockException("distributedLock lock key expression must not be null");
        }
        Method method = ((MethodSignature) pjp.getSignature()).getMethod();
        String[] params = discoverer.getParameterNames(method);
        Object[] args = pjp.getArgs();

        // 添加默认变量
        Map<String, Object> varMap = new HashMap<>();
        varMap.put("separator", DistributedLock.SEPARATOR);
        Object KV = SPELUtil.parse(distributedLock.key(), params, args, varMap, Object.class);
        if (null == KV) {
            throw new DistributedLockException("distributedLock lock key value must not be null");
        }

        // 获取锁前缀
        String prefix = distributedLock.value();
        if (StringUtils.isNotBlank(prefix)) {
            prefix = prefix + DistributedLock.SEPARATOR;
        }
        String locKey = prefix + String.valueOf(KV);
        return redissonLockTemplate.lock(locKey, () -> {
                    try {
                        return pjp.proceed();
                    } catch (Throwable throwable) {
                        throw new DistributedLockException(throwable);
                    }
                },
                distributedLock.waitTime(), distributedLock.leaseTime(), distributedLock.timeUnit(),
                distributedLock.fairLock());
    }
}
