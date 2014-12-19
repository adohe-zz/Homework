package com.xqbase.java.list;

/* DListNode */

import com.xqbase.java.dict.Entry;

/**
 *  DListNode is a class used internally by the DList class.  An DList object
 *  is a doubly-linked list, and an DListNode is a node of a doubly-linked
 *  list.  Each DListNode has three references:  one to an object, one to
 *  the next node in the list, and one to the prev node in the list.
 *
 *  @author Tony He
 */
public class DListNode {

    // Node fields
    public Entry entry;
    public DListNode prev;
    public DListNode next;

    /**
     * Construct a new DListNode instance
     * @param entry data value
     */
    public DListNode(Entry entry) {
        this.entry = entry;
        this.prev = null;
        this.next = null;
    }

    public DListNode(Entry entry, DListNode prev, DListNode next) {
        this.entry = entry;
        this.prev = prev;
        this.next = next;
    }
}
