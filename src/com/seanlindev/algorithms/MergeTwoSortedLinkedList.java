package com.seanlindev.algorithms;

import com.seanlindev.utils.ListNode;

public class MergeTwoSortedLinkedList {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode root = new ListNode(0, null);
        ListNode current = root;
        ListNode node1 = list1;
        ListNode node2 = list2;
        while (node1 != null && node2 != null) {
            if (node1.val < node2.val) {
                current.next = new ListNode(node1.val, null);
                node1 = node1.next;
            } else {
                current.next = new ListNode(node2.val, null);
                node2 = node2.next;
            }
            current = current.next;
        }

        if (node1 != null) {
            current.next = node1;
        }

        if (node2 != null) {
            current.next = node2;
        }

        return root.next;
    }
}
