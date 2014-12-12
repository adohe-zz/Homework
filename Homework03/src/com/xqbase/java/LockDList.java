package com.xqbase.java;

/**
 * A LockDList is a mutable doubly-linked list ADT in
 * which any node can be locked.
 */
public class LockDList extends DList {

    public LockDList() {
        super();
    }

    @Override
    protected DListNode newNode(Object item, DListNode prev, DListNode next) {
        return new LockDListNode(item, (LockDListNode)prev, (LockDListNode)next);
    }

    @Override
    public void insertFront(Object item) {
        LockDListNode newNode = new LockDListNode(item, (LockDListNode) head, (LockDListNode) head.next);
        head.next.prev = newNode;
        head.next = newNode;
        size++;
    }

    @Override
    public void insertBack(Object item) {
        LockDListNode newNode = new LockDListNode(item, (LockDListNode) head.prev, (LockDListNode) head);
        head.prev.next = newNode;
        head.prev = newNode;
        size++;
    }

    @Override
    public void insertAfter(Object item, DListNode node) {
        if (node == null)
            return;

        LockDListNode newNode = new LockDListNode(item, (LockDListNode) node, (LockDListNode) node.next);
        node.next.prev = newNode;
        node.next = newNode;
        size++;
    }

    @Override
    public void insertBefore(Object item, DListNode node) {
        if (node == null)
            return;

        LockDListNode newNode = new LockDListNode(item, (LockDListNode) node.prev, (LockDListNode) node);
        node.prev.next = newNode;
        node.prev = newNode;
        size++;
    }

    @Override
    public void remove(DListNode node) {
        if (node == null || node == head || ((LockDListNode) node).isLocked())
            return;

        node.prev.next = node.next;
        node.next.prev = node.prev;
        size --;
    }

    /**
     * Lock a node
     */
    public void lockNode(DListNode node) {
        if (node == null)
            return;

        ((LockDListNode) node).setLocked(true);
    }

}
