package com.seanlindev.algorithms;

import java.util.Stack;

public class RemoveNodesFromLinkedList {
    class ListNode {
        int val;
        ListNode next;
        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }

        ListNode(int val) {
            this.val = val;
            this.next = null;
        }
    }

    public ListNode removeNodes(ListNode head) {
        Stack<ListNode> stack = new Stack<>();
        ListNode current = head;
        while (current != null) {
            while (!stack.isEmpty() && stack.peek().val < current.val) {
                stack.pop();
            }

            if (!stack.isEmpty()) {
                ListNode node = stack.peek();
                node.next = current;
            }

            stack.push(current);
            current = current.next;
        }

        return stack.get(0);
    }
}
