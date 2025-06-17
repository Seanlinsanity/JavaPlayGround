package com.seanlindev.algorithms;

import java.util.Arrays;

/*
You are given a positive integer 0-indexed array nums.

A subset of the array nums is square-free if the product of its elements is a square-free integer.

A square-free integer is an integer that is divisible by no square number other than 1.

Return the number of square-free non-empty subsets of the array nums. Since the answer may be too large, return it modulo 109 + 7.

A non-empty subset of nums is an array that can be obtained by deleting some (possibly none but not all) elements from nums. Two subsets are different if and only if the chosen indices to delete are different.



Example 1:

Input: nums = [3,4,4,5]
Output: 3
Explanation: There are 3 square-free subsets in this example:
- The subset consisting of the 0th element [3]. The product of its elements is 3, which is a square-free integer.
- The subset consisting of the 3rd element [5]. The product of its elements is 5, which is a square-free integer.
- The subset consisting of 0th and 3rd elements [3,5]. The product of its elements is 15, which is a square-free integer.
It can be proven that there are no more than 3 square-free subsets in the given array.
Example 2:

Input: nums = [1]
Output: 1
Explanation: There is 1 square-free subset in this example:
- The subset consisting of the 0th element [1]. The product of its elements is 1, which is a square-free integer.
It can be proven that there is no more than 1 square-free subset in the given array.


Constraints:

1 <= nums.length <= 1000
1 <= nums[i] <= 30
 */
public class CountTheNumberOfSquareFreeSubsets {
    int MOD = 1_000_000_007;
    public int squareFreeSubsets(int[] nums) {
        int[][] dp = new int[1010][1 << 11];
        for (int[] d : dp) Arrays.fill(d, -1);

        int[] primes = new int[]{2, 3, 5, 7, 11, 13, 17, 19, 23, 29};

        int[] numsPrimeMask = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            numsPrimeMask[i] = computeMask(nums[i], primes);
        }

        // error one : return dfs(0, 1, numsPrimeMask, dp) - 1;
        return (dfs(0, 1, numsPrimeMask, dp) - 1 + MOD) % MOD;
    }

    private int dfs(int pos, int productMask, int[] numsPrimeMask, int[][] dp) {
        if (pos >= numsPrimeMask.length) return 1;

        if (dp[pos][productMask] != -1) return dp[pos][productMask];


        // case 1: skip current pos
        int ans = dfs(pos + 1, productMask, numsPrimeMask, dp);

        // case 2: select current if not conflict
        if ((productMask & numsPrimeMask[pos]) == 0) {
            ans = (ans + dfs(pos + 1, productMask | numsPrimeMask[pos], numsPrimeMask, dp)) % MOD;
        }

        return dp[pos][productMask] = ans;
    }

    private int computeMask(int x, int[] primes) {
        int mask = 0;
        for (int i = 0; i < primes.length; i++) {
            int p = primes[i];
            int cnt = 0;
            while (x % p == 0) {
                x /= p;
                cnt++;
            }

            if (cnt == 0) continue;
            if (cnt == 1) mask |= (1 << (i + 1));
            if (cnt >= 2) return -1;
        }
        return mask;
    }
}
