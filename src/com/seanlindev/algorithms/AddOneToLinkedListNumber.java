package com.seanlindev.algorithms;
/*
You are given a linked list where each element in the list is a node and have an integer data. You need to add 1 to the number formed by concatinating all the list node numbers together and return the head of the modified linked list.

Note: The head represents the first element of the given array.

Examples :

Input: LinkedList: 4->5->6
Output: 457

Explanation: 4->5->6 represents 456 and when 1 is added it becomes 457.
Input: LinkedList: 1->2->3
Output: 124

Explanation:  1->2->3 represents 123 and when 1 is added it becomes 124.
Expected Time Complexity: O(n)
Expected Auxiliary Space: O(1)

Constraints:
1 <= len(list) <= 105
0 <= list[i] <= 105
 */


public class AddOneToLinkedListNumber {
    class Node {
        int data;
        Node next;
        Node(int data) {
            this.data = data;
        }
    }
    public Node addOne(Node head) {
        Node reversed = reverse(head);
        addOneUtil(reversed);
        return reverse(reversed);
    }

    Node reverse(Node head) {
        Node prev = null;
        Node current = head;
        while (current != null) {
            Node temp = current.next;
            current.next = prev;
            prev = current;
            current = temp;
        }

        return prev;
    }

    /* Adds one to a linked lists and return the head
    node of resultant list */
    Node addOneUtil(Node head)
    {
        // res is head node of the resultant list
        Node res = head;
        Node temp = null, prev = null;

        int carry = 1, sum;

        while (head != null) // while both lists exist
        {
            // Calculate value of next digit in resultant
            // list. The next digit is sum of following
            // things (i) Carry (ii) Next digit of head list
            // (if there is a next digit)
            sum = carry + head.data;

            // update carry for next calculation
            carry = (sum >= 10) ? 1 : 0;

            // update sum if it is greater than 10
            sum = sum % 10;

            // Create a new node with sum as data
            head.data = sum;

            // Move head and second pointers to next nodes
            temp = head;
            head = head.next;
        }

        // if some carry is still there, add a new node to
        // result list.
        if (carry > 0)
            temp.next = new Node(carry);

        // return head of the resultant list
        return res;
    }
}
