package com.seanlindev.algorithms;

public class DeleteNodeInLinkedList {
    public class ListNode {
          int val;
          ListNode next;
          ListNode(int x) { val = x; }
     }

     public void deleteNode(ListNode node) {
        if (node == null) { return; }
        ListNode nextNode = node.next;
        node.val = nextNode.val;
        node.next = nextNode.next;
    }
}
