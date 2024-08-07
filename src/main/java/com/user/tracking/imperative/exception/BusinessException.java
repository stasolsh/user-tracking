package com.user.tracking.imperative.exception;

import java.io.Serial;

public class BusinessException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -1554383918868251724L;

    public BusinessException(String msg) {
        super(msg);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }
}
