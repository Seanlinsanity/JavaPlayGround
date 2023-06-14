package com.seanlindev.algorithms;

/*
Given an integer array nums, return the length of the longest strictly increasing
subsequence
.



Example 1:

Input: nums = [10,9,2,5,3,7,101,18]
Output: 4
Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.
Example 2:

Input: nums = [0,1,0,3,2,3]
Output: 4
Example 3:

Input: nums = [7,7,7,7,7,7,7]
Output: 1


Constraints:

1 <= nums.length <= 2500
-104 <= nums[i] <= 104
 */
public class LongestIncreasingSubsequence {
    public int lengthOfLIS(int[] nums) {
        //O(n^2)
        if (nums.length == 1) return 1;

        int[] memo = new int[nums.length];
        int result = 0;
        for (int i = nums.length - 1; i >= 0; i--) {
            int max = 1;
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] < nums[j]) {
                    max = Math.max(1 + memo[j], max);
                }
            }
            memo[i] = max;
            result = Math.max(result, memo[i]);
        }
        return result;
    }

     public int lengthOfLIS2(int[] nums) {
         int[] memo = new int[nums.length];
         int result = 0;
         for (int i = nums.length - 1; i >= 0; i--) {
             result = Math.max(lengthOfLIS(nums, i, memo), result);
         }
         return result;
     }

     public int lengthOfLIS(int[] nums, int start, int[] memo) {
         if (start == nums.length - 1) { return 1; }
         if (memo[start] > 0) { return memo[start]; }
         int result = 1;
         for (int i = start + 1; i < nums.length; i++) {
             if (nums[i] > nums[start]) {
                 result = Math.max(1 + lengthOfLIS(nums, i, memo), result);
             }
         }
         memo[start] = result;
         return result;
     }
}
