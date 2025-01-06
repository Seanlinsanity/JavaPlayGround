package com.seanlindev.algorithms;

import com.seanlindev.utils.TreeNode;

import java.util.ArrayList;
import java.util.List;

/*
Given two binary search trees root1 and root2, return a list containing all the integers from both trees sorted in ascending order.



Example 1:


Input: root1 = [2,1,4], root2 = [1,0,3]
Output: [0,1,1,2,3,4]
Example 2:


Input: root1 = [1,null,8], root2 = [8,1]
Output: [1,1,8,8]


Constraints:

The number of nodes in each tree is in the range [0, 5000].
-105 <= Node.val <= 105

 */
public class AllElementsInTwoBinarySearchTrees {
    public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
        List<Integer> nums1 = new ArrayList<>();
        inorderDFS(root1, nums1);

        List<Integer> nums2 = new ArrayList<>();
        inorderDFS(root2, nums2);

        int index1 = 0;
        int index2 = 0;
        List<Integer> result = new ArrayList<>();
        while (index1 < nums1.size() && index2 < nums2.size()) {
            if (nums1.get(index1) < nums2.get(index2)) {
                result.add(nums1.get(index1));
                index1 += 1;
            } else {
                result.add(nums2.get(index2));
                index2 += 1;
            }
        }

        while (index1 < nums1.size()) {
            result.add(nums1.get(index1));
            index1 += 1;
        }

        while (index2 < nums2.size()) {
            result.add(nums2.get(index2));
            index2 += 1;
        }

        return result;
    }

    private void inorderDFS(TreeNode node, List<Integer> nums) {
        if (node == null) { return; }

        inorderDFS(node.left, nums);
        nums.add(node.val);
        inorderDFS(node.right, nums);
    }
}
