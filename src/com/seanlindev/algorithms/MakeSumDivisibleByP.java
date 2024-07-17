package com.seanlindev.algorithms;

import java.util.HashMap;
import java.util.Map;

public class MakeSumDivisibleByP {
    // [3,1,4,2], 6
    public int minSubarray(int[] nums, int p) {
        long sum = 0;
        for (int num: nums) {
            sum += (long)num;
        }

        if (sum % p == 0) { return 0; }

        int result = nums.length;
        int remainder = (int)(sum % p); //4
        long prefixSum = 0;
        Map<Integer, Integer> prefixSumRemainder = new HashMap<>();
        prefixSumRemainder.put(0, -1);
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];//3 -> 1 -> 4 -> 2
            prefixSum += num; //3 -> 4 -> 8 -> 10
            int prefixRemainder = (int)(prefixSum % p); //4
            // (prefixSumRemainder - targetRemainder + p) % p = remainder
            int targetRemainder = (prefixRemainder - remainder + p) % p; //0
            if (prefixSumRemainder.get(targetRemainder) != null) {
                result = Math.min(result, i - prefixSumRemainder.get(targetRemainder));
            }

            prefixSumRemainder.put(prefixRemainder, i); //{3:0, 0:1, 2:2, }
        }

        return result == nums.length ? -1 : result;
    }
}
