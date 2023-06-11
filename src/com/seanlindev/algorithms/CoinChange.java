package com.seanlindev.algorithms;

import java.util.Arrays;

/*
You are given an integer array coins representing coins of different denominations and an integer amount representing a total amount of money.

Return the fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by any combination of the coins, return -1.

You may assume that you have an infinite number of each kind of coin.



Example 1:

Input: coins = [1,2,5], amount = 11
Output: 3
Explanation: 11 = 5 + 5 + 1
Example 2:

Input: coins = [2], amount = 3
Output: -1
Example 3:

Input: coins = [1], amount = 0
Output: 0


Constraints:

1 <= coins.length <= 12
1 <= coins[i] <= 231 - 1
0 <= amount <= 104
Accepted
1.4M
Submissions
 */
public class CoinChange {
    public int coinChange(int[] coins, int amount) {
        if (amount == 0) { return 0; }
        int[] memoization = new int[amount + 1];
        for (int i = 0; i <= amount; i++) {
            if (i == 0) {
                memoization[i] = 0;
                continue;
            }

            int count = Integer.MAX_VALUE;
            for (int coin: coins) {
                if (i - coin >= 0) {
                    int remain = i - coin;
                    int prev = memoization[remain];
                    if (prev >= 0) {
                        count = Math.min(1 + memoization[remain], count);
                    }
                }
            }

            memoization[i] = count == Integer.MAX_VALUE ? -1 : count;
        }

        return memoization[amount];
    }

     public int coinChange2(int[] coins, int amount) {
         if (amount < 0 || coins.length == 0 || coins == null) {
             return 0;
         }
         int[] dp = new int[amount + 1];
         Arrays.fill(dp, amount + 1);
         dp[0] = 0;

         for (int i = 1; i <= amount; i++) {
             for (int coin : coins) {
                 if (i - coin >= 0) {
                     dp[i] = Math.min(dp[i], 1 + dp[i - coin]);
                 }
             }
         }

         return dp[amount] != amount + 1 ? dp[amount] : -1;
     }
}
