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

    public DList() {
        head = tail = null;
    }

    public boolean isEmpty() {
        return this.head == null;
    }


    protected DListNode createNode(Run run, DListNode prev, DListNode next) {
        return new DListNode(run, prev, next);
    }

    /**
     *  insertFront() inserts an item at the front of this DList.
     *  @param run is the item to be inserted.
     */
    public void insertFront(Run run) {
        DListNode newNode = new DListNode(run);
        if (isEmpty()) {
            tail = newNode;
        } else {
            head.setPrev(newNode);
            newNode.setNext(head);
        }

        head = newNode;
    }

    /**
     *  insertBack() inserts an item at the back of this DList.
     *  @param run is the item to be inserted.
     */
    public void insertBack(Run run) {
        DListNode newNode = new DListNode(run);
        if (isEmpty()) {
            head = newNode;
        } else {
            tail.setNext(newNode);
            newNode.setPrev(tail);
        }

        tail = newNode;
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
        DListNode newNode = new DListNode(run);
        if (node == tail) {
            newNode.setNext(null);
            tail = newNode;
        } else {
            newNode.setNext(node.getNext());
            node.getNext().setPrev(newNode);
        }
        newNode.setPrev(node);
        node.setNext(newNode);
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
        DListNode newNode = new DListNode(run);
        if (node == head) {
            newNode.setPrev(null);
            head = newNode;
        } else {
            newNode.setPrev(node.getPrev());
            node.getPrev().setNext(newNode);
        }
        newNode.setNext(node);
        node.setPrev(newNode);
    }

    /**
     *  remove() removes "node" from this DList.  If "node" is null, do nothing.
     */
    public void remove(DListNode node) {
        if (node == null)
            return;

        if (node == head) {
            head = node.getNext();
        } else {
            node.getPrev().setNext(node.getNext());
        }

        if (node == tail) {
            tail = node.getPrev();
        } else {
            node.getNext().setPrev(node.getPrev());
        }
    }

    @Override
    public String toString() {
        String result = "[  ";
        DListNode node = head;
        while (node != null) {
            result += node;
            node = node.getNext();
        }

        return result + "]";
    }
}
