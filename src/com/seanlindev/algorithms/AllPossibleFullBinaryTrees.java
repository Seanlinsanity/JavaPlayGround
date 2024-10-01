package com.seanlindev.algorithms;

import com.seanlindev.utils.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
Given an integer n, return a list of all possible full binary trees with n nodes. Each node of each tree in the answer must have Node.val == 0.

Each element of the answer is the root node of one possible tree. You may return the final list of trees in any order.

A full binary tree is a binary tree where each node has exactly 0 or 2 children.



Example 1:


Input: n = 7
Output: [[0,0,0,null,null,0,0,null,null,0,0],[0,0,0,null,null,0,0,0,0],[0,0,0,0,0,0,0],[0,0,0,0,0,null,null,null,null,0,0],[0,0,0,0,0,null,null,0,0]]
Example 2:

Input: n = 3
Output: [[0,0,0]]


Constraints:

1 <= n <= 20
 */
public class AllPossibleFullBinaryTrees {
    Map<Integer, List<TreeNode>> dp = new HashMap<>();

    public List<TreeNode> allPossibleFBT(int n) {
        if (n == 1) { return new ArrayList<>(List.of(new TreeNode(0))); }
        if (n % 2 == 0) { return new ArrayList<>(); }
        if (dp.get(n) != null) { return dp.get(n); }

        List<TreeNode> result = new ArrayList<>();
        for (int i = 1; i < n - 1; i++) {
            List<TreeNode> leftResult = allPossibleFBT(i);
            List<TreeNode> rightResult = allPossibleFBT(n - 1 - i);
            if (leftResult.size() == 0 || rightResult.size() == 0) {
                continue;
            }

            for (TreeNode left: leftResult) {
                for (TreeNode right: rightResult) {
                    TreeNode root = new TreeNode(0);
                    root.left = left;
                    root.right = right;
                    result.add(root);
                }
            }
        }

        dp.put(n, result);
        return result;
    }
}
