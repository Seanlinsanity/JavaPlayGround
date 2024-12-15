package com.seanlindev.algorithms;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/*
You are given an integer array nums that is sorted in non-decreasing order.

Determine if it is possible to split nums into one or more subsequences such that both of the following conditions are true:

Each subsequence is a consecutive increasing sequence (i.e. each integer is exactly one more than the previous integer).
All subsequences have a length of 3 or more.
Return true if you can split nums according to the above conditions, or false otherwise.

A subsequence of an array is a new array that is formed from the original array by deleting some (can be none) of the elements without disturbing the relative positions of the remaining elements. (i.e., [1,3,5] is a subsequence of [1,2,3,4,5] while [1,3,2] is not).



Example 1:

Input: nums = [1,2,3,3,4,5]
Output: true
Explanation: nums can be split into the following subsequences:
[1,2,3,3,4,5] --> 1, 2, 3
[1,2,3,3,4,5] --> 3, 4, 5
Example 2:

Input: nums = [1,2,3,3,4,4,5,5]
Output: true
Explanation: nums can be split into the following subsequences:
[1,2,3,3,4,4,5,5] --> 1, 2, 3, 4, 5
[1,2,3,3,4,4,5,5] --> 3, 4, 5
Example 3:

Input: nums = [1,2,3,4,4,5]
Output: false
Explanation: It is impossible to split nums into consecutive increasing subsequences of length 3 or more.


Constraints:

1 <= nums.length <= 104
-1000 <= nums[i] <= 1000
nums is sorted in non-decreasing order.
 */
public class SplitArrayIntoConsecutiveSubsequences {
    public boolean isPossible(int[] nums) {
        // HashMap to store the end element of each subsequence and a priority queue of their lengths
        Map<Integer, PriorityQueue<Integer>> seqEndMap = new HashMap<>();

        // Iterate over each number in the input array
        for (int value : nums) {
            // Get the priority queue of subsequences that end with value - 1
            PriorityQueue<Integer> lengths  = seqEndMap.get(value - 1);
            // If there's a sequence to append to, which ends with the current value - 1
            if (lengths != null) {
                // Remove the shortest subsequence from the queue and increment length
                int length = lengths.poll() + 1;
                // If the current priority queue becomes empty after removal, remove the mapping
                if (lengths.isEmpty()) {
                    seqEndMap.remove(value - 1);
                }
                // Append the incremented length to the current value's priority queue
                seqEndMap.putIfAbsent(value, new PriorityQueue<>());
                seqEndMap.get(value).add(length);
            } else {
                // No preceding subsequences, start a new one with length 1
                seqEndMap.putIfAbsent(value, new PriorityQueue<>());
                seqEndMap.get(value).add(1);
            }
        }

        // Validate the subsequences' lengths
        for (PriorityQueue<Integer> lengths : seqEndMap.values()) {
            // If the shortest subsequence is less than 3, fail the check
            if (lengths.peek() < 3) {
                return false;
            }
        }

        // All subsequences have a length >= 3, the split is possible
        return true;
    }
}
