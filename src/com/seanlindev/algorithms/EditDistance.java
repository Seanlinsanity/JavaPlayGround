package com.seanlindev.algorithms;

/*
Given two strings word1 and word2, return the minimum number of operations required to convert word1 to word2.

You have the following three operations permitted on a word:

Insert a character
Delete a character
Replace a character


Example 1:

Input: word1 = "horse", word2 = "ros"
Output: 3
Explanation:
horse -> rorse (replace 'h' with 'r')
rorse -> rose (remove 'r')
rose -> ros (remove 'e')
Example 2:

Input: word1 = "intention", word2 = "execution"
Output: 5
Explanation:
intention -> inention (remove 't')
inention -> enention (replace 'i' with 'e')
enention -> exention (replace 'n' with 'x')
exention -> exection (replace 'n' with 'c')
exection -> execution (insert 'u')


Constraints:

0 <= word1.length, word2.length <= 500
word1 and word2 consist of lowercase English letters.
 */
public class EditDistance {
    public int minDistance(String word1, String word2) {
        if (word1.length() == 0 && word2.length() == 0) { return 0; }
        int rows = word2.length() + 1;
        int columns = word1.length() + 1;
        int[][] dp = new int[rows][columns];
        for (int c = 0; c < columns; c++) {
            dp[rows - 1][c] = columns - c - 1;
        }

        for (int r = 0; r < rows ; r++) {
            dp[r][columns - 1] = rows - r - 1;
        }

        for (int r = rows - 2; r >= 0; r--) {
            for (int c = columns - 2; c >= 0; c--) {
                char char1 = word1.charAt(c);
                char char2 = word2.charAt(r);
                if (char1 == char2) {
                    dp[r][c] = dp[r + 1][c + 1];
                } else {
                    int count1 = 1 + dp[r + 1][c];
                    int count2 = 1 + dp[r + 1][c + 1];
                    int count3 = 1 + dp[r][c + 1];
                    dp[r][c] = Math.min(count1, Math.min(count2, count3));
                }
            }
        }

        return dp[0][0];

        // Integer[][] dp = new Integer[word1.length()][word2.length()];
        // return editDistanceRecursion(word1, word2, 0, 0, dp);
    }

    int editDistanceRecursion(String word1, String word2, int index1, int index2, Integer[][] dp) {
        if (index1 == word1.length() && index2 == word2.length()) { return 0; }
        if (index1 == word1.length()) { return word2.length() - index2; }
        if (index2 == word2.length()) { return word1.length() - index1; }

        if (dp[index1][index2] != null) { return dp[index1][index2]; }

        char char1 = word1.charAt(index1);
        char char2 = word2.charAt(index2);
        int result = 0;
        if (char1 == char2) {
            result = editDistanceRecursion(word1, word2, index1 + 1, index2 + 1, dp);
        } else {
            // replace
            int count1 = 1 + editDistanceRecursion(word1, word2, index1 + 1, index2 + 1, dp);
            // delete
            int count2 = 1 + editDistanceRecursion(word1, word2, index1 + 1, index2, dp);
            // insert
            int count3 = 1 + editDistanceRecursion(word1, word2, index1, index2 + 1, dp);
            result = Math.min(count3, Math.min(count1, count2));
        }
        dp[index1][index2] = result;
        return result;
    }
}
