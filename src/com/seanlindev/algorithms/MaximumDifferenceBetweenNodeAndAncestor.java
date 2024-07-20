package com.seanlindev.algorithms;

import com.seanlindev.utils.TreeNode;

public class MaximumDifferenceBetweenNodeAndAncestor {
    int result = 0;
    public int maxAncestorDiff(TreeNode root) {
        preorderTraversal(root, root.val, root.val);
        return result;
//        return dfs(root, root.val, root.val);
    }

    void preorderTraversal(TreeNode node, int min, int max) {
        if (node == null) { return; }

        int val = node.val;
        int maxDiff = Math.max(Math.abs(val - min), Math.abs(val - max));
        result = Math.max(result, maxDiff);
        min = Math.min(min, val);
        max = Math.max(max, val);
        preorderTraversal(node.left, min, max);
        preorderTraversal(node.right, min, max);
    }

    // Helper function to perform DFS traversal
    private int dfs(TreeNode node, int min, int max) {
        if (node == null) return max - min; // Base case: return the difference between max and min
        // Update min and max values
        min = Math.min(min, node.val);
        max = Math.max(max, node.val);
        // Calculate max difference for left and right subtree
        int leftDiff = dfs(node.left, min, max);
        int rightDiff = dfs(node.right, min, max);
        // Return the maximum of left and right differences
        return Math.max(leftDiff, rightDiff);
    }
}
