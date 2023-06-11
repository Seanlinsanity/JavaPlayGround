package com.seanlindev.algorithms;

public class MaximumProductSubarray {
    public int maxProduct(int[] nums) {
        int result = Integer.MIN_VALUE;
        int preMax = 0;
        int preMin = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i == 0) {
                preMax = nums[i];
                preMin = nums[i];
                result = Math.max(nums[i], result);
                continue;
            }

            int max = 0;
            int min = 0;
            if (nums[i] >= 0) {
                max = Math.max(nums[i] * preMax, nums[i]);
                min = Math.min(nums[i] * preMin, nums[i]);
            } else {
                max = Math.max(nums[i], nums[i] * preMin);
                min = Math.min(nums[i], nums[i] * preMax);
            }

            preMax = max;
            preMin = min;

            result = Math.max(max, result);
        }

        return result;
    }
}
