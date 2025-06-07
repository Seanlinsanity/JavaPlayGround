package com.seanlindev.algorithms;

/*
You are given a string s and an integer k.

In one operation, you can replace the character at any position with the next or previous letter in the alphabet (wrapping around so that 'a' is after 'z'). For example, replacing 'a' with the next letter results in 'b', and replacing 'a' with the previous letter results in 'z'. Similarly, replacing 'z' with the next letter results in 'a', and replacing 'z' with the previous letter results in 'y'.

Return the length of the longest palindromic subsequence of s that can be obtained after performing at most k operations.



Example 1:

Input: s = "abced", k = 2

Output: 3

Explanation:

Replace s[1] with the next letter, and s becomes "acced".
Replace s[4] with the previous letter, and s becomes "accec".
The subsequence "ccc" forms a palindrome of length 3, which is the maximum.

Example 2:

Input: s = "aaazzz", k = 4

Output: 6

Explanation:

Replace s[0] with the previous letter, and s becomes "zaazzz".
Replace s[4] with the next letter, and s becomes "zaazaz".
Replace s[3] with the next letter, and s becomes "zaaaaz".
The entire string forms a palindrome of length 6.



Constraints:

1 <= s.length <= 200
1 <= k <= 200
s consists of only lowercase English letters.
 */
public class LongestPalindromicSubsequenceAfterAtMostKOperations {
    private char[] s;   // Array to store the characters of the input string
    private Integer[][][] memo; // Memoization table to store intermediate results

    public int longestPalindromicSubsequence(String s, int k) {
        this.s = s.toCharArray(); // Convert the input string to a character array
        int n = s.length(); // Length of the input string
        memo = new Integer[n][n][k + 1]; // Initialize the memoization table
        return longestPalindromicSubsequenceRecursion(0, n - 1, k); // Start the recursive depth-first search
    }

    private int longestPalindromicSubsequenceRecursion(int i, int j, int k) {
        if (i > j) {
            return 0; // If the left index exceeds the right, there is no subsequence
        }
        if (i == j) {
            return 1; // If both indices are the same, it forms a single-character palindrome
        }
        if (memo[i][j][k] != null) {
            return memo[i][j][k]; // Return the stored result if already computed
        }

        // Case 1: Either exclude the left or right character
        int result = Math.max(
                longestPalindromicSubsequenceRecursion(i + 1, j, k),
                longestPalindromicSubsequenceRecursion(i, j - 1, k)
        );

        // Calculate the distance between characters s[i] and s[j] in the alphabet
        int distance = Math.abs(s[i] - s[j]);
        int cost = Math.min(distance, 26 - distance); // Minimum cost to make characters equal

        // Case 2: Include both characters if the modification cost is within the allowed limit
        if (cost <= k) {
            result = Math.max(result, 2 + longestPalindromicSubsequenceRecursion(i + 1, j - 1, k - cost));
        }

        memo[i][j][k] = result; // Store the computed result
        return result; // Return the longest palindromic subsequence length
    }
}
