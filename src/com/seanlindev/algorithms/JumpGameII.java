package com.seanlindev.algorithms;

public class JumpGameII {
    public int jump(int[] nums) {
        int left = 0;
        int right = 0;
        int count = 0;
        while(right < nums.length - 1) {
            int max = -1;
            for (int index = left; index <= right; index++) {
                int jump = nums[index];
                max = Math.max(max, index + jump);
            }
            left = right + 1;
            right = max;
            count += 1;
        }

        return count;
    }
}
