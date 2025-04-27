package com.seanlindev.algorithms;

/*
Given two sorted arrays nums1 and nums2 of size m and n respectively, return the median of the two sorted arrays.

The overall run time complexity should be O(log (m+n)).



Example 1:

Input: nums1 = [1,3], nums2 = [2]
Output: 2.00000
Explanation: merged array = [1,2,3] and median is 2.
Example 2:

Input: nums1 = [1,2], nums2 = [3,4]
Output: 2.50000
Explanation: merged array = [1,2,3,4] and median is (2 + 3) / 2 = 2.5.


Constraints:

nums1.length == m
nums2.length == n
0 <= m <= 1000
0 <= n <= 1000
1 <= m + n <= 2000
-106 <= nums1[i], nums2[i] <= 106
 */
public class MedianOfTwoSortedArrays {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1.length == 0) {
            return nums2.length % 2 == 0 ?
                    ((double)nums2[nums2.length / 2] + (double)nums2[(nums2.length / 2) - 1]) / 2 : (double)nums2[nums2.length / 2];
        }

        if (nums2.length == 0) {
            return nums1.length % 2 == 0 ?
                    ((double)nums1[nums1.length / 2] + (double)nums1[(nums1.length / 2) - 1]) / 2 : (double)nums1[nums1.length / 2];
        }

        int[] numsA = nums1.length < nums2.length ? nums1 : nums2;
        int[] numsB = nums1.length < nums2.length ? nums2 : nums1;
        int total = nums1.length + nums2.length;
        int half = total / 2; // 1

        int left = 0; //l=0
        int right = numsA.length - 1; //r=0 -> -1
        while (true) {
            int mid = (left + right) / 2; // mid = 0
            int idxB = half - (mid + 1) - 1;  //idxB = -1

            int leftA = mid >= 0 ? numsA[mid] : Integer.MIN_VALUE; //leftA = 2
            int rightA = mid + 1 < numsA.length ? numsA[mid + 1] : Integer.MAX_VALUE; //rightA = max
            int leftB = idxB >= 0 ? numsB[idxB] : Integer.MIN_VALUE; //leftB = min
            int rightB = idxB + 1 < numsB.length ? numsB[idxB + 1] : Integer.MAX_VALUE; //rightB = 1

            if (leftA <= rightB && leftB <= rightA) {
                if (total % 2 == 0) {
                    return ((double)(Math.max(leftA, leftB)) + (double)(Math.min(rightA, rightB))) / 2;
                } else {
                    return (double)(Math.min(rightA, rightB));
                }
            } else {
                if (leftA > rightB) {
                    right = mid - 1; //r=-1
                } else {
                    left = mid + 1;
                }
            }

            if (right < left) {
                left = right;
            } else if (left > right) {
                right = left;
            }
        }
    }
}
