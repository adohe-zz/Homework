package com.xqbase.java.impl;

import com.xqbase.java.Stack;
import com.xqbase.java.exception.EmptyStackException;
import com.xqbase.java.exception.FullStackException;

/**
 * Implementation of the stack ADT using a fixed-length array.
 *
 * @author Tony He
 */
public class ArrayStack<E> implements Stack<E> {

    private static final int DEFAULT_CAPACITY = 1000;

    protected int capacity;
    protected E e[];
    protected int top = -1;

    public ArrayStack() {
        this(DEFAULT_CAPACITY);
    }

    public ArrayStack(int capacity) {
        this.capacity = capacity;
        e = (E[]) new Object[this.capacity];
    }

    @Override
    public int size() {
        return (top + 1);
    }

    @Override
    public boolean isEmpty() {
        return top < 0;
    }

    @Override
    public E top() throws EmptyStackException {
        if (isEmpty())
            throw new EmptyStackException("Stack is empty");

        return e[top];
    }

    @Override
    public void push(E element) {
        if (size() == capacity)
            throw new FullStackException("Stack is full");

        e[++top] = element;
    }

    @Override
    public E pop() {
        if (isEmpty())
            throw new EmptyStackException("Stack is empty");

        E tmp = e[top];
        e[top--] = null;
        return tmp;
    }
}
