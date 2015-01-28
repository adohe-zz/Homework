package com.xqbase.java;

import com.xqbase.java.exception.EmptyQueueException;

/**
 * This Queue ADT support FIFO principle.
 *
 * @author Tony He
 */
public interface Queue<E> {

    /**
     * Returns the number of elements in the queue.
     */
    int size();

    /**
     * Returns whether the queue is empty or not.
     */
    boolean isEmpty();

    /**
     * Inspects the element at the font of the queue.
     * @throws EmptyQueueException
     */
    E font() throws EmptyQueueException;

    /**
     * Inserts an element at the rear of the queue.
     */
    void enqueue(E element);

    /**
     * Returns the element at the font of the queue.
     * @throws EmptyQueueException
     */
    E dequeue() throws EmptyQueueException;
}
