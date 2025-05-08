package com.seanlindev.algorithms;

import java.util.Deque;
import java.util.LinkedList;

/*
Given an integer array nums and an integer k, return the length of the shortest non-empty subarray of nums with a sum of at least k. If there is no such subarray, return -1.

A subarray is a contiguous part of an array.



Example 1:

Input: nums = [1], k = 1
Output: 1
Example 2:

Input: nums = [1,2], k = 4
Output: -1
Example 3:

Input: nums = [2,-1,2], k = 3
Output: 3


Constraints:

1 <= nums.length <= 105
-105 <= nums[i] <= 105
1 <= k <= 109
 */

public class ShortestSubarraySumAtLeastK {
    public int shortestSubarray(int[] nums, int k) {
        int result = Integer.MAX_VALUE;
        long prefix = 0;
        Deque<long[]> deque = new LinkedList<>();
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (num >= k) { return 1; }

            prefix += (long)num;
            if (prefix >= (long)k) {
                result = Math.min(result, i + 1);
            }
            long target = prefix - k;
            while (deque.size() > 0 && deque.peekFirst()[0] <= target) {
                long[] remove = deque.removeFirst();
                long index = remove[1];
                result = Math.min(result, i - (int)index);
            }

            while (deque.size() > 0) {
                if (deque.peekLast()[0] >= prefix) {
                    deque.removeLast();
                } else {
                    break;
                }
            }

            deque.addLast(new long[]{ prefix, (long)i });
        }

        return result == Integer.MAX_VALUE ? -1 : result;
    }
}
