package com.seanlindev.algorithms;

/*
Given an integer array nums, handle multiple queries of the following types:

Update the value of an element in nums.
Calculate the sum of the elements of nums between indices left and right inclusive where left <= right.
Implement the NumArray class:

NumArray(int[] nums) Initializes the object with the integer array nums.
void update(int index, int val) Updates the value of nums[index] to be val.
int sumRange(int left, int right) Returns the sum of the elements of nums between indices left and right inclusive (i.e. nums[left] + nums[left + 1] + ... + nums[right]).


Example 1:

Input
["NumArray", "sumRange", "update", "sumRange"]
[[[1, 3, 5]], [0, 2], [1, 2], [0, 2]]
Output
[null, 9, null, 8]

Explanation
NumArray numArray = new NumArray([1, 3, 5]);
numArray.sumRange(0, 2); // return 1 + 3 + 5 = 9
numArray.update(1, 2);   // nums = [1, 2, 5]
numArray.sumRange(0, 2); // return 1 + 2 + 5 = 8


Constraints:

1 <= nums.length <= 3 * 104
-100 <= nums[i] <= 100
0 <= index < nums.length
-100 <= val <= 100
0 <= left <= right < nums.length
At most 3 * 104 calls will be made to update and sumRange.
 */
public class RangeSumQueryMutable {
    // Segment Tree
    class TreeNode {
        int val;
        int start;
        int end;
        TreeNode left;
        TreeNode right;

        TreeNode(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    private final TreeNode root;

    public RangeSumQueryMutable(int[] nums) {
        this.root = buildSegmentTree(nums, 0, nums.length - 1);
    }

    public void update(int index, int val) {
        updateTree(root, index, val);
    }

    public int sumRange(int left, int right) {
        return sumRangeTree(root, left, right);
    }

    private TreeNode buildSegmentTree(int[] nums, int start, int end) {
        TreeNode node = new TreeNode(start, end);
        if (start == end) {
            node.val = nums[start];
            return node;
        }

        int mid = (start + end) / 2;
        node.left = buildSegmentTree(nums, start, mid);
        node.right = buildSegmentTree(nums, mid + 1, end);
        node.val = node.left.val + node.right.val;
        return node;
    }

    private void updateTree(TreeNode node, int index, int val) {
        if (index > node.end || index < node.start) { return; }
        if (node.start == node.end) {
            node.val = val;
            return;
        }

        updateTree(node.left, index, val);
        updateTree(node.right, index, val);
        node.val = node.left.val + node.right.val;
        return;
    }

    private int sumRangeTree(TreeNode node, int left, int right) {
        if (left > node.end || right < node.start) { return 0; }

        if (node.start >= left && node.end <= right) { return node.val; }

        return sumRangeTree(node.left, left, right) + sumRangeTree(node.right, left, right);
    }

}
