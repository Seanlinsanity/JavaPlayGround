package com.seanlindev.algorithms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
Given an integer array of size n, find all elements that appear more than ⌊ n/3 ⌋ times.



Example 1:

Input: nums = [3,2,3]
Output: [3]
Example 2:

Input: nums = [1]
Output: [1]
Example 3:

Input: nums = [1,2]
Output: [1,2]


Constraints:

1 <= nums.length <= 5 * 104
-109 <= nums[i] <= 109


Follow up: Could you solve the problem in linear time and in O(1) space?
 */
public class MajorityElementII {
    public List<Integer> majorityElement(int[] nums) {
        Map<Integer, Integer> numCountMap = new HashMap<>();
        for (int num: nums) {
            numCountMap.put(num, numCountMap.getOrDefault(num, 0) + 1);
            if (numCountMap.keySet().size() > 2) {
                List<Integer> keys = new ArrayList<>(numCountMap.keySet());
                for (Integer key: keys) {
                    int count = numCountMap.get(key) - 1;
                    if (count == 0) {
                        numCountMap.remove(key);
                    } else {
                        numCountMap.put(key, count);
                    }
                }
            }
        }

        List<Integer> result = new ArrayList<>();
        for (Integer key: numCountMap.keySet()) {
            int count = 0;
            for (int num: nums) {
                if (num == key) {
                    count += 1;
                }
            }

            if (count > nums.length / 3) {
                result.add(key);
            }
        }

        return result;
    }
}
