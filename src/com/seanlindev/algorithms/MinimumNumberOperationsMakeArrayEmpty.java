package com.seanlindev.algorithms;

import java.util.HashMap;
import java.util.Map;

/*
You are given a 0-indexed array nums consisting of positive integers.

There are two types of operations that you can apply on the array any number of times:

Choose two elements with equal values and delete them from the array.
Choose three elements with equal values and delete them from the array.
Return the minimum number of operations required to make the array empty, or -1 if it is not possible.



Example 1:

Input: nums = [2,3,3,2,2,4,2,3,4]
Output: 4
Explanation: We can apply the following operations to make the array empty:
- Apply the first operation on the elements at indices 0 and 3. The resulting array is nums = [3,3,2,4,2,3,4].
- Apply the first operation on the elements at indices 2 and 4. The resulting array is nums = [3,3,4,3,4].
- Apply the second operation on the elements at indices 0, 1, and 3. The resulting array is nums = [4,4].
- Apply the first operation on the elements at indices 0 and 1. The resulting array is nums = [].
It can be shown that we cannot make the array empty in less than 4 operations.
Example 2:

Input: nums = [2,1,2,2,3,3]
Output: -1
Explanation: It is impossible to empty the array.


Constraints:

2 <= nums.length <= 105
1 <= nums[i] <= 106
 */
public class MinimumNumberOperationsMakeArrayEmpty {
    public int minOperations(int[] nums) {
        // edge case
        if (nums.length == 1) { return -1; }

        Map<Integer, Integer> elementCount = new HashMap<>(); // Changed from 'counter' to 'elementCount' for clarity
        for (int num : nums) {
            elementCount.put(num, elementCount.getOrDefault(num, 0) + 1);
        }
        int totalOperations = 0; // Changed from 'ans' to 'totalOperations' for clarity
        for (int count : elementCount.values()) {
            if (count == 1) {
                return -1; // It's not possible to make the array empty
            }
            totalOperations += Math.ceil((double) count / 3);
        }
        return totalOperations;

        // Map<Integer, Integer> numCountMap = new HashMap<>();
        // int maxCount = 0;
        // for (int num: nums) {
        //     numCountMap.put(num, numCountMap.getOrDefault(num, 0) + 1);
        //     maxCount = Math.max(maxCount, numCountMap.get(num));
        // }

        // if (maxCount == 1) { return -1; }
        // int[] dp = new int[Math.max(maxCount + 1, 4)];
        // Arrays.fill(dp, Integer.MAX_VALUE);
        // dp[2] = 1;
        // dp[3] = 1;
        // for (int i = 4; i < maxCount + 1; i++) {
        //     int count = Integer.MAX_VALUE;
        //     if (i % 3 == 0) {
        //         dp[i] = i / 3;
        //         continue;
        //     }

        //     if (dp[i - 2] != Integer.MAX_VALUE) {
        //         count = Math.min(count, 1 + dp[i - 2]);
        //     }

        //     if (dp[i - 3] != Integer.MAX_VALUE) {
        //         count = Math.min(count, 1 + dp[i - 3]);
        //     }

        //     dp[i] = count;
        // }

        // int totalOperations = 0;
        // for (int count: numCountMap.values()) {
        //     if (dp[count] == Integer.MAX_VALUE) {
        //         return -1;
        //     }

        //     totalOperations += dp[count];
        // }

        // return totalOperations;
    }
}
