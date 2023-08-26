package com.seanlindev.algorithms;

import com.seanlindev.utils.TreeNode;

/*
Given an integer array nums where the elements are sorted in ascending order, convert it to a
height-balanced
 binary search tree.



Example 1:


Input: nums = [-10,-3,0,5,9]
Output: [0,-3,9,-10,null,5]
Explanation: [0,-10,5,null,-3,null,9] is also accepted:

Example 2:


Input: nums = [1,3]
Output: [3,1]
Explanation: [1,null,3] and [3,1] are both height-balanced BSTs.


Constraints:

1 <= nums.length <= 104
-104 <= nums[i] <= 104
nums is sorted in a strictly increasing order.
 */

public class SortedArrayToBST {
    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums.length == 0) { return null; }

        return sortedArrayToBSTRecursion(0, nums.length - 1, nums);
    }

    TreeNode sortedArrayToBSTRecursion(int start, int end, int[] nums) {
        if (start > end) { return null; }

        int mid = (start + end) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left =  sortedArrayToBSTRecursion(start, mid - 1, nums);
        root.right = sortedArrayToBSTRecursion(mid + 1, end, nums);
        return root;
    }
}
