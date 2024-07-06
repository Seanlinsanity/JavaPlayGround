package com.seanlindev.algorithms;

/*
Given two strings 's1' and 's2', find the length of the longest substring which is common in both the strings.

Example 1:

Input: s1 = "abdca"
       s2 = "cbda"
Output: 2
Explanation: The longest common substring is "bd".
Example 2:

Input: s1 = "passport"
       s2 = "ppsspt"
Output: 3
Explanation: The longest common substring is "ssp".
Constraints:

1 <= s1.length, s2.length <= 1000`
s1 and s2 consist of only lowercase English characters.
 */
public class LongestCommonString {
    public int findLCSLength(String s1, String s2) {
        int result = 0;
        int[][] dp = new int[s1.length() + 1][s2.length() + 1];
        for (int row = s1.length() - 1; row >= 0; row--) {
            for (int column = s2.length() - 1; column >= 0; column--) {
                if (s1.charAt(row) == s2.charAt(column)) {
                    dp[row][column] = 1 + dp[row + 1][column + 1];
                } else {
                    dp[row][column] = 0;
                }
                result = Math.max(result, dp[row][column]);
            }
        }

        return result;
    }
}
