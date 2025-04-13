package com.seanlindev.algorithms;

/*
Given an integer array nums and an integer k, split nums into k non-empty subarrays such that the largest sum of any subarray is minimized.

Return the minimized largest sum of the split.

A subarray is a contiguous part of the array.



Example 1:

Input: nums = [7,2,5,10,8], k = 2
Output: 18
Explanation: There are four ways to split nums into two subarrays.
The best way is to split it into [7,2,5] and [10,8], where the largest sum among the two subarrays is only 18.
Example 2:

Input: nums = [1,2,3,4,5], k = 2
Output: 9
Explanation: There are four ways to split nums into two subarrays.
The best way is to split it into [1,2,3] and [4,5], where the largest sum among the two subarrays is only 9.


Constraints:

1 <= nums.length <= 1000
0 <= nums[i] <= 106
1 <= k <= min(50, nums.length)
 */
public class SplitArrayLargestSum {
    public int splitArray(int[] nums, int k) {
        int sum = 0;
        int max = 0;
        for (int num: nums) {
            sum += num;
            max = Math.max(max, num);
        }

        int low = max;
        int high = sum;
        int result = high;
        while (low <= high) {
            int mid = (low + high) / 2;
            boolean isValid = isValid(mid, nums, k);
            if (isValid) {
                result = Math.min(result, mid);
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return result;
    }

    boolean isValid(int target, int[] nums, int k) {
        int count = 0;
        int currentSum = 0;
        for (int num: nums) {
            if (currentSum + num <= target) {
                currentSum += num;
            } else {
                count += 1;
                currentSum = num;
            }
        }

        count += 1;
        return count <= k;
    }
}
