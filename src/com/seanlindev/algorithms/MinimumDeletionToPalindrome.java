package com.seanlindev.algorithms;

/*
Given a string, find the minimum number of characters that we can remove to make it a palindrome.

Example 1:

Input: "abdbca"
Output: 1
Explanation: By removing "c", we get a palindrome "abdba".
Example 2:

Input: = "cddpd"
Output: 2
Explanation: Deleting "cp", we get a palindrome "ddd".
Example 3:

Input: = "pqr"
Output: 2
Explanation: We have to remove any two characters to get a palindrome, e.g. if we
remove "pq", we get palindrome "r".
 */
public class MinimumDeletionToPalindrome {
    public int findMinimumDeletions(String st) {
        // edge case
        if (st.length() <= 1) { return 0; }

        int[][] dp = new int[st.length()][st.length()];
        for (int row = st.length() - 2; row >= 0; row--) {
            for (int column = row + 1; column < st.length(); column++) {
                if (st.charAt(row) != st.charAt(column)) {
                    dp[row][column] = 1 + Math.min(dp[row +1][column], dp[row][column - 1]);
                } else {
                    dp[row][column] = dp[row + 1][column - 1];
                }
            }
        }

        return dp[0][st.length() - 1];
    }
}
