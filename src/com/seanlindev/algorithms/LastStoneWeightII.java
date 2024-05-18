package com.seanlindev.algorithms;

import java.util.Arrays;

/*
You are given an array of integers stones where stones[i] is the weight of the ith stone.

We are playing a game with the stones. On each turn, we choose any two stones and smash them together. Suppose the stones have weights x and y with x <= y. The result of this smash is:

If x == y, both stones are destroyed, and
If x != y, the stone of weight x is destroyed, and the stone of weight y has new weight y - x.
At the end of the game, there is at most one stone left.

Return the smallest possible weight of the left stone. If there are no stones left, return 0.



Example 1:

Input: stones = [2,7,4,1,8,1]
Output: 1
Explanation:
We can combine 2 and 4 to get 2, so the array converts to [2,7,1,8,1] then,
we can combine 7 and 8 to get 1, so the array converts to [2,1,1,1] then,
we can combine 2 and 1 to get 1, so the array converts to [1,1,1] then,
we can combine 1 and 1 to get 0, so the array converts to [1], then that's the optimal value.
Example 2:

Input: stones = [31,26,33,21,40]
Output: 5


Constraints:

1 <= stones.length <= 30
1 <= stones[i] <= 100
 */
public class LastStoneWeightII {
    public int lastStoneWeightII(int[] stones) {
        int sum = Arrays.stream(stones).sum();
        Integer[][] dp = new Integer[stones.length][sum + 1];
        return lastStoneWeightIIRecursion(stones, 0, 0, sum, dp);
    }

    int lastStoneWeightIIRecursion(int[] stones, int currentSum, int index, int sum, Integer[][] dp) {
        if (index == stones.length || currentSum >= sum / 2) {
            return Math.abs((sum - currentSum) - currentSum);
        }

        if (dp[index][currentSum] != null) {
            return dp[index][currentSum];
        }

        int stone = stones[index];
        int result1 = lastStoneWeightIIRecursion(stones, currentSum + stone, index + 1, sum, dp);
        int result2 = lastStoneWeightIIRecursion(stones, currentSum, index + 1, sum, dp);
        dp[index][currentSum] = Math.min(result1, result2);
        return dp[index][currentSum];
    }
}
