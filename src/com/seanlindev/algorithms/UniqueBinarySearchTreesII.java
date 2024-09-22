package com.seanlindev.algorithms;

import com.seanlindev.utils.TreeNode;

import java.util.ArrayList;
import java.util.List;

/*
Given an integer n, return all the structurally unique BST's (binary search trees), which has exactly n nodes of unique values from 1 to n. Return the answer in any order.



Example 1:


Input: n = 3
Output: [[1,null,2,null,3],[1,null,3,2],[2,1,3],[3,1,null,null,2],[3,2,null,1]]
Example 2:

Input: n = 1
Output: [[1]]


Constraints:

1 <= n <= 8
 */

public class UniqueBinarySearchTreesII {
    public List<TreeNode> generateTrees(int n) {
        return generateTreesTracking(1, n);
    }

    List<TreeNode> generateTreesTracking(int low, int high) {
        List<TreeNode> result = new ArrayList<>();
        if (low > high) {
            result.add(null);
            return result;
        }

        for (int i = low; i <= high; i++) {
            List<TreeNode> leftResult = generateTreesTracking(low, i - 1);
            List<TreeNode> rightResult = generateTreesTracking(i + 1, high);
            for (TreeNode leftNode: leftResult) {
                for (TreeNode rightNode: rightResult) {
                    TreeNode root = new TreeNode(i);
                    root.left = leftNode;
                    root.right = rightNode;
                    result.add(root);
                }
            }
        }

        return result;
    }

    // List<TreeNode> generateTreesTracking(int num, List<Integer> left, List<Integer> right) {
    //     List<TreeNode> leftResult = new ArrayList<>();
    //     for (int i = 0; i < left.size(); i++) {
    //         List<Integer> nextRight = new ArrayList<>();
    //         for (int j = i + 1; j < left.size(); j++) {
    //             nextRight.add(left.get(j));
    //         }
    //         List<Integer> nextLeft = new ArrayList<>();
    //         for (int j = 0; j <= i - 1; j++) {
    //             nextLeft.add(left.get(j));
    //         }
    //         leftResult.addAll(generateTreesTracking(left.get(i), nextLeft, nextRight));
    //     }

    //     List<TreeNode> rightResult = new ArrayList<>();
    //     for (int i = 0; i < right.size(); i++) {
    //         List<Integer> nextRight = new ArrayList<>();
    //         for (int j = i + 1; j < right.size(); j++) {
    //             nextRight.add(right.get(j));
    //         }
    //         List<Integer> nextLeft = new ArrayList<>();
    //         for (int j = 0; j <= i - 1; j++) {
    //             nextLeft.add(right.get(j));
    //         }
    //         rightResult.addAll(generateTreesTracking(right.get(i), nextLeft, nextRight));
    //     }

    //     List<TreeNode> result = new ArrayList<>();
    //     if (leftResult.isEmpty() && rightResult.isEmpty()) {
    //         result.add(new TreeNode(num));
    //     } else if (leftResult.isEmpty()) {
    //         for (TreeNode rightNode: rightResult) {
    //             TreeNode root = new TreeNode(num);
    //             root.right = rightNode;
    //             result.add(root);
    //         }
    //     } else if (rightResult.isEmpty()) {
    //         for (TreeNode leftNode: leftResult) {
    //             TreeNode root = new TreeNode(num);
    //             root.left = leftNode;
    //             result.add(root);
    //         }
    //     } else {
    //         for (TreeNode leftNode: leftResult) {
    //             for (TreeNode rightNode: rightResult) {
    //                 TreeNode root = new TreeNode(num);
    //                 root.left = leftNode;
    //                 root.right = rightNode;
    //                 result.add(root);
    //             }
    //         }
    //     }

    //     return result;
    // }
}
