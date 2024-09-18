package com.seanlindev.algorithms;

import com.seanlindev.utils.ListNode;

/*
Given the head of a linked list, rotate the list to the right by k places.



Example 1:


Input: head = [1,2,3,4,5], k = 2
Output: [4,5,1,2,3]
Example 2:


Input: head = [0,1,2], k = 4
Output: [2,0,1]


Constraints:

The number of nodes in the list is in the range [0, 500].
-100 <= Node.val <= 100
0 <= k <= 2 * 109
 */
public class RotateList {
    public ListNode rotateRight(ListNode head, int k) {
        // null check
        if (head == null) { return null; }

        int length = 0;
        ListNode prev = null; //prev=null
        ListNode current = head; //current=1
        while (current != null) {
            length += 1; //length=1->2
            prev = current; //prev=1->2
            current = current.next;//current=null
        }

        if (length == 1) { return head; }
        int steps = (length - k % length) % length;
        if (steps == 0) { return head; }

        // connect
        prev.next = head;
        current = head;
        prev = null;
        for (int i = 0; i < steps; i++) {
            prev = current;
            current = current.next;
        }

        prev.next = null;
        return current;
    }
}
