package com.seanlindev.algorithms;

/*
An integer array is called arithmetic if it consists of at least three elements and if the difference between any two consecutive elements is the same.

For example, [1,3,5,7,9], [7,7,7,7], and [3,-1,-5,-9] are arithmetic sequences.
Given an integer array nums, return the number of arithmetic subarrays of nums.

A subarray is a contiguous subsequence of the array.



Example 1:

Input: nums = [1,2,3,4]
Output: 3
Explanation: We have 3 arithmetic slices in nums: [1, 2, 3], [2, 3, 4] and [1,2,3,4] itself.
Example 2:

Input: nums = [1]
Output: 0
 */
public class ArithmeticSlices {
    public int numberOfArithmeticSlices(int[] nums) {
        if (nums.length < 3) { return 0; }

        int result = 0;
        int left = 0;
        int right = 2;
        while (right < nums.length) {
            if (nums[right] - nums[right - 1] == nums[right - 1] - nums[right - 2]) {
                int count = right - left - 1;
                result += count;
            } else {
                left = right - 1;
            }

            right += 1;
        }

        return result;

    }
}
