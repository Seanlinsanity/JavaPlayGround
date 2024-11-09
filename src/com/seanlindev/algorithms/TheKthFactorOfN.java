package com.seanlindev.algorithms;

/*
You are given two positive integers n and k. A factor of an integer n is defined as an integer i where n % i == 0.

Consider a list of all factors of n sorted in ascending order, return the kth factor in this list or return -1 if n has less than k factors.



Example 1:

Input: n = 12, k = 3
Output: 3
Explanation: Factors list is [1, 2, 3, 4, 6, 12], the 3rd factor is 3.
Example 2:

Input: n = 7, k = 2
Output: 7
Explanation: Factors list is [1, 7], the 2nd factor is 7.
Example 3:

Input: n = 4, k = 4
Output: -1
Explanation: Factors list is [1, 2, 4], there is only 3 factors. We should return -1.


Constraints:

1 <= k <= n <= 1000


Follow up:

Could you solve this problem in less than O(n) complexity?
 */
public class TheKthFactorOfN {
    public int kthFactor(int n, int k) {
        int factorCount = 0;
        // Iterate through numbers from 1 to n
        for (int i = 1; i <= n; ++i) {
            // Check if i is a factor of n
            if (n % i == 0) {
                factorCount++; // Increment factor count
                // If factorCount equals k, return i as the kth factor
                if (factorCount == k) {
                    return i;
                }
            }
        }
        // If we finish the loop without finding the kth factor, return -1
        return -1;

        // if (n == 1) { return k <= 1 ? 1 : -1; }
        // List<Integer> nums1 = new ArrayList<>();
        // List<Integer> nums2 = new ArrayList<>();
        // int max = n;
        // int i = 1;
        // while (i < max) {
        //     if (n % i == 0) {
        //         int factor = n / i;
        //         if (factor != i) {
        //             nums2.add(factor);
        //         }
        //         nums1.add(i);
        //         max = factor;
        //     }

        //     i += 1;
        // }

        // if (k > nums1.size() + nums2.size()) { return -1; }

        // if (k <= nums1.size()) {
        //     return nums1.get(k - 1);
        // }

        // int index = (nums2.size() - 1) - (k - nums1.size() - 1);
        // return nums2.get(index);
    }
}
