package com.seanlindev.algorithms;

import java.util.Arrays;
import java.util.HashMap;

/*
You are given an array nums of distinct positive integers. You need to sort the array in increasing order based on the sum of the digits of each number. If two numbers have the same digit sum, the smaller number appears first in the sorted order.

Return the minimum number of swaps required to rearrange nums into this sorted order.

A swap is defined as exchanging the values at two distinct positions in the array.



Example 1:

Input: nums = [37,100]

Output: 1

Explanation:

Compute the digit sum for each integer: [3 + 7 = 10, 1 + 0 + 0 = 1] → [10, 1]
Sort the integers based on digit sum: [100, 37]. Swap 37 with 100 to obtain the sorted order.
Thus, the minimum number of swaps required to rearrange nums is 1.
Example 2:

Input: nums = [22,14,33,7]

Output: 0

Explanation:

Compute the digit sum for each integer: [2 + 2 = 4, 1 + 4 = 5, 3 + 3 = 6, 7 = 7] → [4, 5, 6, 7]
Sort the integers based on digit sum: [22, 14, 33, 7]. The array is already sorted.
Thus, the minimum number of swaps required to rearrange nums is 0.
Example 3:

Input: nums = [18,43,34,16]

Output: 2

Explanation:

Compute the digit sum for each integer: [1 + 8 = 9, 4 + 3 = 7, 3 + 4 = 7, 1 + 6 = 7] → [9, 7, 7, 7]
Sort the integers based on digit sum: [16, 34, 43, 18]. Swap 18 with 16, and swap 43 with 34 to obtain the sorted order.
Thus, the minimum number of swaps required to rearrange nums is 2.


Constraints:

1 <= nums.length <= 105
1 <= nums[i] <= 109
nums consists of distinct positive integers.
 */
public class MinimumSwapsSortByDigitSum {
    public int minSwaps(int[] nums) {
        Integer[] target = new Integer[nums.length];
        HashMap<Integer, Integer> pos = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            target[i] = nums[i];
            pos.put(nums[i], i);
        }
        Arrays.sort(target, (a, b) -> {
            int digitA = digitSum(a);
            int digitB = digitSum(b);
            if (digitA == digitB) {
                return Integer.compare(a, b);
            }
            return Integer.compare(digitA, digitB);
        });

        // Array to Keep track of those elements
        // who already been included in the cycle.
        boolean[] vis = new boolean[nums.length];
        int swaps = 0;
        for (int i = 0; i < nums.length; i++) {

            // Already a part of another cycle Or
            // in its correct position
            if (vis[i] || pos.get(target[i]) == i)
                continue;

            int j = i, cycleSize = 0;

            // We make a cycle until it comes
            // back to first element again.
            while (!vis[j]) {
                vis[j] = true;

                // move to next element of the cycle
                j = pos.get(target[j]);
                cycleSize++;
            }

            // Update answer by adding current cycle.
            if (cycleSize > 0) {
                swaps += (cycleSize - 1);
            }
        }
        return swaps;


        // int[][] target = new int[nums.length][2];
        // for (int i = 0; i < nums.length; i++) {
        //     target[i] = new int[]{ digitSum(nums[i]), nums[i], i };
        // }

        // Arrays.sort(target, (a, b) -> {
        //     if (a[0] == b[0]) {
        //         return Integer.compare(a[1], b[1]);
        //     }
        //     return Integer.compare(a[0], b[0]);
        // });

        // int count = 0;
        // for (int i = 0; i < target.length; i++) {
        //     while (target[i][2] != i) {
        //         swap(target, i, target[i][2]);
        //         count += 1;
        //     }
        // }

        // return count;
    }

    int digitSum(int num) {
        int sum = 0;
        while (num > 0) {
            sum += (num % 10);
            num = num / 10;
        }
        return sum;
    }

    void swap(int[][] nums, int i, int j) {
        int[] temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
