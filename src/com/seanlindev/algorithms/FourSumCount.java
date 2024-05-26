package com.seanlindev.algorithms;

import java.util.HashMap;
import java.util.Map;

/*
Given four integer arrays nums1, nums2, nums3, and nums4 all of length n, return the number of tuples (i, j, k, l) such that:

0 <= i, j, k, l < n
nums1[i] + nums2[j] + nums3[k] + nums4[l] == 0


Example 1:

Input: nums1 = [1,2], nums2 = [-2,-1], nums3 = [-1,2], nums4 = [0,2]
Output: 2
Explanation:
The two tuples are:
1. (0, 0, 0, 1) -> nums1[0] + nums2[0] + nums3[0] + nums4[1] = 1 + (-2) + (-1) + 2 = 0
2. (1, 1, 0, 0) -> nums1[1] + nums2[1] + nums3[0] + nums4[0] = 2 + (-1) + (-1) + 0 = 0
Example 2:

Input: nums1 = [0], nums2 = [0], nums3 = [0], nums4 = [0]
Output: 1


Constraints:

n == nums1.length
n == nums2.length
n == nums3.length
n == nums4.length
1 <= n <= 200
-228 <= nums1[i], nums2[i], nums3[i], nums4[i] <= 228
 */
public class FourSumCount {
    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        int n = nums1.length;
        if (n == 1) {
            return nums1[0] + nums2[0] + nums3[0] + nums4[0] == 0 ? 1 : 0;
        }

        Map<Integer, Integer> sumMap = new HashMap<>();
        // Compute all possible sums of pairs from nums1 and nums2 and store in sumMap
        for (int a : nums1) {
            for (int b : nums2) {
                sumMap.put(a + b, sumMap.getOrDefault(a + b, 0) + 1);
            }
        }

        int count = 0;
        // For each pair sum from nums3 and nums4, check if the inverse sum exists in sumMap
        for (int c : nums3) {
            for (int d : nums4) {
                count += sumMap.getOrDefault(-(c + d), 0);
            }
        }

        return count;

        // Arrays.sort(nums1);
        // Arrays.sort(nums2);
        // Arrays.sort(nums3);
        // Arrays.sort(nums4);
        // int result = 0;
        // for (int i = 0; i < n; i++) {
        //     int num1 = nums1[i];
        //     int threeSum = 0 - num1;
        //     for (int j = 0; j < n; j++) {
        //         int num2 = nums2[j];
        //         int twoSum = threeSum - num2;
        //         int left = 0;
        //         int right = n - 1;
        //         while (left < n && right >=0) {
        //             int num3 = nums3[left];
        //             int num4 = nums4[right];
        //             if (num3 + num4 == twoSum) {
        //                 int tempLeft = left;
        //                 int tempRight = right;
        //                 while (left < n && nums3[left] == num3) {
        //                     left += 1;
        //                 }

        //                 while (right >= 0 && nums4[right] == num4) {
        //                     right -= 1;
        //                 }

        //                result += (tempLeft - left) * (right - tempRight);
        //             } else if (num3 + num4 > twoSum) {
        //                 right -= 1;
        //             } else {
        //                 left += 1;
        //             }
        //         }
        //     }
        // }

        // return result;

    }
}
