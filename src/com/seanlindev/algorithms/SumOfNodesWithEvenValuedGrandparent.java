package com.seanlindev.algorithms;

import com.seanlindev.utils.TreeNode;

/*
Given the root of a binary tree, return the sum of values of nodes with an even-valued grandparent. If there are no nodes with an even-valued grandparent, return 0.

A grandparent of a node is the parent of its parent if it exists.



Example 1:


Input: root = [6,7,8,2,7,1,3,9,null,1,4,null,null,null,5]
Output: 18
Explanation: The red nodes are the nodes with even-value grandparent while the blue nodes are the even-value grandparents.
Example 2:


Input: root = [1]
Output: 0

 */
public class SumOfNodesWithEvenValuedGrandparent {
    int sum = 0;
    public int sumEvenGrandparent(TreeNode root) {
        preorderDFS(root, null, null);
        return sum;
    }

    void preorderDFS(TreeNode node, TreeNode parent, TreeNode grandParent) {
        if (node == null) { return; }

        if (grandParent != null && grandParent.val % 2 == 0) {
            sum += node.val;
        }

        preorderDFS(node.left, node, parent);
        preorderDFS(node.right, node, parent);
    }
}
