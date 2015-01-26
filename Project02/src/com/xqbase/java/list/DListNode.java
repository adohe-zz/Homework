package com.xqbase.java.list;

import com.xqbase.java.dict.Entry;

/**
 * A DListNode is a node in a DList (doubly-linked list).
 *
 * @author Tony He
 */
public class DListNode<T> {

    /**
     * item references the item stored in the current node.
     * prev references the previous node in the DList.
     * next references the next node in the DList.
     */
    public T item;
    public DListNode prev;
    public DListNode next;

    /**
     * Construct a new DListNode instance
     *
     * @param item data value
     */
    public DListNode(T item) {
        this.item = item;
        this.prev = null;
        this.next = null;
    }

    /**
     * DListNode() constructor.
     *
     * @param t the entry to store in the node.
     * @param p the node previous to this node.
     * @param n the node following this node.
     */
    DListNode(T t, DListNode p, DListNode n) {
        item = t;
        prev = p;
        next = n;
    }
}

