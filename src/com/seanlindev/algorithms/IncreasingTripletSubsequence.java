package com.seanlindev.algorithms;

/*
Given an integer array nums, return true if there exists a triple of indices (i, j, k) such that i < j < k and nums[i] < nums[j] < nums[k]. If no such indices exists, return false.



Example 1:

Input: nums = [1,2,3,4,5]
Output: true
Explanation: Any triplet where i < j < k is valid.
Example 2:

Input: nums = [5,4,3,2,1]
Output: false
Explanation: No triplet exists.
Example 3:

Input: nums = [2,1,5,0,4,6]
Output: true
Explanation: The triplet (3, 4, 5) is valid because nums[3] == 0 < nums[4] == 4 < nums[5] == 6.


Constraints:

1 <= nums.length <= 5 * 105
-231 <= nums[i] <= 231 - 1

 */

public class IncreasingTripletSubsequence {
    public boolean increasingTriplet(int[] nums) {
        int firstSmall = Integer.MAX_VALUE, secondSmall = Integer.MAX_VALUE;
        for (int num: nums) {
            if (num <= firstSmall) {
                firstSmall = num;
            } else if (num <= secondSmall) {
                secondSmall = num;
            } else {
                return true;
            }
        }

        return false;

        // int[] max = new int[nums.length];
        // int[] min = new int[nums.length];
        // int maxVal = Integer.MIN_VALUE;
        // int minVal = Integer.MAX_VALUE;
        // for (int i = 0; i < nums.length; i++) {
        //     int num1 = nums[i];
        //     minVal = Math.min(num1, minVal);
        //     min[i] = minVal;
        //     int num2 = nums[nums.length - 1 - i];
        //     maxVal = Math.max(num2, maxVal);
        //     max[nums.length - 1 - i] = maxVal;
        // }

        // for (int i = 1; i < nums.length - 1; i++) {
        //     if (nums[i] > min[i] && nums[i] < max[i]) {
        //         return true;
        //     }
        // }

        // return false;
    }
}
