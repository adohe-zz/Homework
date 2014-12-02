package com.xqbase.java;

/* DListNode */

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
    public Run run;
    public DListNode prev;
    public DListNode next;

    /**
     * Construct a new DListNode instance
     * @param run data value
     */
    public DListNode(Run run) {
        this.run = run;
        this.prev = null;
        this.next = null;
    }

    public DListNode(Run run, DListNode prev, DListNode next) {
        this.run = run;
        this.prev = prev;
        this.next = next;
    }

    @Override
    public String toString() {
        return "{Frequency: " + run.frequency + "," + "RGB: " + run.rgb + "}";
    }
}
