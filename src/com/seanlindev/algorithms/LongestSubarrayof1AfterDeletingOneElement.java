package com.seanlindev.algorithms;

/*
Given a binary array nums, you should delete one element from it.

Return the size of the longest non-empty subarray containing only 1's in the resulting array. Return 0 if there is no such subarray.



Example 1:

Input: nums = [1,1,0,1]
Output: 3
Explanation: After deleting the number in position 2, [1,1,1] contains 3 numbers with value of 1's.
Example 2:

Input: nums = [0,1,1,1,0,1,1,0,1]
Output: 5
Explanation: After deleting the number in position 4, [0,1,1,1,1,1,0,1] longest subarray with value of 1's is [1,1,1,1,1].
Example 3:

Input: nums = [1,1,1]
Output: 2
Explanation: You must delete one element.


Constraints:

1 <= nums.length <= 105
nums[i] is either 0 or 1.
 */
public class LongestSubarrayof1AfterDeletingOneElement {
    public int longestSubarray(int[] nums) {
        if (nums.length == 1) { return 0; }

        boolean oneFound = false;
        int result = 0;
        int left = 0;
        int right = 0;
        int zeroCount = 0;
        while (right < nums.length) {
            int num = nums[right];
            if (num == 0) {
                zeroCount += 1;
            } else {
                oneFound = true;
            }
            while (zeroCount > 1) {
                int leftNum = nums[left];
                if (leftNum == 0) {
                    zeroCount -= 1;
                }
                left += 1;
            }

            result = Math.max(result, right - left + 1);
            right += 1;
        }

        return oneFound ? result - 1 : 0;
    }
}
