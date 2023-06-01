package com.seanlindev.algorithms;

/*
You are climbing a staircase. It takes n steps to reach the top.

Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?



Example 1:

Input: n = 2
Output: 2
Explanation: There are two ways to climb to the top.
1. 1 step + 1 step
2. 2 steps
Example 2:

Input: n = 3
Output: 3
Explanation: There are three ways to climb to the top.
1. 1 step + 1 step + 1 step
2. 1 step + 2 steps
3. 2 steps + 1 step


Constraints:

1 <= n <= 45
 */
public class ClimbingStairs {
    class Solution {
        public int climbStairs(int n) {
            int[] memoization = new int[n + 1];
            return climbStairsDP(n, memoization);
        }

        public int climbStairsDP(int n, int[] memoization) {
            if (n == 1) { return 1; }

            if (n == 2) { return 2; }

            if (memoization[n] != 0) { return memoization[n]; }

            memoization[n] = climbStairsDP(n - 1, memoization) + climbStairsDP(n - 2, memoization);
            return memoization[n];
        }
    }
}
