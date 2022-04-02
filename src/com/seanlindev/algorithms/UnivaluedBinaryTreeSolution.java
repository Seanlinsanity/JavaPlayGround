package com.seanlindev.algorithms;

/*
A binary tree is uni-valued if every node in the tree has the same value.

Given the root of a binary tree, return true if the given tree is uni-valued, or false otherwise.


Example 1:
     1
    / \
   1   1
  / \   \
 1   1   1
Input: root = [1,1,1,1,1,null,1]
Output: true

Example 2:
     2
    / \
   2   2
  / \
 5   2

Input: root = [2,2,2,5,2]
Output: false


Constraints:

The number of nodes in the tree is in the range [1, 100].
0 <= Node.val < 100
 */

import com.seanlindev.utils.TreeNode;

class UnivaluedBinaryTreeSolution {
    public boolean isUnivalTree(TreeNode root) {
        if (root == null) { return  false; }
        final int rootValue = root.val;

        TreeNode[] nodes = new TreeNode[]{root.left, root.right};

        while (nodes.length > 0) {
            TreeNode currentNode = nodes[0];
            if (currentNode != null && currentNode.val != rootValue) {
                return false;
            }

            if (currentNode != null && (currentNode.left != null || currentNode.right != null)) {
                if (currentNode.left == null || currentNode.right == null) {
                    TreeNode[] tempNodes = new TreeNode[nodes.length];
                    System.arraycopy(nodes, 1, tempNodes, 0, nodes.length - 1);
                    tempNodes[nodes.length - 1] = currentNode.right == null ? currentNode.left : currentNode.right;
                    nodes = tempNodes;
                } else {
                    TreeNode[] tempNodes = new TreeNode[nodes.length + 1];
                    System.arraycopy(nodes, 1, tempNodes, 0, nodes.length - 1);
                    tempNodes[nodes.length - 1] = currentNode.left;
                    tempNodes[nodes.length] = currentNode.right;
                    nodes = tempNodes;
                }
            } else {
                TreeNode[] tempNodes = new TreeNode[nodes.length - 1];
                System.arraycopy(nodes, 1, tempNodes, 0, nodes.length - 1);
                nodes = tempNodes;
            }
        }

        return true;
    }
}
