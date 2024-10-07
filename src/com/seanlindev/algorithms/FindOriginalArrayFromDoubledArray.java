package com.seanlindev.algorithms;

import java.util.*;

/*
An integer array original is transformed into a doubled array changed by appending twice the value of every element in original, and then randomly shuffling the resulting array.

Given an array changed, return original if changed is a doubled array. If changed is not a doubled array, return an empty array. The elements in original may be returned in any order.



Example 1:

Input: changed = [1,3,4,2,6,8]
Output: [1,3,4]
Explanation: One possible original array could be [1,3,4]:
- Twice the value of 1 is 1 * 2 = 2.
- Twice the value of 3 is 3 * 2 = 6.
- Twice the value of 4 is 4 * 2 = 8.
Other original arrays could be [4,3,1] or [3,1,4].
Example 2:

Input: changed = [6,3,0,1]
Output: []
Explanation: changed is not a doubled array.
Example 3:

Input: changed = [1]
Output: []
Explanation: changed is not a doubled array.


Constraints:

1 <= changed.length <= 105
0 <= changed[i] <= 105
 */
public class FindOriginalArrayFromDoubledArray {
    public int[] findOriginalArray(int[] changed) {
        if (changed.length % 2 != 0) { return new int[]{}; }

        Arrays.sort(changed);

        Map<Integer, Integer> countMap = new HashMap<>();
        List<Integer> original = new ArrayList<>();

        // Count occurrences of each element
        for (int num : changed) {
            countMap.put(num, countMap.getOrDefault(num, 0) + 1);
        }

        for (int num : changed) {
            if (countMap.get(num) == 0) continue; // Skip if count is 0
            if (countMap.getOrDefault(2 * num, 0) == 0) return new int[]{}; // Check if double exists

            original.add(num); // Add to original array
            countMap.put(num, countMap.get(num) - 1); // Decrement count
            countMap.put(2 * num, countMap.get(2 * num) - 1); // Decrement count of double
        }

        int[] arr = new int[original.size()];
        for (int i = 0; i < original.size(); i++) {
            arr[i] = original.get(i);
        }

        return arr;

        // List<Integer> result = new ArrayList<>();
        // Map<Integer, Integer> numCountMap = new HashMap<>();
        // for (int num: changed) {
        //     if (num % 2 == 1) {
        //         numCountMap.put(num * 2, numCountMap.getOrDefault(num * 2, 0) + 1);
        //         result.add(num);
        //     } else {
        //         if (numCountMap.get(num) != null) {
        //             if (numCountMap.get(num) == 1) {
        //                 numCountMap.remove(num);
        //             } else {
        //                 numCountMap.put(num, numCountMap.get(num) - 1);
        //             }
        //         } else {
        //             numCountMap.put(num * 2, numCountMap.getOrDefault(num * 2, 0) + 1);
        //             result.add(num);
        //         }
        //     }
        // }

        // if (numCountMap.keySet().size() > 0) { return new int[]{}; }

        // int[] arr = new int[result.size()];
        // for (int i = 0; i < result.size(); i++) {
        //     arr[i] = result.get(i);
        // }

        // return arr;
    }
}
