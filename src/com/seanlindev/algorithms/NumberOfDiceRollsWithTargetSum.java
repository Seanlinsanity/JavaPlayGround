package com.seanlindev.algorithms;

/*
You have n dice, and each dice has k faces numbered from 1 to k.

Given three integers n, k, and target, return the number of possible ways (out of the kn total ways) to roll the dice, so the sum of the face-up numbers equals target. Since the answer may be too large, return it modulo 109 + 7.



Example 1:

Input: n = 1, k = 6, target = 3
Output: 1
Explanation: You throw one die with 6 faces.
There is only one way to get a sum of 3.
Example 2:

Input: n = 2, k = 6, target = 7
Output: 6
Explanation: You throw two dice, each with 6 faces.
There are 6 ways to get a sum of 7: 1+6, 2+5, 3+4, 4+3, 5+2, 6+1.
Example 3:

Input: n = 30, k = 30, target = 500
Output: 222616187
Explanation: The answer must be returned modulo 109 + 7.


Constraints:

1 <= n, k <= 30
1 <= target <= 1000
 */
public class NumberOfDiceRollsWithTargetSum {
    long modulo = (long)Math.pow(10, 9) + 7L;
    public int numRollsToTarget(int n, int k, int target) {
        long[][] dp = new long[n + 1][target + 1];

        for (int i = n - 1; i >= 0; i--) {
            for (int j = target - 1; j >= 0; j--){
                int currentNum = n - i;
                int currentTarget = target - j;
                if (currentNum == 1) {
                    dp[i][j] = currentTarget <= k ? 1 : 0;
                } else {
                    long sum = 0;
                    for (int f = 1; f <= k; f++) {
                        if (currentTarget - f >= 0) {
                            sum += dp[i + 1][j + f];
                            sum = sum % modulo;
                        }
                    }
                    dp[i][j] = sum;
                }
            }
        }

        return (int)dp[0][0];

        // Long[][] dp = new Long[n + 1][target + 1];
        // return (int)numRollsToTargetRecursion(n, k, target, dp);
    }

    long numRollsToTargetRecursion(int n, int k, int target, Long[][] dp) {
        if (target <= 0) { return 0; }
        if (n == 1) { return target <= k ? 1 : 0; }

        if (dp[n][target] != null) { return dp[n][target]; }

        long sum = 0;
        for (int i = 1; i <= k; i++) {
            sum += numRollsToTargetRecursion(n - 1, k, target - i, dp);
            sum = sum % modulo;
        }

        dp[n][target] = sum;
        return sum;
    }
}
