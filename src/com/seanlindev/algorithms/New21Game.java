package com.seanlindev.algorithms;

import java.util.HashMap;

/*
Alice plays the following game, loosely based on the card game "21".

Alice starts with 0 points and draws numbers while she has less than k points. During each draw, she gains an integer number of points randomly from the range [1, maxPts], where maxPts is an integer. Each draw is independent and the outcomes have equal probabilities.

Alice stops drawing numbers when she gets k or more points.

Return the probability that Alice has n or fewer points.

Answers within 10-5 of the actual answer are considered accepted.



Example 1:

Input: n = 10, k = 1, maxPts = 10
Output: 1.00000
Explanation: Alice gets a single card, then stops.
Example 2:

Input: n = 6, k = 1, maxPts = 10
Output: 0.60000
Explanation: Alice gets a single card, then stops.
In 6 out of 10 possibilities, she is at or below 6 points.
Example 3:

Input: n = 21, k = 17, maxPts = 10
Output: 0.73278


Constraints:

0 <= k <= n <= 104
1 <= maxPts <= 104
 */
public class New21Game {
    public double new21Game(int n, int k, int maxPts) {
        if (n == 0 || k == 0) { return 1; }

        double windowSum = 0;
        for (int i = k; i < k + maxPts; i++) {
            windowSum += (i <= n) ? 1 : 0;
        }

        HashMap<Integer, Double> dp = new HashMap<>();
        for (int i = k - 1; i >= 0; i--) {
            dp.put(i, windowSum / maxPts);
            double remove = 0;
            if (i + maxPts <= n) {
                remove = dp.getOrDefault(i + maxPts, 1.0);
            }
            windowSum += (dp.get(i) - remove);
        }

        return dp.get(0);

        // Double[] dp = new Double[k + maxPts];
        // double baseProbability = (double)1 / (double)maxPts;
        // double result = new21GameRecursion(n, k, maxPts, 0, dp, baseProbability);
        // return result;
    }

    // double new21GameRecursion(int n, int k, int maxPts, int point, Double[] dp, double baseProbability) {
    //     if (point >= k) {
    //         return point <= n ? 1 : 0;
    //     }

    //     if (dp[point] != null) { return dp[point]; }

    //     double total = 0;
    //     for (int i = 1; i <= maxPts; i++) {
    //         double result = new21GameRecursion(n, k, maxPts, point + i, dp, baseProbability);
    //         if (result > 0) {
    //             total += baseProbability;
    //         }
    //     }

    //     dp[point] = total;
    //     return total;
    // }
}
