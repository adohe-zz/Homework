package com.xqbase.java;

/* DList.java */

/**
 *  The DList class is a doubly-linked implementation of the linked list
 *  abstraction.  DLists are mutable data structures, which can grow at either
 *  end.
 *
 *  @author Tony He
 **/
public class DList {

    // List fields
    private DListNode head;
    private DListNode tail;
    private int size;

    public DList() {
        head = tail = null;
        size = 0;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public int getLength() {
        return this.size;
    }

    protected DListNode createNode(Run run, DListNode prev, DListNode next) {
        return new DListNode(run, prev, next);
    }

    /**
     *  insertFront() inserts an item at the front of this DList.
     *  @param run is the item to be inserted.
     */
    public void insertFront(Run run) {
        if (head == null) {
            head = new DListNode(run, null, null);
            tail = head;
        } else {
            head = new DListNode(run, null, head);
        }
        
        size ++;
    }

    /**
     *  insertBack() inserts an item at the back of this DList.
     *  @param run is the item to be inserted.
     */
    public void insertBack(Run run) {
        if (head == null) {
            head = new DListNode(run, null, null);
            tail = head;
        } else {
            tail = new DListNode(run, tail, null);
        }

        size ++;
    }

    /**
     *  front() returns the node at the front of this DList.  If the DList is
     *  empty, return null.
     *
     *  @return the node at the front of this DList.
     */
    public DListNode front() {
        if (isEmpty())
            return null;

        return head;
    }

    /**
     *  back() returns the node at the back of this DList.  If the DList is
     *  empty, return null.
     *
     *  @return the node at the back of this DList.
     */
    public DListNode back() {
        if (isEmpty())
            return null;

        return tail;
    }

    /**
     *  next() returns the node following "node" in this DList.  If "node" is
     *  null, or "node" is the last node in this DList, return null.
     *
     *  @param node the node whose successor is sought.
     *  @return the node following "node".
     */
    public DListNode next(DListNode node) {
        if (node == null)
            return null;

        return node.getNext();
    }

    /**
     *  prev() returns the node prior to "node" in this DList.  If "node" is
     *  null, or "node" is the first node in this DList, return null.
     *
     *  @param node the node whose predecessor is sought.
     *  @return the node prior to "node".
     */
    public DListNode prev(DListNode node) {
        if (node == null)
            return null;

        return node.getPrev();
    }

    /**
     *  insertAfter() inserts an item in this DList immediately following "node".
     *  If "node" is null, do nothing.
     *  @param run the item to be inserted.
     *  @param node the node to insert the item after.
     */
    public void insertAfter(Run run, DListNode node) {
        if (node == null)
            return;

        // Create a new node instance
        DListNode newNode = createNode(run, node, node.getNext());
        if (node.getNext() == null)
            tail = newNode;
        node.setNext(newNode);
        size ++;
    }

    /**
     *  insertBefore() inserts an item in this DList immediately before "node".
     *  If "node" is null, do nothing.
     *  @param run the item to be inserted.
     *  @param node the node to insert the item before.
     */
    public void insertBefore(Run run, DListNode node) {
        if (node == null) {
            return;
        }

        // Create a new node instance
        DListNode newNode = createNode(run, node.getPrev(), node);
        if (node.getPrev() == null)
            head = newNode;
        node.setPrev(newNode);
        size ++;
    }

    /**
     *  remove() removes "node" from this DList.  If "node" is null, do nothing.
     */
    public void remove(DListNode node) {
        if (node == null)
            return;

        if (node.getPrev() == null) {
            head = node.getNext();
        } else if(node.getNext() == null) {
            tail = node.getPrev();
        } else {
            node.getPrev().setNext(node.getNext());
        }
        size --;
    }

    /***
     * Helper function for RunLengthEncoding to update the frequency
     */
    public void updateBack() {
        if (tail != null) {
            int f = tail.getRun().getFrequency() + 1;
            tail.getRun().setFrequency(f);
        }
    }

    @Override
    public String toString() {
        String result = "[  ";
        DListNode node = head;
        while (node != tail.getNext()) {
            result = result + node.getRun() + " ";
            node = node.getNext();
        }

        return result + "]";
    }
}
