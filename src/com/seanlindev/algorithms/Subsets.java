package com.seanlindev.algorithms;

import java.util.ArrayList;
import java.util.List;

/*
Given an integer array nums of unique elements, return all possible
subsets
 (the power set).

The solution set must not contain duplicate subsets. Return the solution in any order.



Example 1:

Input: nums = [1,2,3]
Output: [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
Example 2:

Input: nums = [0]
Output: [[],[0]]


Constraints:

1 <= nums.length <= 10
-10 <= nums[i] <= 10
All the numbers of nums are unique.
 */
public class Subsets {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        track(result, 0, nums, list);
        return result;
    }

    public void track(
        List<List<Integer>> result,
        int start,
        int[] nums,
        List<Integer> list
    ) {
        if (start >= nums.length) {
            result.add(new ArrayList<>(list));
        } else {
            // add the element and start the recursive call
            list.add(nums[start]);
            track(result, start + 1, nums, list);

            // Don't add the element and do the backtracking call.
            list.remove(list.size() - 1);
            track(result, start + 1, nums, list);
        }
    }
}
