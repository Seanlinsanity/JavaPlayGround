package com.seanlindev.algorithms;

/*
Given an array nums with n objects colored red, white, or blue, sort them in-place so that objects of the same color are adjacent, with the colors in the order red, white, and blue.

We will use the integers 0, 1, and 2 to represent the color red, white, and blue, respectively.

You must solve this problem without using the library's sort function.



Example 1:

Input: nums = [2,0,2,1,1,0]
Output: [0,0,1,1,2,2]
Example 2:

Input: nums = [2,0,1]
Output: [0,1,2]


Constraints:

n == nums.length
1 <= n <= 300
nums[i] is either 0, 1, or 2.


Follow up: Could you come up with a one-pass algorithm using only constant extra space?
 */

public class SortColors {
    public void sortColors(int[] nums) {
        // edge case
        if (nums.length <= 1) { return; }

        int current = 0;
        int zeroIndex = 0;
        int twoIndex = nums.length - 1;
        while (current <= twoIndex) {
            if (nums[current] == 1) {
                current += 1;
            } else if (nums[current] == 2) {
                swap(nums, current, twoIndex);
                twoIndex -= 1;
            } else {
                if (current == zeroIndex) {
                    current += 1;
                    zeroIndex += 1;
                } else {
                    swap(nums, current, zeroIndex);
                    zeroIndex += 1;
                }
            }
        }
    }

    void swap(int[] nums, int index1, int index2) {
        int temp = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = temp;
    }
}
