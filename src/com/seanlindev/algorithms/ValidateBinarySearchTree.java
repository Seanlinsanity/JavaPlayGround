package com.seanlindev.algorithms;

import com.seanlindev.utils.TreeNode;

public class ValidateBinarySearchTree {
    public boolean isValidBST(TreeNode root) {
        if (root.left == null && root.right == null) { return true; }
        return isBSTRecursion(null, null, root);
    }

    public boolean isBSTRecursion(Integer max, Integer min, TreeNode node) {
        if (max != null && node.val >= max) { return false; }
        if (min != null && node.val <= min) { return false; }

        TreeNode current = node; // 2 , 1
        TreeNode left = current.left;  //1, null
        TreeNode right = current.right;  //3, null

        if (left != null && !isBSTRecursion(current.val, min, left)) {
            return false;
        }

        if (right != null && !isBSTRecursion(max, current.val, right)) {
            return false;
        }

        return true;
    }
}
