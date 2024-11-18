package com.seanlindev.algorithms;

import java.util.Arrays;

/*
You are given an integer array nums and an integer k.

In one operation, you can pick two numbers from the array whose sum equals k and remove them from the array.

Return the maximum number of operations you can perform on the array.



Example 1:

Input: nums = [1,2,3,4], k = 5
Output: 2
Explanation: Starting with nums = [1,2,3,4]:
- Remove numbers 1 and 4, then nums = [2,3]
- Remove numbers 2 and 3, then nums = []
There are no more pairs that sum up to 5, hence a total of 2 operations.
Example 2:

Input: nums = [3,1,3,4,3], k = 6
Output: 1
Explanation: Starting with nums = [3,1,3,4,3]:
- Remove the first two 3's, then nums = [1,4,3]
There are no more pairs that sum up to 6, hence a total of 1 operation.


Constraints:

1 <= nums.length <= 105
1 <= nums[i] <= 109
1 <= k <= 109
 */

public class MaxNumberOfKSumPairs {
    public int maxOperations(int[] nums, int k) {
        // Map<Integer, Integer> map = new HashMap<>();
        // for (int num: nums) {
        //     map.put(num, map.getOrDefault(num, 0) + 1);
        // }

        // int index = 0;
        // int count = 0;
        // List<Integer> keys = new ArrayList<>(map.keySet());
        // while (index < keys.size()) {
        //     int num = keys.get(index);
        //     if (map.get(num) == null) {
        //         index += 1;
        //         continue;
        //     }

        //     int target = k - num;
        //     if (map.get(target) != null) {
        //         if (target == num) {
        //             if (map.get(num) >= 2) {
        //                 count += 1;
        //                 map.put(num, map.get(num) == 2 ? null : map.get(num) - 2);
        //             } else {
        //                 index += 1;
        //             }
        //         } else {
        //             count += 1;
        //             map.put(num, map.get(num) == 1 ? null : map.get(num) - 1);
        //             map.put(target, map.get(target) == 1 ? null : map.get(target) - 1);
        //         }
        //     } else {
        //         index += 1;
        //     }
        // }

        // return count;

        Arrays.sort(nums);
        int left = 0;
        int right = nums.length - 1;
        int count = 0;
        while (left < right) {
            if (nums[left] + nums[right] == k) {
                count += 1;
                left += 1;
                right -= 1;
            } else if (nums[left] + nums[right] > k) {
                right -= 1;
            } else {
                left += 1;
            }
        }
        return count;
    }
}
