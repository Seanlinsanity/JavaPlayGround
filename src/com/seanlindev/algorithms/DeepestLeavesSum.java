package com.seanlindev.algorithms;

import com.seanlindev.utils.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/*
Given the root of a binary tree, return the sum of values of its deepest leaves.


Example 1:


Input: root = [1,2,3,4,5,null,6,7,null,null,null,null,8]
Output: 15
Example 2:

Input: root = [6,7,8,2,7,1,3,9,null,1,4,null,null,null,5]
Output: 19


Constraints:

The number of nodes in the tree is in the range [1, 104].
1 <= Node.val <= 100
 */

public class DeepestLeavesSum {
    public int deepestLeavesSum(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList();
        int sum = 0;
        queue.add(root);
        while (queue.size() > 0) {
            int currentSum = 0;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.remove();
                currentSum += node.val;
                if (node.left != null) {
                    queue.add(node.left);
                }

                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            sum = currentSum;
        }

        return sum;
    }
}
