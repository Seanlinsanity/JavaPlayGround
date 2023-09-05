package com.seanlindev.algorithms;

import com.seanlindev.utils.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/*
Given the root of a binary tree, return the maximum width of the given tree.

The maximum width of a tree is the maximum width among all levels.

The width of one level is defined as the length between the end-nodes (the leftmost and rightmost non-null nodes), where the null nodes between the end-nodes that would be present in a complete binary tree extending down to that level are also counted into the length calculation.

It is guaranteed that the answer will in the range of a 32-bit signed integer.



Example 1:


Input: root = [1,3,2,5,3,null,9]
Output: 4
Explanation: The maximum width exists in the third level with length 4 (5,3,null,9).
Example 2:


Input: root = [1,3,2,5,null,null,9,6,null,7]
Output: 7
Explanation: The maximum width exists in the fourth level with length 7 (6,null,null,null,null,null,7).
Example 3:


Input: root = [1,3,2,5]
Output: 2
Explanation: The maximum width exists in the second level with length 2 (3,2).


Constraints:

The number of nodes in the tree is in the range [1, 3000].
-100 <= Node.val <= 100
 */
public class MaxWidthOfBinaryTree {
    class Node {
        TreeNode treeNode;
        int index;

        Node(TreeNode _treeNode, int _index) {
            this.treeNode = _treeNode;
            this.index = _index;
        }
    }
    public int widthOfBinaryTree(TreeNode root) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(root, 1));
        int result = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            int leftMostNum = 0;
            for (int i = 0; i < size; i++) {
                Node node = queue.remove();
                if (i == 0) {
                    leftMostNum = node.index;
                }

                result = Math.max(result, node.index - leftMostNum + 1);
                TreeNode left = node.treeNode.left;
                if (left != null) {
                    queue.add(new Node(left, node.index * 2));
                }

                TreeNode right = node.treeNode.right;
                if (right != null) {
                    queue.add(new Node(right, node.index * 2 + 1));
                }
            }
        }

        return result;
    }
}
