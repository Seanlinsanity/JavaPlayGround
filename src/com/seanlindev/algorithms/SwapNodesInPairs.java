package com.seanlindev.algorithms;

import com.seanlindev.utils.ListNode;

/*
Given a linked list, swap every two adjacent nodes and return its head. You must solve the problem without modifying the values in the list's nodes (i.e., only nodes themselves may be changed.)



Example 1:

Input: head = [1,2,3,4]

Output: [2,1,4,3]

Explanation:



Example 2:

Input: head = []

Output: []

Example 3:

Input: head = [1]

Output: [1]

Example 4:

Input: head = [1,2,3]

Output: [2,1,3]



Constraints:

The number of nodes in the list is in the range [0, 100].
0 <= Node.val <= 100
 */
public class SwapNodesInPairs {
    public ListNode swapPairs(ListNode head) {
        if (head == null) { return null; }

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;
        ListNode current = head;
        while (current != null && current.next != null) {
            ListNode next = current.next;
            ListNode temp = next.next;
            prev.next = next;
            next.next = current;
            current.next = temp;
            prev = current;
            current = current.next;
        }

        return dummy.next;
    }

}
