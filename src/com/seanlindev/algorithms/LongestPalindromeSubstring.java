package com.seanlindev.algorithms;

/*
Given a string s, return the longest
palindromic

substring
 in s.



Example 1:

Input: s = "babad"
Output: "bab"
Explanation: "aba" is also a valid answer.
Example 2:

Input: s = "cbbd"
Output: "bb"


Constraints:

1 <= s.length <= 1000
s consist of only digits and English letters.

    end
    b   a   b   a   d
b   T   F   T   F   F
a   F   T   F   T   F
b   F   F   T   F   F
a   F   F   F   T   F
d   F   F   F   F   T
start
 */
public class LongestPalindromeSubstring {
    public String longestPalindrome(String s) {
        // edge case
        if (s.length() == 1) { return s; }

        boolean[][] dp = new boolean[s.length()][s.length()];
        for (int row = 0; row < s.length(); row++) {
            for (int column = 0; column <= row; column++)
                dp[row][column] = true;
        }

        int maxLength = 1;
        String result = s.substring(0, 1);
        for (int row = s.length() - 2; row >= 0; row--) {
            for (int column = row + 1; column < s.length(); column++) {
                if (s.charAt(row) == s.charAt(column)) {
                    dp[row][column] = dp[row + 1][column - 1];
                } else {
                    dp[row][column] = false;
                }

                if (dp[row][column]) {
                    if (column - row + 1 > maxLength) {
                        maxLength = column - row + 1;
                        result = s.substring(row, column + 1);
                    }
                }
            }
        }

        return result;
    }
}
