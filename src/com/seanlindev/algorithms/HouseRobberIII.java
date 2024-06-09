package com.seanlindev.algorithms;

import com.seanlindev.utils.TreeNode;

/*
The thief has found himself a new place for his thievery again. There is only one entrance to this area, called root.

Besides the root, each house has one and only one parent house. After a tour, the smart thief realized that all houses in this place form a binary tree. It will automatically contact the police if two directly-linked houses were broken into on the same night.

Given the root of the binary tree, return the maximum amount of money the thief can rob without alerting the police.



Example 1:


Input: root = [3,2,3,null,3,null,1]
Output: 7
Explanation: Maximum amount of money the thief can rob = 3 + 3 + 1 = 7.
Example 2:


Input: root = [3,4,5,1,3,null,1]
Output: 9
Explanation: Maximum amount of money the thief can rob = 4 + 5 = 9.


Constraints:

The number of nodes in the tree is in the range [1, 104].
0 <= Node.val <= 104
 */
public class HouseRobberIII {
    public int rob(TreeNode root) {
        // Map<TreeNode, Integer> cache = new HashMap<>();
        // return postOrderTraverse(root, cache);

        int[] result = postOrderTraverse(root);
        return Math.max(result[0], result[1]);
    }

    int[] postOrderTraverse(TreeNode node) {
        if (node == null) { return new int[]{0, 0}; }

        int withNode = node.val;
        int withoutNode = 0;
        if (node.left != null) {
            int[] leftResult = postOrderTraverse(node.left);
            withNode += leftResult[1];
            withoutNode += Math.max(leftResult[0], leftResult[1]);
        }

        if (node.right != null) {
            int[] rightResult = postOrderTraverse(node.right);
            withNode += rightResult[1];
            withoutNode += Math.max(rightResult[0], rightResult[1]);
        }

        return new int[]{withNode, withoutNode};
    }

    // int postOrderTraverse(TreeNode node, Map<TreeNode, Integer> cache) {
    //     if (node == null) { return 0; }

    //     if (node.left == null && node.right == null) {
    //         return node.val;
    //     }

    //     if (cache.get(node) != null) { return cache.get(node); }

    //     int result1 = node.val;
    //     int result2 = 0;
    //     if (node.left != null) {
    //         result1 += postOrderTraverse(node.left.left, cache) + postOrderTraverse(node.left.right, cache);
    //         result2 += postOrderTraverse(node.left, cache);
    //     }

    //     if (node.right != null) {
    //         result1 += postOrderTraverse(node.right.left, cache) + postOrderTraverse(node.right.right, cache);
    //         result2 += postOrderTraverse(node.right, cache);
    //     }

    //     int result = Math.max(result1, result2);
    //     cache.put(node, result);
    //     return result;
    // }
}
