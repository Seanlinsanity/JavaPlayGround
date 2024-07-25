package com.seanlindev.algorithms;

import java.util.Arrays;
import java.util.Stack;

/*
Given an array of integers arr, find the sum of min(b), where b ranges over every (contiguous) subarray of arr. Since the answer may be large, return the answer modulo 109 + 7.



Example 1:

Input: arr = [3,1,2,4]
Output: 17
Explanation:
Subarrays are [3], [1], [2], [4], [3,1], [1,2], [2,4], [3,1,2], [1,2,4], [3,1,2,4].
Minimums are 3, 1, 2, 4, 1, 1, 2, 1, 1, 1.
Sum is 17.
Example 2:

Input: arr = [11,81,94,43,3]
Output: 444


Constraints:

1 <= arr.length <= 3 * 104
1 <= arr[i] <= 3 * 104
 */

public class SumOfSubarrayMinimums {
    public int sumSubarrayMins(int[] arr) {
        long module = (long)Math.pow(10, 9) + 7;

        int[] nearestRightSmallerIndex = new int[arr.length];
        Arrays.fill(nearestRightSmallerIndex, arr.length);
        Stack<int[]> stack = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            int num = arr[i];
            while (stack.size() > 0 && num <= stack.peek()[1]) {
                int[] node = stack.pop();
                nearestRightSmallerIndex[node[0]] = i;
            }

            stack.push(new int[]{ i, arr[i] });
        }

        int[] nearestLeftSmallerIndex = new int[arr.length];
        Arrays.fill(nearestLeftSmallerIndex, -1);
        stack = new Stack<>();
        for (int i = arr.length - 1; i >= 0; i--) {
            int num = arr[i];
            while (stack.size() > 0 && num < stack.peek()[1]) {
                int[] node = stack.pop();
                nearestLeftSmallerIndex[node[0]] = i;
            }

            stack.push(new int[]{ i, arr[i] });
        }

        long result = 0;
        for (int i = 0; i < arr.length; i++) {
            int num = arr[i];
            int left = nearestLeftSmallerIndex[i];
            int right = nearestRightSmallerIndex[i];
            long count = ((long)(i - left) * (long)(right - i)) % module;
            result += ((num * count) % module);
            result = result % module;
        }

        return (int)result;
    }
}
