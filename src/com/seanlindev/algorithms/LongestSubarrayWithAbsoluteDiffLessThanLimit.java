package com.seanlindev.algorithms;

import java.util.Deque;
import java.util.LinkedList;

/*
Given an array of integers nums and an integer limit, return the size of the longest non-empty subarray such that the absolute difference between any two elements of this subarray is less than or equal to limit.



Example 1:

Input: nums = [8,2,4,7], limit = 4
Output: 2
Explanation: All subarrays are:
[8] with maximum absolute diff |8-8| = 0 <= 4.
[8,2] with maximum absolute diff |8-2| = 6 > 4.
[8,2,4] with maximum absolute diff |8-2| = 6 > 4.
[8,2,4,7] with maximum absolute diff |8-2| = 6 > 4.
[2] with maximum absolute diff |2-2| = 0 <= 4.
[2,4] with maximum absolute diff |2-4| = 2 <= 4.
[2,4,7] with maximum absolute diff |2-7| = 5 > 4.
[4] with maximum absolute diff |4-4| = 0 <= 4.
[4,7] with maximum absolute diff |4-7| = 3 <= 4.
[7] with maximum absolute diff |7-7| = 0 <= 4.
Therefore, the size of the longest subarray is 2.
Example 2:

Input: nums = [10,1,2,4,7,2], limit = 5
Output: 4
Explanation: The subarray [2,4,7,2] is the longest since the maximum absolute diff is |2-7| = 5 <= 5.
Example 3:

Input: nums = [4,2,2,2,4,4,2,2], limit = 0
Output: 3


Constraints:

1 <= nums.length <= 105
1 <= nums[i] <= 109
0 <= limit <= 109
 */
public class LongestSubarrayWithAbsoluteDiffLessThanLimit {
    public int longestSubarray(int[] nums, int limit) {
        if (nums.length == 1) { return 1; }

        int maxLength = 0;
        int left = 0;
        int right = 0;
        Deque<int[]> minDeque = new LinkedList<>();
        Deque<int[]> maxDeque = new LinkedList<>();
        while (right < nums.length) {
            while (minDeque.size() > 0 && minDeque.peekLast()[0] > nums[right]) {
                minDeque.removeLast();
            }
            minDeque.addLast(new int[]{ nums[right], right});

            while (maxDeque.size() > 0 && maxDeque.peekLast()[0] < nums[right]) {
                maxDeque.removeLast();
            }
            maxDeque.addLast(new int[]{ nums[right], right});

            while (maxDeque.peekFirst()[0] - minDeque.peekFirst()[0] > limit) {
                left += 1;
                if (minDeque.peekFirst()[1] < left) {
                    minDeque.removeFirst();
                }

                if (maxDeque.peekFirst()[1] < left) {
                    maxDeque.removeFirst();
                }
            }

            maxLength = Math.max(maxLength, right - left + 1);
            right += 1;
        }

        return maxLength;
    }
}
