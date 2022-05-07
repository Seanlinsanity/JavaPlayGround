package com.seanlindev.algorithms;

import java.util.Arrays;
import java.util.List;

public class KnapsackProblem {
    static public List<List<Integer>> valueWeightPairs = Arrays.asList(
                                                        Arrays.asList(60, 10),
                                                        Arrays.asList(100, 20),
                                                        Arrays.asList(120, 30)
                                                    );

    static public int maxValue(int maxWeight, List<List<Integer>> valueWeightPairs) {
        if (valueWeightPairs.size() == 0) {
            return 0;
        }

        int[][] results = new int[maxWeight + 1][valueWeightPairs.size() + 1];
        for (int i = 0; i < maxWeight + 1; i++) {
            for (int j = 0; j < valueWeightPairs.size() + 1; j++) {
                results[i][j] = -1;
            }
        }
        return maxValue(maxWeight, valueWeightPairs.size(), valueWeightPairs, results);
    }

    static private int maxValue(int maxWeight, int items, List<List<Integer>> valueWeightPairs, int[][] results) {
        if (items == 0 || maxWeight == 0) {
            return 0;
        }

        if (results[maxWeight][items] != -1) {
            return results[maxWeight][items];
        }

        Integer value = valueWeightPairs.get(valueWeightPairs.size() - items).get(0);
        Integer weight = valueWeightPairs.get(valueWeightPairs.size() - items).get(1);
        int result;
        if (maxWeight >= weight) {
            result = Math.max(value + maxValue(maxWeight - weight, items - 1, valueWeightPairs, results),
                              maxValue(maxWeight, items - 1, valueWeightPairs, results));
        } else {
            result = maxValue(maxWeight, items - 1, valueWeightPairs, results);
        }
        results[maxWeight][items] = result;
        return result;
    }

}
