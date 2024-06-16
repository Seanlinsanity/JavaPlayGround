package com.seanlindev.algorithms;

public class CheckValidPartitionForTheArray {
    public boolean validPartition(int[] nums) {
        boolean[] dp = new boolean[nums.length + 1];
        dp[nums.length] = true;
        for (int index = nums.length - 1; index >= 0; index--) {
            if (index + 1 < nums.length && nums[index] == nums[index + 1]) {
                if (dp[index + 2]) {
                    dp[index] = true;
                }
            }

            if (index + 2 < nums.length && nums[index] == nums[index + 1] && nums[index] == nums[index + 2]) {
                if (dp[index + 3]) {
                    dp[index] = true;
                }
            }

            if (index + 2 < nums.length && nums[index] == nums[index + 1] - 1 && nums[index] == nums[index + 2] - 2) {
                if (dp[index + 3]) {
                    dp[index] = true;
                }
            }
        }

        return dp[0];

        // Boolean[] dp = new Boolean[nums.length];
        // return validPartitionRecursion(nums, 0, dp);
    }

    boolean validPartitionRecursion(int[] nums, int index, Boolean[] dp) {
        if (index == nums.length) { return true; }
        if (dp[index] != null) { return dp[index]; }

        if (index + 1 < nums.length && nums[index] == nums[index + 1]) {
            if (validPartitionRecursion(nums, index + 2, dp)) {
                dp[index] = true;
                return true;
            }
        }

        if (index + 2 < nums.length && nums[index] == nums[index + 1] && nums[index] == nums[index + 2]) {
            if (validPartitionRecursion(nums, index + 3, dp)) {
                dp[index] = true;
                return true;
            }
        }

        if (index + 2 < nums.length && nums[index] == nums[index + 1] - 1 && nums[index] == nums[index + 2] - 2) {
            if (validPartitionRecursion(nums, index + 3, dp)) {
                dp[index] = true;
                return true;
            }
        }

        dp[index] = false;
        return false;
    }
}
