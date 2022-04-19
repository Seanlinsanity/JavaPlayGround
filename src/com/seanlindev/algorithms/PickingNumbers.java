package com.seanlindev.algorithms;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PickingNumbers {
    public static int pickingNumbers(List<Integer> a) {
        Map<Integer, Integer> numCountMap = new HashMap<Integer, Integer>();
        for (Integer num:a) {
            if (numCountMap.get(num) != null) {
                numCountMap.put(num, numCountMap.get(num) + 1);
            } else {
                numCountMap.put(num, 1);
            }
        }

        int result = 0;
        for (Map.Entry<Integer, Integer> set: numCountMap.entrySet()) {
            Integer key = set.getKey();
            if (numCountMap.get(key + 1) != null) {
                result = Math.max(numCountMap.get(key + 1).intValue() + set.getValue().intValue(), result);
            } else {
                result = Math.max(set.getValue().intValue(), result);
            }
        }

        return result;
    }
}
