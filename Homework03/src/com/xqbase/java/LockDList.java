package com.xqbase.java;

/**
 * A LockDList is a mutable doubly-linked list ADT in
 * which any node can be locked.
 *
 * @author Tony He
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

    /**
     * Since LockDList is subclass of DList, just need to
     * check the lockNode and remove method..
     * @param args
     */
    public static void main(String[] args) {
        LockDList l = new LockDList();
        System.out.println("### TESTING insertFront ###\nEmpty list is " + l);

        l.insertFront(7);
        l.insertFront(8);
        l.insertBack(9);
        l.insertBack(10);

        if (l.size != 4) {
            System.out.println("size is wrong.");
        }
        l.remove(l.back());
        if (((Integer)l.back().item) != 9) {
            System.out.println("remove() is wrong.");
        }
        if (l.size != 3) {
            System.out.println("size is wrong.");
        }

        System.out.println("### TESTING lockNode ###\nList is " + l);
        l.lockNode(l.front().next);
        l.remove(l.front().next);
        if (l.size == 2) {
            System.out.println("size is wrong.");
        }
        if (((Integer)l.front().next.item) != 7) {
            System.out.println("lockNode() is wrong.");
        }
    }
}
