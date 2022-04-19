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
        for (Map.Entry<Integer, Integer> set :
                numCountMap.entrySet()) {
            if (numCountMap.get(set.getKey() + 1) != null) {
                Integer count = numCountMap.get(set.getKey()) + numCountMap.get(set.getKey() + 1);
                result = Math.max(result, count.intValue());
            } else {
                result = Math.max(result, numCountMap.get(set.getKey()).intValue());
            }
        }

        return result;
    }
}
