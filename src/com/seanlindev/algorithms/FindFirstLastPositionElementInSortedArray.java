package com.seanlindev.algorithms;

/*
Given an array of integers nums sorted in non-decreasing order, find the starting and ending position of a given target value.

If target is not found in the array, return [-1, -1].

You must write an algorithm with O(log n) runtime complexity.



Example 1:

Input: nums = [5,7,7,8,8,10], target = 8
Output: [3,4]
Example 2:

Input: nums = [5,7,7,8,8,10], target = 6
Output: [-1,-1]
Example 3:

Input: nums = [], target = 0
Output: [-1,-1]


Constraints:

0 <= nums.length <= 105
-109 <= nums[i] <= 109
nums is a non-decreasing array.
-109 <= target <= 109
 */
public class FindFirstLastPositionElementInSortedArray {
    public int[] searchRange(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        int minIndex = -1;
        int nextLeft = -1;
        int nextRight = -1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                if (nextLeft == -1) {
                    nextLeft = left;
                }

                if (nextRight == -1) {
                    nextRight = right;
                }

                minIndex = mid;
                right = mid - 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        if (minIndex == -1) { return new int[]{ -1, -1 }; }

        int maxIndex = -1;
        left = nextLeft;
        right = nextRight;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                maxIndex = mid;
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return new int[]{ minIndex, maxIndex };
    }
}
