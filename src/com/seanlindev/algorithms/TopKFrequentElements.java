package com.seanlindev.algorithms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
Given an integer array nums and an integer k, return the k most frequent elements. You may return the answer in any order.



Example 1:

Input: nums = [1,1,1,2,2,3], k = 2
Output: [1,2]
Example 2:

Input: nums = [1], k = 1
Output: [1]


Constraints:

1 <= nums.length <= 105
-104 <= nums[i] <= 104
k is in the range [1, the number of unique elements in the array].
It is guaranteed that the answer is unique.


Follow up: Your algorithm's time complexity must be better than O(n log n), where n is the array's size.
 */
public class TopKFrequentElements {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> numCountMap = new HashMap<>();
        for (int num: nums) {
            Integer count = numCountMap.get(num);
            if (count == null) {
                count = 0;
            }
            count += 1;
            numCountMap.put(num, count);
        }

        Map<Integer, List<Integer>> frequencyMap = new HashMap<>();
        for (Map.Entry<Integer, Integer> set: numCountMap.entrySet()) {
            Integer num = set.getKey();
            Integer count = set.getValue();
            List<Integer> numList = frequencyMap.get(count);
            if (numList == null) {
                numList = new ArrayList<>();
            }
            numList.add(num);
            frequencyMap.put(count, numList);
        }

        int[] result = new int[k];
        int index = 0;
        for (int count = nums.length; count >= 0; count--){
            List<Integer> numList = frequencyMap.get(count);
            if (numList != null) {
                for (Integer num: numList) {
                    result[index] = num;
                    index += 1;
                }
            }

            if (index == k) { break; }
        }

        return result;
    }
}
