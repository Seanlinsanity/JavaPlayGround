package com.seanlindev.algorithms;

import java.util.Arrays;

/*
Given two integer arrays nums1 and nums2, return the maximum length of a subarray that appears in both arrays.



Example 1:

Input: nums1 = [1,2,3,2,1], nums2 = [3,2,1,4,7]
Output: 3
Explanation: The repeated subarray with maximum length is [3,2,1].
Example 2:

Input: nums1 = [0,0,0,0,0], nums2 = [0,0,0,0,0]
Output: 5
Explanation: The repeated subarray with maximum length is [0,0,0,0,0].


Constraints:

1 <= nums1.length, nums2.length <= 1000
0 <= nums1[i], nums2[i] <= 100
 */
public class MaxLengthOfRepeatedSubarray {
    public int findLength(int[] nums1, int[] nums2) {
        int maxLength = 0;
        int[][] dp = new int[nums1.length + 1][nums2.length + 1];
        for (int row = nums1.length - 1; row >= 0; row--) {
            for (int column = nums2.length - 1; column >= 0; column--) {
                if (nums1[row] == nums2[column]) {
                    int legnth = 1 + dp[row + 1][column + 1];
                    maxLength = Math.max(maxLength, legnth);
                    dp[row][column] = legnth;
                } else {
                    dp[row][column] = 0;
                }
            }
        }

        return maxLength;
    }

    int maxLength = 0;
    public int findLength2(int[] nums1, int[] nums2) {
        int[][] dp = new int[nums1.length][nums2.length];
        for (int[] array: dp) {
            Arrays.fill(array, -1);
        }
        maxLengthRepeatedSubarray(0, 0, nums1, nums2, dp);
        return maxLength;
    }

    //0,0
    //1,0
    //0,1
    //1 + (1,2)
    //1 + (2,3)
    //1 + (3,4)
    // 1 + (4,5)
    int maxLengthRepeatedSubarray(int index1, int index2, int[] nums1, int[] nums2, int[][] dp) {
        if (index1 >= nums1.length || index2 >= nums2.length) { return 0; }

        if (dp[index1][index2] > -1) {
            return dp[index1][index2];
        }

        int result = 0;
        if (nums1[index1] == nums2[index2]) {
            result = 1 + maxLengthRepeatedSubarray(index1 + 1, index2 + 1, nums1, nums2, dp);
            maxLength = Math.max(maxLength, result);
        }

        maxLengthRepeatedSubarray(index1 + 1, index2, nums1, nums2, dp);
        maxLengthRepeatedSubarray(index1, index2 + 1, nums1, nums2, dp);
        dp[index1][index2] = result;
        return result;
    }
}
