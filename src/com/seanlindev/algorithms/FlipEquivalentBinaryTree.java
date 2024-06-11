package com.seanlindev.algorithms;

import com.seanlindev.utils.TreeNode;

public class FlipEquivalentBinaryTree {
    public boolean flipEquiv(TreeNode root1, TreeNode root2) {
        return preOrderTraverse(root1, root2);
    }

    boolean preOrderTraverse(TreeNode node1, TreeNode node2) {
        if (node1 == null && node2 == null) { return true; }
        if (node1 == null || node2 == null) { return false; }
        if (node1.val != node2.val) { return false; }

        if (preOrderTraverse(node1.right, node2.left) && preOrderTraverse(node1.left, node2.right)) {
            return true;
        }

        if (preOrderTraverse(node1.left, node2.left) && preOrderTraverse(node1.right, node2.right)) {
            return true;
        }

        return false;
    }

//    boolean preOrderTraverse(TreeNode node1, TreeNode node2) {
//         boolean shouldFlip = false;
//         if (node1.left != null && node2.left != null) {
//             if (node1.left.val != node2.left.val) {
//                 shouldFlip = true;
//             }
//         } else if (node1.left == null && node2.left != null || node1.left != null && node2.left == null) {
//             shouldFlip = true;
//         }
//
//         if (shouldFlip) {
//             return preOrderTraverse(node1.right, node2.left) && preOrderTraverse(node1.left, node2.right);
//         } else {
//             return preOrderTraverse(node1.left, node2.left) && preOrderTraverse(node1.right, node2.right);
//         }
//    }
}
