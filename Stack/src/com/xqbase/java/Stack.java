package com.xqbase.java;

import com.xqbase.java.exception.EmptyStackException;

/**
 * Interface for a stack: a collection of objects that are
 * inserted and removed according to the last-in first-out
 * principle. This interface includes the main methods of
 * {@link java.util.Stack}
 *
 * @author Tony He
 */
public interface Stack<E> {

    /**
     * Returns the number of elements in the stack.
     */
    public int size();

    /**
     * Returns whether the stack is empty.
     */
    public boolean isEmpty();

    /**
     * Inspects the element at the top of the stack.
     */
    public E top() throws EmptyStackException;

    /**
     * Inserts an element at the top of the stack.
     */
    public void push(E element);

    /**
     * Removes the top element from the stack.
     */
    public E pop();
}
