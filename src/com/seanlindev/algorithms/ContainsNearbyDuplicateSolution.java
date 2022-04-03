package com.seanlindev.algorithms;

import java.util.HashMap;

public class ContainsNearbyDuplicateSolution {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        if (k == 0) {
            return false;
        }

        HashMap<Integer, Integer> numberPositionMap = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++) {
            final Integer number = Integer.valueOf(nums[i]);
            final Integer position = numberPositionMap.get(number);
            if (position != null && i - position <= k) {
                return true;
            }

            numberPositionMap.put(number, i);
        }
        return false;
    }
}
