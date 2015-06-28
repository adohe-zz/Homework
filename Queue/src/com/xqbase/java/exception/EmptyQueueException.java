package com.xqbase.java.exception;

/**
 * Empty Queue Exception.
 *
 * @author Tony He
 */
public class EmptyQueueException extends RuntimeException {

    public EmptyQueueException(String message) {
        super(message);
    }
}
