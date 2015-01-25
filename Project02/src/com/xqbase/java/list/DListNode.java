package com.xqbase.java.list;

import com.xqbase.java.dict.Entry;

/**
 *  A DListNode is a node in a DList (doubly-linked list).
 *
 *  @author Tony He
 */
public class DListNode {

    /**
     *  item references the item stored in the current node.
     *  prev references the previous node in the DList.
     *  next references the next node in the DList.
     *
     *  DO NOT CHANGE THE FOLLOWING FIELD DECLARATIONS.
     */
    public Entry entry;
    protected DListNode prev;
    protected DListNode next;

    /**
     * Construct a new DListNode instance
     *
     * @param entry data value
     */
    public DListNode(Entry entry) {
        this.entry = entry;
        this.prev = null;
        this.next = null;
    }

    /**
     *  DListNode() constructor.
     *  @param e the entry to store in the node.
     *  @param p the node previous to this node.
     *  @param n the node following this node.
     */
    DListNode(Entry e, DListNode p, DListNode n) {
        entry = e;
        prev = p;
        next = n;
    }
}

