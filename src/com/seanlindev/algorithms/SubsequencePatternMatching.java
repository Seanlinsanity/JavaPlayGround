package com.seanlindev.algorithms;

/*
Given a string and a pattern, write a method to count the number of times the pattern appears in the string as a subsequence.

Example 1: Input: string: "baxmx", pattern: "ax"
Output: 2
Explanation: {baxmx, baxmx}.

Example 2:

Input: string: "tomorrow", pattern: "tor"
Output: 4
Explanation: Following are the four occurences: {tomorrow, tomorrow, tomorrow, tomorrow}.


 */
public class SubsequencePatternMatching {
    public int findSPMCount(String str, String pat) {
        // edge case
        if (str.length() < pat.length()) { return 0; }

        int[][] dp = new int[pat.length() +1][str.length() +1];
        for (int column = 0; column < str.length() +1; column ++) {
            dp[pat.length()][column] = 1;
        }

        for (int row = pat.length() - 1; row >= 0; row--) {
            for (int column = str.length() - 1; column >= 0; column--) {
                if (pat.charAt(row) == str.charAt(column)) {
                    dp[row][column] = dp[row + 1][column + 1] + dp[row][column + 1];
                } else {
                    dp[row][column] = dp[row][column + 1];
                }
            }
        }
        return dp[0][0];
    }

    public int findSPMCountII(String str, String pat) {
        // edge case
        if (pat.length() > str.length()) { return 0; }

        int[][] dp = new int[str.length()][pat.length()];
        return numOfSubsequencePattern(0, 0, str, pat, dp);
    }

    int numOfSubsequencePattern(int index1, int index2, String str, String pat, int[][] dp) {
        if (index2 == pat.length()) {
            return 1;
        }

        if (index1 >= str.length()) {
            return 0;
        }

        if (dp[index1][index2] != 0) {
            return dp[index1][index2];
        }

        int count = 0;
        if (str.charAt(index1) == pat.charAt(index2)) {
            count += numOfSubsequencePattern(index1 + 1, index2 + 1, str, pat, dp);
        }

        count += numOfSubsequencePattern(index1 + 1, index2, str, pat, dp);
        dp[index1][index2] = count;
        return count;
    }
}
