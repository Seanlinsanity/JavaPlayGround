package com.seanlindev.algorithms;

import com.seanlindev.utils.ListNode;
import com.seanlindev.utils.TreeNode;

/*
Given the head of a singly linked list where elements are sorted in ascending order, convert it to a
height-balanced
 binary search tree.



Example 1:


Input: head = [-10,-3,0,5,9]
Output: [0,-3,9,-10,null,5]
Explanation: One possible answer is [0,-3,9,-10,null,5], which represents the shown height balanced BST.
Example 2:

Input: head = []
Output: []


Constraints:

The number of nodes in head is in the range [0, 2 * 104].
-105 <= Node.val <= 105
 */
public class ConvertSortedListToBinarySearchTree {
    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) { return null; }
        if (head.next == null) { return new TreeNode(head.val); }
        if (head.next.next == null) {
            TreeNode root = new TreeNode(head.val);
            root.right = new TreeNode(head.next.val);
            return root;
        }

        ListNode prev = null;
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }

        prev.next = null;
        TreeNode root = new TreeNode(slow.val);
        root.right = sortedListToBST(slow.next);
        root.left = sortedListToBST(head);

        return root;
    }
}
