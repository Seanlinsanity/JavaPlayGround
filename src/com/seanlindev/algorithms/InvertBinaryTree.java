package com.seanlindev.algorithms;

import com.seanlindev.utils.TreeNode;

public class InvertBinaryTree {
    public TreeNode invertTree(TreeNode root) {
        if (root == null) { return root; }
        TreeNode left = root.left; //2
        TreeNode right = root.right; //7
        root.left = right;
        root.right = left;
        if (left != null) {
            invertTree(left);
        }
        if (right != null) {
            invertTree(right);
        }
        return root;
    }
}
