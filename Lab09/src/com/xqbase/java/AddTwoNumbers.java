package com.xqbase.java;

/**
 * LeetCode02 -- Add Two Numbers
 *
 * @author Tony He
 */
public class AddTwoNumbers {

    public static class ListNode {
        int val;
        ListNode next;
        public ListNode(int val) {
            this.val = val;
        }
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(5);
        /*l1.next = new ListNode(4);
        l1.next.next = new ListNode(3);*/
        ListNode l2 = new ListNode(5);
       /* l2.next = new ListNode(6);
        l2.next.next = new ListNode(4);*/
        ListNode list = addTwoNumbers(l1, l2);
        while (list != null) {
            System.out.print(list.val + " -> ");
            list = list.next;
        }
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode curr = null;
        ListNode head = null;
        boolean carry = false;

        while (l1 != null && l2 != null) {
            int i = l1.val + l2.val;
            if (carry) {
                i += 1;
                carry = false;
            }

            if (i >= 10) {
                carry = true;
                i -= 10;
            }

            if (head == null) {
                head = new ListNode(i);
                curr = head;
            } else {
                curr.next = new ListNode(i);
                curr = curr.next;
            }

            l1 = l1.next;
            l2 = l2.next;
        }

        ListNode list = l1 != null ? l1 : (l2 != null ? l2 : null);
        while (list != null) {
            int i = list.val;
            if (carry) {
                i += 1;
                carry = false;
            }
            if (i >= 10) {
                carry = true;
                i -= 10;
            }
            curr.next = new ListNode(i);
            list = list.next;
            curr = curr.next;
        }

        if (carry) {
            curr.next = new ListNode(1);
        }

        return head;
    }
}