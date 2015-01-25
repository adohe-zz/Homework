package com.xqbase.java.list;

import com.xqbase.java.dict.Entry;

/**
 *  A DList is a mutable doubly-linked list ADT.  Its implementation is
 *  circularly-linked and employs a sentinel (dummy) node at the head
 *  of the list.
 *
 * @author Tony He
 */
public class DList {

    /**
     *  head references the sentinel node.
     *  size is the number of items in the list.  (The sentinel node does not
     *       store an item.)
     */
    protected DListNode head;
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
     *  newNode() calls the DListNode constructor.  Use this class to allocate
     *  new DListNodes rather than calling the DListNode constructor directly.
     *  That way, only this method needs to be overridden if a subclass of DList
     *  wants to use a different kind of node.
     *  @param entry the entry to store in the node.
     *  @param prev the node previous to this node.
     *  @param next the node following this node.
     */
    protected DListNode newNode(Entry entry, DListNode prev, DListNode next) {
        return new DListNode(entry, prev, next);
    }

    /**
     *  DList() constructor for an empty DList.
     */
    public DList() {
        head = newNode(null, null, null);
        head.next = head;
        head.prev = head;
        size = 0;
    }

    /**
     *  isEmpty() returns true if this DList is empty, false otherwise.
     *  @return true if this DList is empty, false otherwise.
     *  Performance:  runs in O(1) time.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     *  length() returns the length of this DList.
     *  @return the length of this DList.
     *  Performance:  runs in O(1) time.
     */
    public int length() {
        return size;
    }

    /**
     *  insertFront() inserts an item at the front of this DList.
     *  @param entry is the item to be inserted.
     *  Performance:  runs in O(1) time.
     */
    public void insertFront(Entry entry) {
        DListNode newNode = newNode(entry, head, head.next);
        head.next.prev = newNode;
        head.next = newNode;
        size ++;
    }

    /**
     *  insertBack() inserts an item at the back of this DList.
     *  @param entry is the item to be inserted.
     *  Performance:  runs in O(1) time.
     */
    public void insertBack(Entry entry) {
        DListNode newNode = newNode(entry, head.prev, head);
        head.prev.next = newNode;
        head.prev = newNode;
        size++;
    }

    /**
     *  front() returns the node at the front of this DList.  If the DList is
     *  empty, return null.
     *
     *  Do NOT return the sentinel under any circumstances!
     *
     *  @return the node at the front of this DList.
     *  Performance:  runs in O(1) time.
     */
    public DListNode front() {
        if (isEmpty())
            return null;

        return head.next;
    }

    /**
     *  back() returns the node at the back of this DList.  If the DList is
     *  empty, return null.
     *
     *  Do NOT return the sentinel under any circumstances!
     *
     *  @return the node at the back of this DList.
     *  Performance:  runs in O(1) time.
     */
    public DListNode back() {
        if (isEmpty())
            return null;

        return head.prev;
    }

    /**
     *  next() returns the node following "node" in this DList.  If "node" is
     *  null, or "node" is the last node in this DList, return null.
     *
     *  Do NOT return the sentinel under any circumstances!
     *
     *  @param node the node whose successor is sought.
     *  @return the node following "node".
     *  Performance:  runs in O(1) time.
     */
    public DListNode next(DListNode node) {
        if (node == null || node.next == head)
            return null;

        return node.next;
    }

    /**
     *  prev() returns the node prior to "node" in this DList.  If "node" is
     *  null, or "node" is the first node in this DList, return null.
     *
     *  Do NOT return the sentinel under any circumstances!
     *
     *  @param node the node whose predecessor is sought.
     *  @return the node prior to "node".
     *  Performance:  runs in O(1) time.
     */
    public DListNode prev(DListNode node) {
        if (node == null || node.prev == head)
            return null;

        return node.prev;
    }

    /**
     *  insertAfter() inserts an item in this DList immediately following "node".
     *  If "node" is null, do nothing.
     *  @param entry the item to be inserted.
     *  @param node the node to insert the item after.
     *  Performance:  runs in O(1) time.
     */
    public void insertAfter(Entry entry, DListNode node) {
        if (node == null)
            return;

        DListNode newNode = newNode(entry, node, node.next);
        node.next.prev = newNode;
        node.next = newNode;
        size++;
    }

