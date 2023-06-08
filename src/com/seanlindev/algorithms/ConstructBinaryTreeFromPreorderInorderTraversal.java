package com.seanlindev.algorithms;

import com.seanlindev.utils.TreeNode;

import java.util.HashMap;
import java.util.Map;

public class ConstructBinaryTreeFromPreorderInorderTraversal {
    Map<Integer, Integer> inorderPositions = new HashMap<>();

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        for (int i = 0; i < inorder.length; i++) {
            inorderPositions.put(inorder[i], i);
        }
        TreeNode root = new TreeNode(preorder[0]);
        int[] left = {0, indexOf(root.val)};
        int[] right = {indexOf(root.val) + 1, inorder.length};
        buildTree(root, 0, preorder, left, right);
        return root;
    }

    public void buildTree(TreeNode root, int start, int[] preorder, int[] left, int[] right) {
        if (left[1] - left[0] > 0) {
            TreeNode leftNode = new TreeNode(preorder[start + 1]);
            int newStart = start + 1;
            int[] newLeft = {left[0], indexOf(leftNode.val)};
            int[] newRight = {indexOf(leftNode.val) + 1, left[1]};
            buildTree(leftNode, newStart, preorder, newLeft, newRight);
            root.left = leftNode;
        }

        if (right[1] - right[0] > 0) {
            int leftLength = left[1] - left[0];
            TreeNode rightNode = new TreeNode(preorder[start + leftLength + 1]);
            int newStart = start + leftLength + 1;
            int[] newLeft = {right[0], indexOf(rightNode.val)};
            int[] newRight = {indexOf(rightNode.val) + 1, right[1]};
            buildTree(rightNode, newStart, preorder, newLeft, newRight);
            root.right = rightNode;
        }
    }

    public TreeNode buildTree2(int[] preorder, int[] inorder) {
        if (preorder.length < 1 || inorder.length < 1) return null;

        for (int i = 0; i < inorder.length; i++) {
            inorderPositions.put(inorder[i], i);
        }

        return builder(preorder, 0, 0, inorder.length - 1);
    }

    public TreeNode builder(
            int[] preorder,
            int preorderIndex,
            int inorderLow,
            int inorderHigh
    ) {
        if (
                preorderIndex > preorder.length - 1 || inorderLow > inorderHigh
        ) return null;

        int currentVal = preorder[preorderIndex];
        TreeNode n = new TreeNode(currentVal);
        int mid = inorderPositions.get(currentVal);

        n.left = builder(preorder, preorderIndex + 1, inorderLow, mid - 1);
        n.right =
                builder(
                        preorder,
                        preorderIndex + (mid - inorderLow) + 1,
                        mid + 1,
                        inorderHigh
                );

        return n;
    }

    public int indexOf(int target) {
        return inorderPositions.get(target);
    }
}
