package com.seanlindev.algorithms;

public class MaximumDifferenceBetweenIncreasingElementsSolution {
    public int maximumDifference(int[] nums) {
        if (nums.length < 2) { return -1; }

        int result = -1;
        int currentMin = nums[0];

        for (int num: nums) {
            if ((num > currentMin) && (num - currentMin > result))  {
                result = num - currentMin;
            } else if (num < currentMin) {
                currentMin = num;
            }
        }

        return result;
    }
}
