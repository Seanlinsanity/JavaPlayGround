package com.seanlindev.algorithms;

import java.util.HashMap;

public class NumberOfGoodPairs {
    public int numIdenticalPairs(int[] nums) {
        HashMap<Integer, Integer> numMap = new HashMap<Integer, Integer>();

        int count = 0;
        for (int num: nums) {
            if (numMap.get(Integer.valueOf(num)) != null) {
                Integer currentNumCount = numMap.get(Integer.valueOf(num));
                count += currentNumCount;
                numMap.put(Integer.valueOf(num), currentNumCount + 1);
            } else {
                numMap.put(Integer.valueOf(num), 1);
            }

        }

        return count;
    }
}
