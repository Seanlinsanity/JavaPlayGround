package com.seanlindev.algorithms;

import java.util.Arrays;

public class MinimumDifferenceBetweenKScores {
    public int minimumDifference(int[] nums, int k) {
        if (k == 1) { return 0; }

        Arrays.sort(nums);
        int result = Integer.MAX_VALUE;
        for (int i = 0; i <= nums.length - k; i++) {
            int diff = nums[i + k - 1] - nums[i];
            result = Math.min(result, diff);
        }

        return result;
    }
}
