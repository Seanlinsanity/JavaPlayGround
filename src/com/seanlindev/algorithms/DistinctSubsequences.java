package com.seanlindev.algorithms;

import java.util.Arrays;

/*
Given two strings s and t, return the number of distinct
subsequences
 of s which equals t.

The test cases are generated so that the answer fits on a 32-bit signed integer.



Example 1:

Input: s = "rabbbit", t = "rabbit"
Output: 3
Explanation:
As shown below, there are 3 ways you can generate "rabbit" from s.
rabbbit
rabbbit
rabbbit
Example 2:

Input: s = "babgbag", t = "bag"
Output: 5
Explanation:
As shown below, there are 5 ways you can generate "bag" from s.
babgbag
babgbag
babgbag
babgbag
babgbag


Constraints:

1 <= s.length, t.length <= 1000
s and t consist of English letters.
 */
public class DistinctSubsequences {
    public int numDistinct(String s, String t) {
        if (s.length() < t.length()) {  return 0; }

        int[][] dp = new int[s.length()][t.length()];
        for (int i = 0; i < s.length(); i++) {
            Arrays.fill(dp[i], -1);
        }

        return distinctSubsequence(0, 0, s, t, dp);
    }

    public int distinctSubsequence(int index1, int index2, String s, String t, int[][] dp) {
        if (index2 == t.length()) { return 1; }

        if (index1 >= s.length()) { return 0; }

        if (dp[index1][index2] != -1) {
            return dp[index1][index2];
        }

        int result = 0;
        if (s.charAt(index1) == t.charAt(index2)) {
            result += distinctSubsequence(index1 + 1, index2 + 1, s, t, dp);
        }

        result += distinctSubsequence(index1 + 1, index2, s, t, dp);

        dp[index1][index2] = result;
        return result;
    }
}
