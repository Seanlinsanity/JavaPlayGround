package com.seanlindev.algorithms;

import java.util.Arrays;

/*
You are given an array of n pairs pairs where pairs[i] = [lefti, righti] and lefti < righti.

A pair p2 = [c, d] follows a pair p1 = [a, b] if b < c. A chain of pairs can be formed in this fashion.

Return the length longest chain which can be formed.

You do not need to use up all the given intervals. You can select pairs in any order.



Example 1:

Input: pairs = [[1,2],[2,3],[3,4]]
Output: 2
Explanation: The longest chain is [1,2] -> [3,4].
Example 2:

Input: pairs = [[1,2],[7,8],[4,5]]
Output: 3
Explanation: The longest chain is [1,2] -> [4,5] -> [7,8].


Constraints:

n == pairs.length
1 <= n <= 1000
-1000 <= lefti < righti <= 1000
 */
public class MaximumLengthOfPairChain {
    public int findLongestChain(int[][] pairs) {
        Arrays.sort(pairs, (a, b) -> Integer.compare(a[1], b[1]));

        int[] prev = pairs[0];
        int res = 1;

        for (int i = 1; i < pairs.length; i++) {
            int[] cur = pairs[i];
            if (cur[0] > prev[1]) {
                res++;
                prev = cur;
            }
        }

        return res;

        // Arrays.sort(pairs, (a, b) -> Integer.compare(a[0], b[0]));

        // int[] dp = new int[pairs.length];
        // dp[pairs.length - 1] = 1;
        // int result = 1;
        // for (int i = pairs.length - 2; i >= 0; i--) {
        //     int maxLength = 1;
        //     for (int j = i + 1; j < pairs.length; j++) {
        //         if (pairs[j][0] > pairs[i][1]) {
        //             maxLength = Math.max(maxLength, 1 + dp[j]);
        //         }
        //     }
        //     dp[i] = maxLength;
        //     result = Math.max(result, dp[i]);
        // }

        // return result;
    }
}
