package com.seanlindev.algorithms;

/*
You are given an array prices where prices[i] is the price of a given stock on the ith day, and an integer fee representing a transaction fee.

Find the maximum profit you can achieve. You may complete as many transactions as you like, but you need to pay the transaction fee for each transaction.

Note:

You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).
The transaction fee is only charged once for each stock purchase and sale.


Example 1:

Input: prices = [1,3,2,8,4,9], fee = 2
Output: 8
Explanation: The maximum profit can be achieved by:
- Buying at prices[0] = 1
- Selling at prices[3] = 8
- Buying at prices[4] = 4
- Selling at prices[5] = 9
The total profit is ((8 - 1) - 2) + ((9 - 4) - 2) = 8.
Example 2:

Input: prices = [1,3,7,5,10,3], fee = 3
Output: 6


Constraints:

1 <= prices.length <= 5 * 104
1 <= prices[i] < 5 * 104
0 <= fee < 5 * 104
 */
public class BestTimeBuySellStockWithTransactionFee {
    public int maxProfit(int[] prices, int fee) {
        Integer[][] dp = new Integer[2][prices.length];
        return maxProfitRecursion(0, false, prices, fee, dp);
    }

    int maxProfitRecursion(int index, boolean hasStock, int[] prices, int fee, Integer[][] dp) {
        if (index == prices.length) { return 0; }

        int dpRow = hasStock ? 1 : 0;
        if (dp[dpRow][index] != null) { return dp[dpRow][index]; }

        int price = prices[index];
        int profit = maxProfitRecursion(index + 1, hasStock, prices, fee, dp);
        if (hasStock) {
            profit = Math.max(
                    profit,
                    (price - fee) + maxProfitRecursion(index + 1, false, prices, fee, dp)
            );
        } else {
            profit = Math.max(
                    profit,
                    maxProfitRecursion(index + 1, true, prices, fee, dp) - price
            );
        }

        dp[dpRow][index] = profit;
        return profit;
    }
}
