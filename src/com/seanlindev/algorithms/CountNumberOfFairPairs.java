package com.seanlindev.algorithms;

import java.util.Arrays;

/*
Given a 0-indexed integer array nums of size n and two integers lower and upper, return the number of fair pairs.

A pair (i, j) is fair if:

0 <= i < j < n, and
lower <= nums[i] + nums[j] <= upper


Example 1:

Input: nums = [0,1,7,4,4,5], lower = 3, upper = 6
Output: 6
Explanation: There are 6 fair pairs: (0,3), (0,4), (0,5), (1,3), (1,4), and (1,5).
Example 2:

Input: nums = [1,7,9,2,5], lower = 11, upper = 11
Output: 1
Explanation: There is a single fair pair: (2,3).


Constraints:

1 <= nums.length <= 105
nums.length == n
-109 <= nums[i] <= 109
-109 <= lower <= upper <= 109
 */
public class CountNumberOfFairPairs {
    public long countFairPairs(int[] nums, int lower, int upper) {
        Arrays.sort(nums);
        return findPairs(nums, upper + 1) - findPairs(nums, lower);
    }

    /*
     [0,1,4,4,5,7]   6
      ^         ^
      ^       ^
        ^     ^
        ^   ^
          ^ ^
    */
    long findPairs(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        long count = 0;
        while (left < right) {
            int sum = nums[left] + nums[right];
            if (sum >= target) {
                right -= 1;
            } else {
                count += (long)(right - left);
                left += 1;
            }
        }

        return count;
    }

    // find the largest index that the num is less than upperBound
    // [0,1,4,4,5,7]
    // (1,5,7)
    int binarySearch(int[] nums, int start, int end, int upperBound) {
        int l = start; //1 -> 4 -> 5
        int r = end; //5
        int result = -1; //1
        while (l <= r) {
            int mid = (r - l) / 2 + l; //3 -> 4 -> 5
            if (nums[mid] >= upperBound) { //
                r = mid - 1;
            } else {
                result = Math.max(result, mid); //result=4
                l = mid + 1;//5
            }
        }

        return result;
    }
}
