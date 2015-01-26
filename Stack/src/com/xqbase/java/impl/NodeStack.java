package com.xqbase.java.impl;

import com.xqbase.java.Stack;
import com.xqbase.java.exception.EmptyStackException;

/**
 * Implementation of the stack ADT using the singly linked list.
 *
 * @author Tony He
 */
public class NodeStack<E> implements Stack<E> {

    private Node<E> top; // the top of the stack
    private int size;

    public NodeStack() {
        top = null;
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return top == null;
    }

    @Override
    public E top() throws EmptyStackException {
        if (isEmpty())
            throw new EmptyStackException("Stack is empty");

        return top.getElement();
    }

    @Override
    public void push(E element) {
        Node<E> node = new Node<E>(element, top);
        top = node;
        size ++;
    }

    @Override
    public E pop() {
        if (isEmpty())
            throw new EmptyStackException("Stack is empty");

        E tmp = top.getElement();
        top = top.getNext();
        size --;
        return tmp;
    }
}
