package com.seanlindev.algorithms;

import java.util.Arrays;

/*
Given a sequence, find the length of its longest repeating subsequence (LRS). A repeating subsequence will be the one that appears at least twice in the original sequence and is not overlapping (i.e. none of the corresponding characters in the repeating subsequences have the same index).

Example 1:

Input: "t o m o r r o w"
Output: 2
Explanation: The longest repeating subsequence is "or" {tomorrow}.

Example 2:

Input: "a a b d b c e c"
Output: 3
Explanation: The longest repeating subsequence is "a b c" {a a b d b c e c}.

Example 3:

Input: "f m f f"
Output: 2
Explanation: The longest repeating subsequence is "f f" {f m f f, f m f f}. Please note the second last character is shared in LRS.
 */
public class LongestRepeatingSubsequence {
    public int findLRSLength(String str) {
        int length = str.length();
        int[][] dp = new int[length + 1][length + 1];
        for (int row = length - 1; row >= 0; row--) {
            for (int column = length - 1; column >= 0; column--) {
                if (row == column) {
                    dp[row][column] = dp[row + 1][column];
                } else {
                    char char1 = str.charAt(row);
                    char char2 = str.charAt(column);
                    if (char1 == char2) {
                        dp[row][column] = 1 + dp[row + 1][column + 1];
                    } else {
                        dp[row][column] = Math.max(dp[row][column + 1], dp[row +1][column]);
                    }
                }
            }
        }
        return dp[0][0];
    }

    public int findLRSLengthII(String str) {
        int length = str.length();
        Integer[][] dp = new Integer[length][length];
        return longestRepeatingSubsequence(0, 0, str.toCharArray(), dp);
    }

    int longestRepeatingSubsequence(int index1, int index2, char[] chars, Integer[][] dp) {
        if (index1 >= chars.length || index2 >= chars.length) {
            return 0;
        }

        if (dp[index1][index2] != null) {
            return dp[index1][index2];
        }

        if (index1 == index2) {
            int result = longestRepeatingSubsequence(index1, index2 + 1, chars, dp);
            dp[index1][index2] = result;
            return result;
        }

        char char1 = chars[index1];
        char char2 = chars[index2];
        int result = 0;
        if (char1 == char2) {
            result = 1 + longestRepeatingSubsequence(index1 + 1, index2 + 1, chars, dp);
        } else {
            result = Math.max(
                    longestRepeatingSubsequence(index1, index2 + 1, chars, dp),
                    longestRepeatingSubsequence(index1 + 1, index2, chars, dp)
            );
        }

        dp[index1][index2] = result;
        return result;
    }
}
