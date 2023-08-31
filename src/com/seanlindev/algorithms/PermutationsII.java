package com.seanlindev.algorithms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
Given a collection of numbers, nums, that might contain duplicates, return all possible unique permutations in any order.



Example 1:

Input: nums = [1,1,2]
Output:
[[1,1,2],
 [1,2,1],
 [2,1,1]]
Example 2:

Input: nums = [1,2,3]
Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]


Constraints:

1 <= nums.length <= 8
-10 <= nums[i] <= 10
 */
public class PermutationsII {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Map<Integer, Integer> numCountMap = new HashMap<>();
        for (int num: nums) {
            numCountMap.putIfAbsent(num, 0);
            numCountMap.put(num, numCountMap.get(num) + 1);
        }
        backtracking(numCountMap, new ArrayList<>(), result, nums.length);
        return result;
    }

    void backtracking(
        Map<Integer, Integer> numCountMap,
        List<Integer> currentNums,
        List<List<Integer>> result,
        int length
    ) {
        if (currentNums.size() == length) {
            result.add(new ArrayList<>(currentNums));
            return;
        }

        for (Integer num: numCountMap.keySet()) {
            Integer count = numCountMap.get(num);
            if (count > 0) {
                currentNums.add(num);
                numCountMap.put(num, count - 1);
                backtracking(numCountMap, currentNums, result, length);
                numCountMap.put(num, count);
                currentNums.remove(currentNums.size() - 1);
            }
        }
    }
}
