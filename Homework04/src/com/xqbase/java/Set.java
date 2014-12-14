package com.xqbase.java;

import com.xqbase.java.list.DList;
import com.xqbase.java.list.InvalidNodeException;
import com.xqbase.java.list.List;
import com.xqbase.java.list.ListNode;

/**
 *  A Set is a collection of Comparable elements stored in sorted order.
 *  Duplicate elements are not permitted in a Set.
 **/
public class Set {

    private List list;
    private int size;
    /**
     * Set ADT invariants:
     *  1)  The Set's elements must be precisely the elements of the List.
     *  2)  The List must always contain Comparable elements, and those elements
     *      must always be sorted in ascending order.
     *  3)  No two elements in the List may be equal according to compareTo().
     **/

    /**
     *  Constructs an empty Set.
     *
     *  Performance:  runs in O(1) time.
     **/
    public Set() {
        list = new DList();
        size = 0;
    }

    /**
     *  cardinality() returns the number of elements in this Set.
     *
     *  Performance:  runs in O(1) time.
     **/
    public int cardinality() {
        return size;
    }

    /**
     *  insert() inserts a Comparable element into this Set.
     *
     *  Sets are maintained in sorted order.  The ordering is specified by the
     *  compareTo() method of the java.lang.Comparable interface.
     *
     *  Performance:  runs in O(this.cardinality()) time.
     **/
    public void insert(Comparable c) {
        if (list.isEmpty()) {
            list.insertFront(c);
            size ++;
        } else {
            ListNode n = list.front();
            try {
                for (; n.isValidNode(); n = n.next()) {
                    int r = c.compareTo(n.item());
                    if (r == 0) {
                        return;
                    }
                    if (r < 0) {
                        if (!n.prev().isValidNode() || (n.prev().isValidNode() && c.compareTo(n.prev().item()) != 0)) {
                            n.insertBefore(c);
                            size ++;
                            break;
                        }
                    }
                }
                if (!n.isValidNode()) {
                    list.insertBack(c);
                    size ++;
                }
            } catch (InvalidNodeException e) {
                System.out.println("set contains invalid node!!!");
            }
        }
    }

    /**
     *  union() modifies this Set so that it contains all the elements it
     *  started with, plus all the elements of s.  The Set s is NOT modified.
     *  Make sure that duplicate elements are not created.
     *
     *  Performance:  Must run in O(this.cardinality() + s.cardinality()) time.
     *
     *  Your implementation should NOT copy elements of s or "this", though it
     *  will copy _references_ to the elements of s.  Your implementation will
     *  create new _nodes_ for the elements of s that are added to "this", but
     *  you should reuse the nodes that are already part of "this".
     *
     *  DO NOT MODIFY THE SET s.
     *  DO NOT ATTEMPT TO COPY ELEMENTS; just copy _references_ to them.
     **/
    public void union(Set s) {
        ListNode sHead = s.list.front();
        ListNode head = list.front();
        while (head.isValidNode() && sHead.isValidNode()) {
            try {
                if (((Comparable) head.item()).compareTo(sHead.item()) < 0) {
                    head = head.next();
                } else if (((Comparable) head.item()).compareTo(sHead.item()) == 0) {
                    sHead = sHead.next();
                    head = head.next();
                } else {
                    head.insertBefore(sHead.item());
                    size ++;
                    sHead = sHead.next();
                }
            } catch (InvalidNodeException e) {
                System.out.println("union should not contains invalid node!!!");
            }
        }

        while (sHead.isValidNode()) {
            try {
                list.insertBack(sHead.item());
                size ++;
                sHead = sHead.next();
            } catch (InvalidNodeException e) {
                System.out.println("union should not contains invalid node!!!");
            }
        }
    }

    /**
     *  intersect() modifies this Set so that it contains the intersection of
     *  its own elements and the elements of s.  The Set s is NOT modified.
     *
     *  Performance:  Must run in O(this.cardinality() + s.cardinality()) time.
     *
     *  Do not construct any new ListNodes during the execution of intersect.
     *  Reuse the nodes of "this" that will be in the intersection.
     *
     *  DO NOT MODIFY THE SET s.
     *  DO NOT CONSTRUCT ANY NEW NODES.
     *  DO NOT ATTEMPT TO COPY ELEMENTS.
     **/
    public void intersect(Set s) {
        ListNode sHead = s.list.front();
        ListNode head = list.front();

        while (head.isValidNode() && sHead.isValidNode()) {
            try {
                if (((Comparable) head.item()).compareTo(sHead.item()) < 0) {
                    ListNode next = head.next();
                    head.remove();
                    head = next;
                    size --;
                } else if (((Comparable) head.item()).compareTo(sHead.item()) == 0) {
                    head = head.next();
                    sHead = sHead.next();
                } else {
                    sHead = sHead.next();
                }
            } catch (InvalidNodeException e) {
                System.out.println("intersect contains invalid node!!!");
            }
        }

        while (head.isValidNode()) {
            try {
                ListNode node = head.next();
                head.remove();
                head = node;
                size --;
            } catch (InvalidNodeException e) {
                System.out.println("intersect contains invalid node!!!");
            }
        }
    }

