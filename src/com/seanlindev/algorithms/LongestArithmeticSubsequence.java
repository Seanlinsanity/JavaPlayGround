package com.seanlindev.algorithms;

import java.util.HashMap;
import java.util.Map;

/*
Given an array nums of integers, return the length of the longest arithmetic subsequence in nums.

Note that:

A subsequence is an array that can be derived from another array by deleting some or no elements without changing the order of the remaining elements.
A sequence seq is arithmetic if seq[i + 1] - seq[i] are all the same value (for 0 <= i < seq.length - 1).


Example 1:

Input: nums = [3,6,9,12]
Output: 4
Explanation:  The whole array is an arithmetic sequence with steps of length = 3.
Example 2:

Input: nums = [9,4,7,2,10]
Output: 3
Explanation:  The longest arithmetic subsequence is [4,7,10].
Example 3:

Input: nums = [20,1,15,3,10,5,8]
Output: 4
Explanation:  The longest arithmetic subsequence is [20,15,10,5].


Constraints:

2 <= nums.length <= 1000
0 <= nums[i] <= 500
 */
public class LongestArithmeticSubsequence {
    public int longestArithSeqLength(int[] nums) {
        int n = nums.length;
        // Array of maps to store the length of arithmetic sequence ending with A[i] with a certain difference
        Map<Integer, Integer>[] dp = new HashMap[n];
        int result = 2; // Minimum length of any arithmetic subsequence
        for (int i = 0; i < n; i++) {
            dp[i] = new HashMap<>();
            for (int j = 0; j < i; j++) {
                int diff = nums[i] - nums[j];
                // Compute the length of the longest subsequence with this difference
                dp[i].put(diff, dp[j].getOrDefault(diff, 1) + 1);
                // Update result if a longer subsequence is found
                result = Math.max(result, dp[i].get(diff));
            }
        }
        return result;

        //     Map<String, Integer> dp = new HashMap<>();
        //     return longestArithSeqLengthRecursion(nums, 0, -1, 1000, dp);
    }

    int longestArithSeqLengthRecursion(int[] nums, int i, int lastIdx, int diff, Map<String, Integer> dp) {
        if (i == nums.length) { return 0; }

        String key = String.valueOf(i) + ":" + String.valueOf(lastIdx) + ":" + String.valueOf(diff);
        if (dp.get(key) != null) { return dp.get(key); }

        int result = 0;
        if (lastIdx == -1) {
            result = Math.max(
                    1 + longestArithSeqLengthRecursion(nums, i + 1, i, 1000, dp),
                    longestArithSeqLengthRecursion(nums, i + 1, -1, 1000, dp)
            );
        } else {
            if (diff == 1000) {
                result = Math.max(
                        1 + longestArithSeqLengthRecursion(nums, i + 1, i, nums[i] - nums[lastIdx], dp),
                        longestArithSeqLengthRecursion(nums, i + 1, lastIdx, 1000, dp)
                );
            } else {
                if (nums[i] - nums[lastIdx] == diff) {
                    result = 1 + longestArithSeqLengthRecursion(nums, i + 1, i, diff, dp);
                } else {
                    result = longestArithSeqLengthRecursion(nums, i + 1, lastIdx, diff, dp);
                }
            }
        }

        dp.put(key, result);
        return result;
    }
}
