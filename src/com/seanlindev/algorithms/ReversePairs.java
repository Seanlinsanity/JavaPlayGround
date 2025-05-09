package com.seanlindev.algorithms;

/*
Given an integer array nums, return the number of reverse pairs in the array.

A reverse pair is a pair (i, j) where:

0 <= i < j < nums.length and
nums[i] > 2 * nums[j].


Example 1:

Input: nums = [1,3,2,3,1]
Output: 2
Explanation: The reverse pairs are:
(1, 4) --> nums[1] = 3, nums[4] = 1, 3 > 2 * 1
(3, 4) --> nums[3] = 3, nums[4] = 1, 3 > 2 * 1
Example 2:

Input: nums = [2,4,3,5,1]
Output: 3
Explanation: The reverse pairs are:
(1, 4) --> nums[1] = 4, nums[4] = 1, 4 > 2 * 1
(2, 4) --> nums[2] = 3, nums[4] = 1, 3 > 2 * 1
(3, 4) --> nums[3] = 5, nums[4] = 1, 5 > 2 * 1


Constraints:

1 <= nums.length <= 5 * 104
-231 <= nums[i] <= 231 - 1
 */
public class ReversePairs {
    public int reversePairs(int[] nums) {
        return reversePairsRecursion(nums, 0, nums.length - 1);
    }

    int reversePairsRecursion(int[] nums, int left, int right) {
        if (right <= left) { return 0; }
        int mid = (left + right) / 2;
        int leftCount = reversePairsRecursion(nums, left, mid);
        int rightCount = reversePairsRecursion(nums, mid + 1, right);
        int mergeCount = countAndMerge(nums, left, mid, right);
        return leftCount + rightCount + mergeCount;
    }

    int countAndMerge(int[] nums, int left, int mid, int right) {
        int count = 0;
        int i = left;
        int j = mid + 1;
        while (j <= right && i <= mid) {
            long rightNum = (long)nums[j];
            if (nums[i] > 2 * rightNum) {
                count += (mid - i + 1);
                j += 1;
            } else {
                i += 1;
            }
        }

        int[] newNums = new int[right - left + 1];
        int idx1 = left;
        int idx2 = mid + 1;
        int idx = 0;
        while (idx1 <= mid && idx2 <= right) {
            if (nums[idx1] >= nums[idx2]) {
                newNums[idx] = nums[idx2];
                idx2 += 1;
                idx += 1;
            } else {
                newNums[idx] = nums[idx1];
                idx1 += 1;
                idx += 1;
            }
        }

        while (idx1 <= mid) {
            newNums[idx] = nums[idx1];
            idx += 1;
            idx1 += 1;
        }

        while (idx2 <= right) {
            newNums[idx] = nums[idx2];
            idx += 1;
            idx2 += 1;
        }

        for (int k = 0; k < newNums.length; k++) {
            nums[left + k] = newNums[k];
        }

        return count;
    }
}
