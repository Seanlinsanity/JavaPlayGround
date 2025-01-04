package com.seanlindev.algorithms;

/*
Given a binary array nums and an integer k, return the maximum number of consecutive 1's in the array if you can flip at most k 0's.



Example 1:

Input: nums = [1,1,1,0,0,0,1,1,1,1,0], k = 2
Output: 6
Explanation: [1,1,1,0,0,1,1,1,1,1,1]
Bolded numbers were flipped from 0 to 1. The longest subarray is underlined.
Example 2:

Input: nums = [0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1], k = 3
Output: 10
Explanation: [0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,1,1,1,1]
Bolded numbers were flipped from 0 to 1. The longest subarray is underlined.


Constraints:

1 <= nums.length <= 105
nums[i] is either 0 or 1.
0 <= k <= nums.length
 */
public class MaxConsecutiveOnesIII {
    public int longestOnes(int[] nums, int k) {
        int left = 0;
        int right = 0;
        int zero = 0;
        int result = 0;
        while (right < nums.length) {
            int num = nums[right];
            if (num == 0) {
                zero += 1;
            }

            while (zero > k) {
                int leftNum = nums[left];
                if (leftNum == 0) {
                    zero -= 1;
                }
                left += 1;
            }

            result = Math.max(result, right - left + 1);
            right += 1;
        }

        return result;
    }
}
