package com.seanlindev.algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
Given an array nums of n integers, return an array of all the unique quadruplets [nums[a], nums[b], nums[c], nums[d]] such that:

0 <= a, b, c, d < n
a, b, c, and d are distinct.
nums[a] + nums[b] + nums[c] + nums[d] == target
You may return the answer in any order.



Example 1:

Input: nums = [1,0,-1,0,-2,2], target = 0
Output: [[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
Example 2:

Input: nums = [2,2,2,2,2], target = 8
Output: [[2,2,2,2]]


Constraints:

1 <= nums.length <= 200
-109 <= nums[i] <= 109
-109 <= target <= 109
 */
public class FourSum {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        if (nums.length < 4) { return new ArrayList<>(); }

        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < nums.length - 3; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            long remain = (long)target - (long)nums[i];
            int left = i + 1;
            int right = nums.length - 1;
            List<List<Integer>> threeSumResult = threeSum(Arrays.copyOfRange(nums, left, right + 1), remain, nums[i]);
            result.addAll(threeSumResult);
        }

        return result;
    }

    List<List<Integer>> threeSum(int[] nums, long target, int baseNum) {
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            long remain = (long)(target - nums[i]);
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                long current = (long)nums[left] + (long)nums[right];
                if (current == remain) {
                    result.add(new ArrayList<>(List.of(baseNum, nums[i], nums[left], nums[right])));
                    int leftVal = nums[left];
                    while (left < right && nums[left] == leftVal) {
                        left += 1;
                    }
                } else if (current > remain) {
                    right -= 1;
                } else {
                    left += 1;
                }
            }
        }

        return result;
    }
}
