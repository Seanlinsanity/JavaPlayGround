package com.seanlindev.algorithms;

import com.seanlindev.utils.ListNode;

import java.util.Stack;

public class RemoveNodesFromLinkedList {
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
