package com.seanlindev.algorithms;

/*
You are given two positive integers n and limit.

Return the total number of ways to distribute n candies among 3 children such that no child gets more than limit candies.



Example 1:

Input: n = 5, limit = 2
Output: 3
Explanation: There are 3 ways to distribute 5 candies such that no child gets more than 2 candies: (1, 2, 2), (2, 1, 2) and (2, 2, 1).
Example 2:

Input: n = 3, limit = 3
Output: 10
Explanation: There are 10 ways to distribute 3 candies such that no child gets more than 3 candies: (0, 0, 3), (0, 1, 2), (0, 2, 1), (0, 3, 0), (1, 0, 2), (1, 1, 1), (1, 2, 0), (2, 0, 1), (2, 1, 0) and (3, 0, 0).


Constraints:

1 <= n <= 106
1 <= limit <= 106
 */
public class DistributeCandiesAmongChildrenII {
    //n=5,limit=2
    public long distributeCandies(int n, int limit) {
        // return (
        //     cal(n + 2) -
        //     3 * cal(n - limit + 1) +
        //     3 * cal(n - (limit + 1) * 2 + 2) -
        //     cal(n - 3 * (limit + 1) + 2)
        // );

        long ans = 0;
        for (int i = 0; i <= Math.min(limit, n); i++) {
            if (n - i > 2 * limit) {
                continue;
            }
            ans += Math.min(n - i, limit) - Math.max(0, n - i - limit) + 1;
        }
        return ans;

        // Long[][][] dp = new Long[3][n + 1][limit + 1];
        // return distributeCandiesRecursion(0, n, limit, 0, dp);
    }

    public long cal(int x) {
        if (x < 0) {
            return 0;
        }
        return ((long) x * (x - 1)) / 2;
    }

    private long distributeCandiesRecursion(int i, int n, int limit, int current, Long[][][] dp) {
        if (n == 0) { return 1; }
        if (i >= 3) { return 0; }
        if (dp[i][n][current] != null) { return dp[i][n][current]; }
        long count = 0;
        if (current < limit) {
            count += distributeCandiesRecursion(i, n - 1, limit, current + 1, dp);
        }
        count += distributeCandiesRecursion(i + 1, n, limit, 0, dp);
        dp[i][n][current] = count;
        return count;
    }
}
