package com.seanlindev.algorithms;

/*
You are given an integer array coins representing coins of different denominations and an integer amount representing a total amount of money.

Return the number of combinations that make up that amount. If that amount of money cannot be made up by any combination of the coins, return 0.

You may assume that you have an infinite number of each kind of coin.

The answer is guaranteed to fit into a signed 32-bit integer.



Example 1:

Input: amount = 5, coins = [1,2,5]
Output: 4
Explanation: there are four ways to make up the amount:
5=5
5=2+2+1
5=2+1+1+1
5=1+1+1+1+1
Example 2:

Input: amount = 3, coins = [2]
Output: 0
Explanation: the amount of 3 cannot be made up just with coins of 2.
Example 3:

Input: amount = 10, coins = [10]
Output: 1


Constraints:

1 <= coins.length <= 300
1 <= coins[i] <= 5000
All the values of coins are unique.
0 <= amount <= 5000
 */
public class CoinChange2 {
    public int change(int amount, int[] coins) {
        int n = coins.length; //3
        int[][] dp = new int[n][amount + 1];
        /*
              0 1 2 3 4 5 (amount)
              _ _ _ _ _ _
           1| 1,1,1,1,1,1
           2| 1,1,2,2,3,3
           5| 1,1,2,2,3,4
      (coin)
         */

        for (int i = 0; i < n; i++) {
            //i = 2
            for (int j = 0; j < amount + 1; j++) {
                //j = 0
                if (j == 0) {
                    dp[i][j] = 1;
                } else {
                    int count = 0;
                    if (i > 0) { //2 < 2
                        count += dp[i - 1][j];
                    }

                    if (coins[i] <= j) { //5 <= 5
                        count += dp[i][j - coins[i]];
                    }
                    dp[i][j] = count;
                }
            }
        }

        return dp[n - 1][amount];
    }
}
