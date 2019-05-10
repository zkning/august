package com.august.redisson.exception;

public class DistributedLockException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public DistributedLockException(String msg) {
        super(msg);
    }

    public DistributedLockException(Throwable cause) {
        super(cause);
    }
}
