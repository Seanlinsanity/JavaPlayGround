package com.seanlindev.algorithms;

import java.util.HashMap;
import java.util.Map;

public class ElementAppearingMoreThan25PercentInSortedArraySolution {
    public int findSpecialInteger(int[] arr) {
        if (arr.length == 1) {
            return arr[0];
        }

        Map<Integer, Integer> numCountMap = new HashMap<Integer, Integer>();

        for (int num: arr) {
            final Integer count = numCountMap.get(num);
            if (count == null) {
                numCountMap.put(Integer.valueOf(num), 1);
            } else if (count + 1 > arr.length / 4) {
                return num;
            } else {
                numCountMap.put(Integer.valueOf(num), count + 1);
            }
        }

        return 0;
    }
}
