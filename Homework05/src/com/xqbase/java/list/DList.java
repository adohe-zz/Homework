package com.xqbase.java.list;

/* DList.java */

import com.xqbase.java.dict.Entry;

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

    /**
     *  insertFront() inserts an item at the front of this DList.
     *  @param entry is the item to be inserted.
     */
    public void insertFront(Entry entry) {
        DListNode newNode = new DListNode(entry);
        if (isEmpty()) {
            tail = newNode;
        } else {
            head.prev = newNode;
            newNode.next = head;
        }

        head = newNode;
    }

    /**
     *  insertBack() inserts an item at the back of this DList.
     *  @param entry is the item to be inserted.
     */
    public void insertBack(Entry entry) {
        DListNode newNode = new DListNode(entry);
        if (isEmpty()) {
            head = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
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
     *  insertAfter() inserts an item in this DList immediately following "node".
     *  If "node" is null, do nothing.
     *  @param entry the item to be inserted.
     *  @param node the node to insert the item after.
     */
    public void insertAfter(Entry entry, DListNode node) {
        if (node == null)
            return;

        // Create a new node instance
        DListNode newNode = new DListNode(entry);
        if (node == tail) {
            newNode.next = null;
            tail = newNode;
        } else {
            newNode.next = node.next;
            node.next.prev = newNode;
        }
        newNode.prev = node;
        node.next = newNode;
    }

    /**
     *  insertBefore() inserts an item in this DList immediately before "node".
     *  If "node" is null, do nothing.
     *  @param entry the item to be inserted.
     *  @param node the node to insert the item before.
     */
    public void insertBefore(Entry entry, DListNode node) {
        if (node == null) {
            return;
        }

        // Create a new node instance
        DListNode newNode = new DListNode(entry);
        if (node == head) {
            newNode.prev = null;
            head = newNode;
        } else {
            newNode.prev = node.prev;
            node.prev.next = newNode;
        }
        newNode.next = node;
        node.prev = newNode;
    }

    /**
     *  remove() removes "node" from this DList.  If "node" is null, do nothing.
     */
    public void remove(DListNode node) {
        if (node == null)
            return;

        if (node == head) {
            head = node.next;
        } else {
            node.prev.next = node.next;
        }

        if (node == tail) {
            tail = node.prev;
        } else {
            node.next.prev = node.prev;
        }
    }

    /**
     * find() finds the specific entry from the list. If found return this
     * entry node, otherwise return null;
     */
    public DListNode find(Entry entry) {
        DListNode head = front();
        while (head != null) {
            if (head.entry.key().equals(entry.key()))
                return head;
            head = head.next;
        }

        return null;
    }

    /**
     * clear() removes all the node in the list.
     */
    public void clear() {
        DListNode head = front();
        while (head != null) {
            DListNode next = head.next;
            remove(head);
            head = next;
        }
    }

    @Override
    public String toString() {
        String result = "[  ";
        DListNode node = head;
        while (node != null) {
            result += node;
            node = node.next;
        }

        return result + "]";
    }
}
