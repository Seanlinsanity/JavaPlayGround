package com.seanlindev.algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
Given a set of distinct positive integers nums, return the largest subset answer such that every pair (answer[i], answer[j]) of elements in this subset satisfies:

answer[i] % answer[j] == 0, or
answer[j] % answer[i] == 0
If there are multiple solutions, return any of them.



Example 1:

Input: nums = [1,2,3]
Output: [1,2]
Explanation: [1,3] is also accepted.
Example 2:

Input: nums = [1,2,4,8]
Output: [1,2,4,8]


Constraints:

1 <= nums.length <= 1000
1 <= nums[i] <= 2 * 109
All the integers in nums are unique.
 */
public class LargestDivisibleSubset {
    public List<Integer> largestDivisibleSubset(int[] nums) {
        if (nums.length == 1) { return List.of(nums[0]); }
        Arrays.sort(nums);

        List<Integer>[] dp = new ArrayList[nums.length];
        List<Integer> result = new ArrayList<>(List.of(nums[nums.length - 1]));
        dp[nums.length - 1] = result;
        for (int i = nums.length - 2; i >= 0; i--) {
            List<Integer> max = new ArrayList<>(List.of(nums[i]));
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] % nums[i] == 0) {
                    if (dp[j].size() + 1 > max.size()) {
                        List<Integer> list = new ArrayList<>(List.of(nums[i]));
                        list.addAll(dp[j]);
                        max = list;
                    }
                }
            }

            dp[i] = max;
            if (max.size() > result.size()) {
                result = max;
            }
        }
        return result;
    }
}