    /**
     *  toString() returns a String representation of this Set.  The String must
     *  have the following format:
     *    {  } for an empty Set.  No spaces before "{" or after "}"; two spaces
     *            between them.
     *    {  1  2  3  } for a Set of three Integer elements.  No spaces before
     *            "{" or after "}"; two spaces before and after each element.
     *            Elements are printed with their own toString method, whatever
     *            that may be.  The elements must appear in sorted order, from
     *            lowest to highest according to the compareTo() method.
     *
     *  WARNING:  THE AUTOGRADER EXPECTS YOU TO PRINT SETS IN _EXACTLY_ THIS
     *            FORMAT, RIGHT UP TO THE TWO SPACES BETWEEN ELEMENTS.  ANY
     *            DEVIATIONS WILL LOSE POINTS.
     **/
    public String toString() {
        // Replace the following line with your solution.
        return list.toString();
    }

    public static void main(String[] argv) {
        Set s = new Set();
        s.insert(new Integer(3));
        s.insert(new Integer(4));
        s.insert(new Integer(3));
        System.out.println("Set s = " + s);

        Set s2 = new Set();
        s2.insert(new Integer(4));
        s2.insert(new Integer(5));
        s2.insert(new Integer(5));
        System.out.println("Set s2 = " + s2);

        Set s3 = new Set();
        s3.insert(new Integer(5));
        s3.insert(new Integer(3));
        s3.insert(new Integer(8));
        System.out.println("Set s3 = " + s3);

        s.union(s2);
        System.out.println("After s.union(s2), s = " + s);

        testUnion();

        s.intersect(s3);
        System.out.println("After s.intersect(s3), s = " + s);

        System.out.println("s.cardinality() = " + s.cardinality());

        testIntersect();
    }

    private static void testUnion() {
        Set s = new Set();
        s.insert(new Integer(3));
        s.insert(new Integer(4));
        s.insert(new Integer(6));
        s.insert(new Integer(7));
        System.out.println("Set s = " + s);

        Set s1 = new Set();
        s1.insert(new Integer(3));
        s1.insert(new Integer(4));
        s1.insert(new Integer(6));
        s1.insert(new Integer(7));

        s.union(s1);
        System.out.println("After s.union(s1), s = " + s);

        Set s2 = new Set();
        s2.insert(new Integer(1));
        s2.insert(new Integer(2));
        s2.insert(new Integer(4));

        s.union(s2);
        System.out.println("After s.union(s2), s = " + s);

        Set s3 = new Set();
        s3.insert(new Integer(5));
        s3.insert(new Integer(7));
        s3.insert(new Integer(9));

        s.union(s3);
        System.out.println("After s.union(s3), s = " + s);

        Set s4 = new Set();
        s4.insert(new Integer(4));
        s4.insert(new Integer(5));

        s.union(s4);
        System.out.println("After s.union(s4), s = " + s);

        Set s5 = new Set();
        s5.insert(new Integer(1));
        s5.insert(new Integer(2));
        s5.insert(new Integer(3));
        s5.insert(new Integer(4));
        s5.insert(new Integer(5));
        s5.insert(new Integer(6));
        s5.insert(new Integer(7));
        s5.insert(new Integer(8));
        s5.insert(new Integer(9));

        s.union(s5);
        System.out.println("After s.union(s5), s = " + s);
    }

    private static void testIntersect() {
        Set s = new Set();
        s.insert(new Integer(5));
        s.insert(new Integer(6));
        s.insert(new Integer(7));
        s.insert(new Integer(4));
        s.insert(new Integer(1));

        System.out.println("s = " + s);

        Set s1 = new Set();
        s1.insert(new Integer(1));
        s1.insert(new Integer(2));
        s1.insert(new Integer(3));
        s1.insert(new Integer(4));
        s1.insert(new Integer(5));
        s1.insert(new Integer(6));
        s1.insert(new Integer(7));
        s1.insert(new Integer(8));
        s1.insert(new Integer(9));

        s.intersect(s1);
        System.out.println("After s.intersect(s1), s = " + s);
        System.out.println("s.cardinality() = " + s.cardinality());

        Set s2 = new Set();
        s2.insert(new Integer(2));
        s2.insert(new Integer(4));
        s2.insert(new Integer(6));
        s2.insert(new Integer(5));
        s2.insert(new Integer(10));

        s.intersect(s2);
        System.out.println("After s.intersect(s2), s = " + s);
        System.out.println("s.cardinality() = " + s.cardinality());

        Set s3 = new Set();
        s3.insert(new Integer(2));
        s3.insert(new Integer(1));
        s3.insert(new Integer(3));
        s3.insert(new Integer(4));
        s3.insert(new Integer(6));

        s.intersect(s3);
        System.out.println("After s.intersect(s3), s = " + s);
        System.out.println("s.cardinality() = " + s.cardinality());

        Set s4 = new Set();
        s.insert(new Integer(5));

        s.intersect(s4);
        System.out.println("After s.intersect(s4), s = " + s);
        System.out.println("s.cardinality() = " + s.cardinality());

        s.insert(new Integer(1));
        s.insert(new Integer(4));

        System.out.println("Reconstruct s = " + s);
        System.out.println("Reconstruct s.cardinality() = " + s.cardinality());

        Set s5 = new Set();

        s.intersect(s5);
        System.out.println("After s.intersect(s5), s = " + s);
        System.out.println("s.cardinality() = " + s.cardinality());
    }
}
