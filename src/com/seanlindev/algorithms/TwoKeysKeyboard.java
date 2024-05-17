package com.seanlindev.algorithms;

/*
There is only one character 'A' on the screen of a notepad. You can perform one of two operations on this notepad for each step:

Copy All: You can copy all the characters present on the screen (a partial copy is not allowed).
Paste: You can paste the characters which are copied last time.
Given an integer n, return the minimum number of operations to get the character 'A' exactly n times on the screen.



Example 1:

Input: n = 3
Output: 3
Explanation: Initially, we have one character 'A'.
In step 1, we use Copy All operation.
In step 2, we use Paste operation to get 'AA'.
In step 3, we use Paste operation to get 'AAA'.
Example 2:

Input: n = 1
Output: 0


Constraints:

1 <= n <= 1000
 */
public class TwoKeysKeyboard {
    int result = Integer.MAX_VALUE;
    public int minSteps(int n) {
        if (n == 1) { return 0; }
        // int[][] dp = new int[n+1][n+1];
        // tracking(1, 0, 0, n, dp);
        // return result;

        if (n == 1) { return 0; }

        int[][] dp = new int[n][n];

        return tracking(1, 0, 0, n, dp);
    }
    //1,0,0,3
    //1,1,1,3
    //2,1,2,3
    //2,2,3,3
    //4,2,4,3
    //3,1,3,3
    //
    int tracking(int charCount, int copyCount, int step, int n, int[][] dp) {
        if (charCount > n) { return Integer.MAX_VALUE; }

        if (charCount == n) {
            result = Math.min(step, result);
            return step;
        }

        if (dp[charCount][copyCount] > 0) {
            return dp[charCount][copyCount];
        }

        int trackResult = Integer.MAX_VALUE;
        if (charCount != copyCount) {
            // copy
            trackResult = Math.min(trackResult, tracking(charCount, charCount, step + 1, n, dp));
        }

        if (copyCount > 0) {
            // paste
            trackResult = Math.min(trackResult, tracking(charCount + copyCount, copyCount, step + 1, n, dp));
        }

        dp[charCount][copyCount] = trackResult;
        result = Math.min(trackResult, result);
        return trackResult;
    }
}