    /**
     *  insertBefore() inserts an item in this DList immediately before "node".
     *  If "node" is null, do nothing.
     *  @param entry the item to be inserted.
     *  @param node the node to insert the item before.
     *  Performance:  runs in O(1) time.
     */
    public void insertBefore(Entry entry, DListNode node) {
        if (node == null)
            return;

        DListNode newNode = newNode(entry, node.prev, node);
        node.prev.next = newNode;
        node.prev = newNode;
        size++;
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
     *  remove() removes "node" from this DList.  If "node" is null, do nothing.
     *  Performance:  runs in O(1) time.
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
     *  toString() returns a String representation of this DList.
     *
     *  DO NOT CHANGE THIS METHOD.
     *
     *  @return a String representation of this DList.
     *  Performance:  runs in O(n) time, where n is the length of the list.
     */
    public String toString() {
        String result = "[  ";
        DListNode current = head.next;
        while (current != head) {
            result = result + current.entry.value().toString() + "  ";
            current = current.next;
        }
        return result + "]";
    }

    public static void main(String[] args) {
        DList l = new DList();
        System.out.println("### TESTING insertFront ###\nEmpty list is " + l);

        l.insertFront(new Entry(9, 9));
        System.out.println("\nInserting 9 at front.\nList with 9 is " + l);
        if (((Integer)l.head.next.entry.value()) != 9) {
            System.out.println("head.next.item is wrong.");
        }
        if (((Integer)l.front().entry.value()) != 9) {
            System.out.println("l.front().item is wrong.");
        }
        if (l.head.next.prev != l.head) {
            System.out.println("head.next.prev is wrong.");
        }
        if (((Integer)l.head.prev.entry.value()) != 9) {
            System.out.println("head.prev.item is wrong.");
        }
        if (l.head.prev.next != l.head) {
            System.out.println("head.prev.next is wrong.");
        }
        if (l.size != 1) {
            System.out.println("size is wrong.");
        }

        l.insertFront(new Entry(8, 8));
        System.out.println("\nInserting 8 at front.\nList with 8 and 9 is " + l);
        if (((Integer)l.head.next.entry.value()) != 8) {
            System.out.println("head.next.item is wrong.");
        }
        if (((Integer)l.front().entry.value()) != 8) {
            System.out.println("l.font().item is wrong.");
        }
        if (l.head.next.prev != l.head) {
            System.out.println("head.next.prev is wrong.");
        }
        if (((Integer)l.head.prev.entry.value()) != 9) {
            System.out.println("head.prev.item is wrong.");
        }
        if (l.head.prev.next != l.head) {
            System.out.println("head.prev.next is wrong.");
        }
        if (l.head.next.next != l.head.prev) {
            System.out.println("l.head.next.next != l.head.prev.");
        }
        if (l.head.prev.prev != l.head.next) {
            System.out.println("l.head.prev.prev != l.head.next.");
        }
        if (l.size != 2) {
            System.out.println("size is wrong.");
        }



        l.insertBack(new Entry(7, 7));
        System.out.println("\nInserting 7 at back.\nList with 8, 9 and 7 is " + l);
        if (((Integer)l.back().entry.value()) != 7) {
            System.out.println("list.back().item is wrong");
        }
        if (l.size != 3) {
            System.out.println("size is wrong.");
        }

        l.insertBefore(new Entry(6, 6), l.back());
        System.out.println("\nInserting 6 before back node.\nList with 8, 9, 7 and 6 is " + l);
        if (((Integer)l.back().prev.entry.value()) != 6) {
            System.out.println("insertAfter() is wrong.");
        }
        if (l.size != 4) {
            System.out.println("size is wrong.");
        }

        l.insertAfter(new Entry(5, 5), l.front());
        System.out.println("\nInserting 5 after front node.\nList with 8, 9, 7, 6 and 5 is " + l);
        if (((Integer)l.front().next.entry.value()) != 5) {
            System.out.println("insertBefore() is wrong.");
        }
        if (l.size != 5) {
            System.out.println("size is wrong.");
        }

        if (((Integer)l.prev(l.back()).entry.value()) != 6) {
            System.out.println("prev() is wrong.");
        }
        if (((Integer)l.next(l.front()).entry.value()) != 5) {
            System.out.println("next() is wrong.");
        }

        l.remove(l.back());
        if (((Integer)l.back().entry.value()) == 7) {
            System.out.println("remove() is wrong.");
        }
        if (l.size != 4) {
            System.out.println("size is wrong.");
        }

        l.remove(l.front());
        if (((Integer)l.front().entry.value()) != 5) {
            System.out.println("remove() is wrong.");
        }
        if (l.size != 3) {
            System.out.println("size is wrong.");
        }
    }
}