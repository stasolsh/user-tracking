package com.awin.cod.challenge.imperative.exception;

import java.io.Serial;

public class BusinesException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -1554383918868251724L;

    public BusinesException(String msg) {
        super(msg);
    }

    public BusinesException(String message, Throwable cause) {
        super(message, cause);
    }
}
