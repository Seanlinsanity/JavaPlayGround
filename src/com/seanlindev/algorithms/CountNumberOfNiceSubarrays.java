package com.seanlindev.algorithms;

/*
Given an array of integers nums and an integer k. A continuous subarray is called nice if there are k odd numbers on it.

Return the number of nice sub-arrays.



Example 1:

Input: nums = [1,1,2,1,1], k = 3
Output: 2
Explanation: The only sub-arrays with 3 odd numbers are [1,1,2,1] and [1,2,1,1].
Example 2:

Input: nums = [2,4,6], k = 1
Output: 0
Explanation: There are no odd numbers in the array.
Example 3:

Input: nums = [2,2,2,1,2,2,1,2,2,2], k = 2
Output: 16


Constraints:

1 <= nums.length <= 50000
1 <= nums[i] <= 10^5
1 <= k <= nums.length
 */
public class CountNumberOfNiceSubarrays {
    public int numberOfSubarrays(int[] nums, int k) {
        int[] nextOddIndex = new int[nums.length];
        int prev = -1;
        for (int i = nums.length - 1; i >= 0; i--) {
            nextOddIndex[i] = prev;
            if (nums[i] % 2 != 0) {
                prev = i;
            }
        }

        int l = 0;
        int r = 0;
        int count = 0;
        int result = 0;
        while (r < nums.length) {
            if (nums[r] % 2 != 0) {
                count += 1;
            }

            while (count == k) {
                int endIndex = nextOddIndex[r] == -1 ? nums.length : nextOddIndex[r];
                result += (endIndex - r);

                int leftNum = nums[l];
                if (leftNum % 2 != 0) {
                    count -= 1;
                }
                l += 1;
            }

            r += 1;
        }

        return result;
    }
}
