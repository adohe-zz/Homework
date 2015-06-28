package com.xqbase.java.impl;


import com.xqbase.java.Queue;
import com.xqbase.java.exception.EmptyQueueException;

/**
 * A queue implementation based on the generic linked list.
 *
 * @author Tony He
 */
public class NodeQueue<E> implements Queue<E> {

    protected Node<E> head;
    protected Node<E> tail;
    protected int size;

    public NodeQueue() {
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public E font() throws EmptyQueueException {
        if (isEmpty())
            throw new EmptyQueueException("Queue is empty");

        return head.getElement();
    }

    @Override
    public void enqueue(E element) {
        Node<E> node = new Node<E>();
        node.setElement(element);
        node.setNext(null);
        if (size == 0)
            head = node;
        else
            tail.setNext(node);
        tail = node;
        size ++;
    }

    @Override
    public E dequeue() throws EmptyQueueException {
        if (isEmpty())
            throw new EmptyQueueException("Queue is empty");

        E tmp = head.getElement();
        head = head.getNext();
        size --;
        if (size == 0)
            tail = null;
        return tmp;
    }
}
