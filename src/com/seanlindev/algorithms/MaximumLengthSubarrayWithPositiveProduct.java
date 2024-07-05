package com.seanlindev.algorithms;

/*
Given an array of integers nums, find the maximum length of a subarray where the product of all its elements is positive.

A subarray of an array is a consecutive sequence of zero or more values taken out of that array.

Return the maximum length of a subarray with positive product.



Example 1:

Input: nums = [1,-2,-3,4]
Output: 4
Explanation: The array nums already has a positive product of 24.
Example 2:

Input: nums = [0,1,-2,-3,-4]
Output: 3
Explanation: The longest subarray with positive product is [1,-2,-3] which has a product of 6.
Notice that we cannot include 0 in the subarray since that'll make the product 0 which is not positive.
Example 3:

Input: nums = [-1,-2,-3,0,1]
Output: 2
Explanation: The longest subarray with positive product is [-1,-2] or [-2,-3].


Constraints:

1 <= nums.length <= 105
-109 <= nums[i] <= 109
 */
public class MaximumLengthSubarrayWithPositiveProduct {
    public int getMaxLen(int[] nums) {
        if (nums.length == 1) { return nums[0] > 0 ? 1 : 0; }

        int result = 0;
        int negativeLength = 0;
        int positiveLength = 0;
        for (int num: nums) {
            if (num == 0) {
                negativeLength = 0;
                positiveLength = 0;
            } else if (num > 0) {
                negativeLength = (negativeLength > 0) ? negativeLength + 1 : 0;
                positiveLength += 1;
            } else if (num < 0) {
                int temp = negativeLength;
                negativeLength = positiveLength + 1;
                positiveLength = (temp > 0) ? temp + 1 : 0;
            }
            result = Math.max(result, positiveLength);
        }

        return result;
    }
}
