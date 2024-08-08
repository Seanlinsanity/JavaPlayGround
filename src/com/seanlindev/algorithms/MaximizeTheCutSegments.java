package com.seanlindev.algorithms;

import java.util.Arrays;

/*
Given an integer n denoting the Length of a line segment. You need to cut the line segment in such a way that the cut length of a line segment each time is either x , y or z. Here x, y, and z are integers.
After performing all the cut operations, your total number of cut segments must be maximum.

Note: if no segment can be cut then return 0.

Examples:

Input: n = 4, x = 2, y = 1, z = 1
Output: 4
Explanation: Total length is 4, and the cut
lengths are 2, 1 and 1.  We can make
maximum 4 segments each of length 1.
Input: n = 5, x = 5, y = 3, z = 2
Output: 2
Explanation: Here total length is 5, and
the cut lengths are 5, 3 and 2. We can
make two segments of lengths 3 and 2.
Expected Time Complexity : O(n)
Expected Auxiliary Space: O(n)

Constraints
1 <= n, x, y, z <= 104
 */
public class MaximizeTheCutSegments {
    public int maximizeCuts(int n, int x, int y, int z)
    {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, -1);
        dp[0] = 0;
        for (int i = 1; i <= n; i++) {
            if (i >= x && dp[i - x] >= 0) {
                dp[i] = Math.max(dp[i], 1 + dp[i - x]);
            }
            if (i >= y && dp[i - y] >= 0) {
                dp[i] = Math.max(dp[i], 1 + dp[i - y]);
            }

            if (i >= z && dp[i - z] >= 0) {
                dp[i] = Math.max(dp[i], 1 + dp[i - z]);
            }
        }
        return dp[n] == -1 ? 0 : dp[n];
    }
}
