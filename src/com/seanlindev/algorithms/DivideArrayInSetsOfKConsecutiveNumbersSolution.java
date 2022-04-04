package com.seanlindev.algorithms;

import java.util.Arrays;
import java.util.HashMap;

public class DivideArrayInSetsOfKConsecutiveNumbersSolution {
    public boolean isPossibleDivide(int[] nums, int k) {
        if (nums.length == 0 || nums.length % k != 0) { return false; }

        if (k == 1) { return true; }

        HashMap<Integer, Integer> numCountMap = new HashMap<Integer, Integer>();
        for (int num:nums) {
            final Integer currentNumCount = numCountMap.get(Integer.valueOf(num));
            if (currentNumCount != null) {
                numCountMap.put(Integer.valueOf(num), currentNumCount + 1);
            } else {
                numCountMap.put(Integer.valueOf(num), 1);
            }
        }

        Arrays.sort(nums);

        for (int num:nums) {
            final Integer currentNumCount = numCountMap.get(Integer.valueOf(num));
            if (currentNumCount == null) {
                continue;
            } else if (currentNumCount == 1) {
                numCountMap.remove(Integer.valueOf(num));
            } else {
                numCountMap.put(Integer.valueOf(num), currentNumCount - 1);
            }

            for (int i = 1; i < k ; i++) {
                final Integer nextNumCount = numCountMap.get(Integer.valueOf(num + i));
                if (nextNumCount == null) {
                    return false;
                } else if (nextNumCount == 1) {
                    numCountMap.remove(Integer.valueOf(num + i));
                } else {
                    numCountMap.put(Integer.valueOf(num + i), nextNumCount - 1);
                }
            }
        }

        return true;
    }
}
