package com.seanlindev.algorithms;

import com.seanlindev.utils.ListNode;

/*
Given the head of a linked list and a value x, partition it such that all nodes less than x come before nodes greater than or equal to x.

You should preserve the original relative order of the nodes in each of the two partitions.



Example 1:


Input: head = [1,4,3,2,5,2], x = 3
Output: [1,2,2,4,3,5]
Example 2:

Input: head = [2,1], x = 2
Output: [1,2]


Constraints:

The number of nodes in the list is in the range [0, 200].
-100 <= Node.val <= 100
-200 <= x <= 200
 */
public class PartitionList {
    public ListNode partition(ListNode head, int x) {
        if (head == null) { return null; }
        ListNode firstHalfHead = null, secondHalfHead = null, firstHalfCurrent = null, secondHalfCurrent = null, current = head;

        while (current != null) { //current=1->1
            if (current.val < x) {
                if (firstHalfHead == null) {
                    firstHalfHead = current; //firstHalfHead=1
                    firstHalfCurrent = firstHalfHead; //firstHalfCurrent=1
                } else {
                    firstHalfCurrent.next = current; //firstHalfCurrent=1->1
                    firstHalfCurrent = current; //firstHalfCurrent=1
                }
            } else {
                if (secondHalfHead == null) {
                    secondHalfHead = current;
                    secondHalfCurrent = secondHalfHead;
                } else {
                    secondHalfCurrent.next = current;
                    secondHalfCurrent = current;
                }
            }
            current = current.next; //current=1->1
        }

        if (firstHalfCurrent == null) {
            return secondHalfHead;
        }

        if (secondHalfCurrent == null) {
            return firstHalfHead;
        }
        firstHalfCurrent.next = secondHalfHead;
        secondHalfCurrent.next = null;
        return firstHalfHead;
    }
}
