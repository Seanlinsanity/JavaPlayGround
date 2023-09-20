package com.seanlindev.algorithms;

/*
You may recall that an array arr is a mountain array if and only if:

arr.length >= 3
There exists some index i (0-indexed) with 0 < i < arr.length - 1 such that:
arr[0] < arr[1] < ... < arr[i - 1] < arr[i]
arr[i] > arr[i + 1] > ... > arr[arr.length - 1]
Given an integer array nums​​​, return the minimum number of elements to remove to make nums​​​ a mountain array.



Example 1:

Input: nums = [1,3,1]
Output: 0
Explanation: The array itself is a mountain array so we do not need to remove any elements.
Example 2:

Input: nums = [2,1,1,5,6,2,3,1]
Output: 3
Explanation: One solution is to remove the elements at indices 0, 1, and 5, making the array nums = [1,5,6,3,1].


Constraints:

3 <= nums.length <= 1000
1 <= nums[i] <= 109
It is guaranteed that you can make a mountain array out of nums.
 */

public class MinimumRemovalsToMountainArray {
    public int minimumMountainRemovals(int[] nums) {
        return nums.length - findLBSLength(nums);
    }

    public int findLBSLength(int[] nums) {
        int maxLength = 0;
        Integer[][] dp = new Integer[nums.length][nums.length + 1];
        Integer[][] dpReverse = new Integer[nums.length][nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            int rightLength = findLDSLength(dp, nums, i, -1);
            int leftLength = findLDSLengthReverse(dpReverse, nums, i, -1);
            int length = rightLength + leftLength - 1;
            if (leftLength > 1 && rightLength > 1 && length >= 3) {
                maxLength = Math.max(maxLength, length);
            }
        }
        return maxLength;
    }

    private int findLDSLength(Integer[][] dp, int[] nums, int currentIndex, int previousIndex) {
        if(currentIndex == nums.length) { return 0; }

        if(dp[currentIndex][previousIndex+1] == null) {
            // include nums[currentIndex] if it is smaller than the previous number
            int c1 = 0;
            if(previousIndex == -1 || nums[currentIndex] < nums[previousIndex])
                c1 = 1 + findLDSLength(dp, nums, currentIndex+1, currentIndex);

            // excluding the number at currentIndex
            int c2 = findLDSLength(dp, nums, currentIndex+1, previousIndex);

            dp[currentIndex][previousIndex+1] = Math.max(c1, c2);
        }

        return dp[currentIndex][previousIndex+1];
    }

    // find the longest decreasing subsequence from currentIndex till the beginning of the array
    private int findLDSLengthReverse(Integer[][] dp, int[] nums, int currentIndex, int previousIndex) {
        if(currentIndex < 0) { return 0; }

        if(dp[currentIndex][previousIndex+1] == null) {
            // include nums[currentIndex] if it is smaller than the previous number
            int c1 = 0;
            if(previousIndex == -1 || nums[currentIndex] < nums[previousIndex])
                c1 = 1 + findLDSLengthReverse(dp, nums, currentIndex-1, currentIndex);

            // excluding the number at currentIndex
            int c2 = findLDSLengthReverse(dp, nums, currentIndex-1, previousIndex);

            dp[currentIndex][previousIndex+1] = Math.max(c1, c2);
        }
        return dp[currentIndex][previousIndex+1];
    }
}
