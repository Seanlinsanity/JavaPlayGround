package com.seanlindev.algorithms;

import java.util.HashMap;
import java.util.Map;

/*
Given an integer array nums, return true if you can partition the array into two subsets such that the sum of the elements in both subsets is equal or false otherwise.



Example 1:

Input: nums = [1,5,11,5]
Output: true
Explanation: The array can be partitioned as [1, 5, 5] and [11].
Example 2:

Input: nums = [1,2,3,5]
Output: false
Explanation: The array cannot be partitioned into equal sum subsets.


Constraints:

1 <= nums.length <= 200
1 <= nums[i] <= 100
 */
public class PartitionEqualSubsetSum {
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % 2 != 0) return false;
        int target = sum / 2;
        boolean[][] dp = new boolean[nums.length + 1][target + 1];
        int n = nums.length;
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= target; j++) {
                if (i == 0 || j == 0) {
                    if (i == 0) dp[i][j] = false; else if (j == 0) dp[i][j] =
                            true;
                } else if (j >= nums[i - 1]) {
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i - 1]];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[n][target];
    }

     public boolean canPartition2(int[] nums) {
         if (nums.length == 1) { return false; }

         int sum = 0;
         for (int num: nums) {
             sum += num;
         }

         if (sum % 2 != 0) { return false; }

         Map<String, Boolean> dp = new HashMap<>();
         return canPartition(0, nums, sum / 2, dp);
     }

     public boolean canPartition(int index, int[] nums, int target, Map<String, Boolean> dp) {
         String key = index + "-" + target;
         if (dp.get(key) != null) {
             return dp.get(key);
         }

         if (target == 0) {
             dp.put(key, true);
             return true;
         }

         if (target < 0) {
             dp.put(key, false);
             return false;
         }

         int num = nums[index];
         if (index == nums.length - 1) {
             boolean isEqual = num == target;
             dp.put(key, isEqual);
             return isEqual;
         }

         boolean result = canPartition(index + 1, nums, target - num, dp) ||
                 canPartition(index + 1, nums, target, dp);
         dp.put(key, result);
         return result;
     }
}
