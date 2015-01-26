package com.xqbase.java.list;

import com.xqbase.java.dict.Entry;

/**
 * A DList is a mutable doubly-linked list ADT.  Its implementation is
 * circularly-linked and employs a sentinel (dummy) node at the head
 * of the list.
 *
 * @author Tony He
 */
public class DList<T> {

    /**
     * head references the sentinel node.
     * size is the number of items in the list.  (The sentinel node does not
     * store an item.)
     */
    protected DListNode<T> head;
    protected int size;

    /* DList invariants:
     *  1)  head != null.
     *  2)  For any DListNode x in a DList, x.next != null.
     *  3)  For any DListNode x in a DList, x.prev != null.
     *  4)  For any DListNode x in a DList, if x.next == y, then y.prev == x.
     *  5)  For any DListNode x in a DList, if x.prev == y, then y.next == x.
     *  6)  size is the number of DListNodes, NOT COUNTING the sentinel,
     *      that can be accessed from the sentinel (head) by a sequence of
     *      "next" references.
    */

    /**
     * newNode() calls the DListNode constructor.  Use this class to allocate
     * new DListNodes rather than calling the DListNode constructor directly.
     * That way, only this method needs to be overridden if a subclass of DList
     * wants to use a different kind of node.
     *
     * @param t    the item to store in the node.
     * @param prev the node previous to this node.
     * @param next the node following this node.
     */
    protected DListNode<T> newNode(T t, DListNode prev, DListNode next) {
        return new DListNode<T>(t, prev, next);
    }

    /**
     * DList() constructor for an empty DList.
     */
    public DList() {
        head = newNode(null, null, null);
        head.next = head;
        head.prev = head;
        size = 0;
    }

    /**
     * isEmpty() returns true if this DList is empty, false otherwise.
     *
     * @return true if this DList is empty, false otherwise.
     * Performance:  runs in O(1) time.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * length() returns the length of this DList.
     *
     * @return the length of this DList.
     * Performance:  runs in O(1) time.
     */
    public int length() {
        return size;
    }

    /**
     * insertFront() inserts an item at the front of this DList.
     *
     * @param t is the item to be inserted.
     *          Performance:  runs in O(1) time.
     */
    public DListNode<T> insertFront(T t) {
        DListNode<T> newNode = newNode(t, head, head.next);
        head.next.prev = newNode;
        head.next = newNode;
        size++;
        return newNode;
    }

    /**
     * insertBack() inserts an item at the back of this DList.
     *
     * @param t is the item to be inserted.
     *          Performance:  runs in O(1) time.
     */
    public void insertBack(T t) {
        DListNode<T> newNode = newNode(t, head.prev, head);
        head.prev.next = newNode;
        head.prev = newNode;
        size++;
    }

    /**
     * front() returns the node at the front of this DList.  If the DList is
     * empty, return null.
     * <p/>
     * Do NOT return the sentinel under any circumstances!
     *
     * @return the node at the front of this DList.
     * Performance:  runs in O(1) time.
     */
    public DListNode<T> front() {
        if (isEmpty())
            return null;

        return head.next;
    }

    /**
     * back() returns the node at the back of this DList.  If the DList is
     * empty, return null.
     * <p/>
     * Do NOT return the sentinel under any circumstances!
     *
     * @return the node at the back of this DList.
     * Performance:  runs in O(1) time.
     */
    public DListNode<T> back() {
        if (isEmpty())
            return null;

        return head.prev;
    }

    /**
     * next() returns the node following "node" in this DList.  If "node" is
     * null, or "node" is the last node in this DList, return null.
     * <p/>
     * Do NOT return the sentinel under any circumstances!
     *
     * @param node the node whose successor is sought.
     * @return the node following "node".
     * Performance:  runs in O(1) time.
     */
    public DListNode<T> next(DListNode<T> node) {
        if (node == null || node.next == head)
            return null;

        return node.next;
    }

    /**
     * prev() returns the node prior to "node" in this DList.  If "node" is
     * null, or "node" is the first node in this DList, return null.
     * <p/>
     * Do NOT return the sentinel under any circumstances!
     *
     * @param node the node whose predecessor is sought.
     * @return the node prior to "node".
     * Performance:  runs in O(1) time.
     */
    public DListNode<T> prev(DListNode<T> node) {
        if (node == null || node.prev == head)
            return null;

        return node.prev;
    }

    /**
     * insertAfter() inserts an item in this DList immediately following "node".
     * If "node" is null, do nothing.
     *
     * @param t    the item to be inserted.
     * @param node the node to insert the item after.
     *             Performance:  runs in O(1) time.
     */
    public void insertAfter(T t, DListNode<T> node) {
        if (node == null)
            return;

        DListNode<T> newNode = newNode(t, node, node.next);
        node.next.prev = newNode;
        node.next = newNode;
        size++;
    }

    /**
     * insertBefore() inserts an item in this DList immediately before "node".
     * If "node" is null, do nothing.
     *
     * @param t    the item to be inserted.
     * @param node the node to insert the item before.
     *             Performance:  runs in O(1) time.
     */
    public void insertBefore(T t, DListNode<T> node) {
        if (node == null)
            return;

        DListNode<T> newNode = newNode(t, node.prev, node);
        node.prev.next = newNode;
        node.prev = newNode;
        size++;
    }

    /**
     * find() finds the specific entry from the list. If found return this
     * entry node, otherwise return null;
     */
    public DListNode<T> find(T entry) {
        DListNode head = front();
        while (head != null) {
            if (head.item.equals(entry))
                return head;
            head = head.next;
        }

        return null;
    }

    /**
     * remove() removes "node" from this DList.  If "node" is null, do nothing.
     * Performance:  runs in O(1) time.
     */
    public void remove(DListNode node) {
        if (node == null || node == head)
            return;

        node.prev.next = node.next;
        node.next.prev = node.prev;
        size--;
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

    /**
     * toString() returns a String representation of this DList.
     * <p/>
     * DO NOT CHANGE THIS METHOD.
     *
     * @return a String representation of this DList.
     * Performance:  runs in O(n) time, where n is the length of the list.
     */
    public String toString() {
        String result = "[  ";
        DListNode current = head.next;
        while (current != head) {
            result = result + current.item.toString() + "  ";
            current = current.next;
        }
        return result + "]";
    }
}