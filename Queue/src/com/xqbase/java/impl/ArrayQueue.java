package com.xqbase.java.impl;


import com.xqbase.java.Queue;
import com.xqbase.java.exception.EmptyQueueException;
import com.xqbase.java.exception.FullQueueException;

/**
 * A queue interface implementation based on
 * fixed size array.
 *
 * @author Tony He
 */
public class ArrayQueue<E> implements Queue<E> {

    private static final int DEFAULT_CAPACITY = 100;

    protected int capacity;
    protected E[] e;
    protected int f;
    protected int r;

    public ArrayQueue() {
        this(DEFAULT_CAPACITY);
    }

    public ArrayQueue(int cap) {
        capacity = cap;
        e = (E[]) new Object[capacity];
        f = 0;
        r = 0;
    }

    @Override
    public int size() {
        return (capacity - f + r) % capacity;
    }

    @Override
    public boolean isEmpty() {
        return (f == r);
    }

    @Override
    public E font() throws EmptyQueueException {
        if (isEmpty())
            throw new EmptyQueueException("Queue is empty.");

        return e[f];
    }

    @Override
    public void enqueue(E element) throws FullQueueException {
        if (size() == capacity - 1)
            throw new FullQueueException("Queue is full.");

        e[r] = element;
        r = (r + 1) % capacity;
    }

    @Override
    public E dequeue() throws EmptyQueueException {
        if (isEmpty())
            throw new EmptyQueueException("Queue is empty");

        E tmp = e[f];
        e[f] = null;
        f = (f + 1) % capacity;
        return tmp;
    }
}
