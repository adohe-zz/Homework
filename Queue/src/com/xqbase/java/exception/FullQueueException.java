package com.xqbase.java.exception;

/**
 * Full Queue Exception.
 *
 * @author Tony He
 */
public class FullQueueException extends RuntimeException {

    public FullQueueException(String message) {
        super(message);
    }
}
