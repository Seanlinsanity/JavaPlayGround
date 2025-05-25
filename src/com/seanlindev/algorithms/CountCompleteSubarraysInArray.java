package com.seanlindev.algorithms;

/*
You are given an array nums consisting of positive integers.

We call a subarray of an array complete if the following condition is satisfied:

The number of distinct elements in the subarray is equal to the number of distinct elements in the whole array.
Return the number of complete subarrays.

A subarray is a contiguous non-empty part of an array.



Example 1:

Input: nums = [1,3,1,2,2]
Output: 4
Explanation: The complete subarrays are the following: [1,3,1,2], [1,3,1,2,2], [3,1,2] and [3,1,2,2].
Example 2:

Input: nums = [5,5,5,5]
Output: 10
Explanation: The array consists only of the integer 5, so any subarray is complete. The number of subarrays that we can choose is 10.


Constraints:

1 <= nums.length <= 1000
1 <= nums[i] <= 2000
 */

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CountCompleteSubarraysInArray {
    public int countCompleteSubarrays(int[] nums) {
        Set<Integer> numSet = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            numSet.add(num);
        }

        int result = 0;
        int left = 0, right = 0;
        int count = numSet.size();
        Map<Integer, Integer> numCountMap = new HashMap<>();
        while (right < nums.length) {
            numCountMap.put(nums[right], numCountMap.getOrDefault(nums[right], 0) + 1);
            if (numCountMap.keySet().size() < count) {
                right += 1;
            } else {
                while (numCountMap.keySet().size() == count) {
                    result += (nums.length - right);
                    int leftNum = nums[left];
                    int leftCount = numCountMap.get(leftNum);
                    if (leftCount == 1) {
                        numCountMap.remove(leftNum);
                    } else {
                        numCountMap.put(leftNum, leftCount - 1);
                    }
                    left += 1;
                }

                right += 1;
            }
        }

        return result;
    }
}
