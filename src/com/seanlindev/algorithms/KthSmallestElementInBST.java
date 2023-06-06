package com.seanlindev.algorithms;

import com.seanlindev.utils.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class KthSmallestElementInBST {
    public int kthSmallest(TreeNode root, int k) {
        List<Integer> nums = new ArrayList<>();
        inorderTraverse(root, nums);
        return nums.get(k - 1);
    }

    public void inorderTraverse(TreeNode root, List<Integer> list) {
        if (root == null) { return; }

        inorderTraverse(root.left, list);
        list.add(root.val);
        inorderTraverse(root.right, list);
    }
}
