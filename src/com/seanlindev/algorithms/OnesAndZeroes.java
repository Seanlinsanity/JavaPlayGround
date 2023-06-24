package com.seanlindev.algorithms;

import java.util.Arrays;

public class OnesAndZeroes {
    public int findMaxForm(String[] strs, int m, int n) {
        int length = strs.length;
        int[][][] dp = new int[length][m + 1][n + 1];
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < m + 1; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }
        return findMaxForm(0, m, n, strs, m, n, dp);
    }

    public int findMaxForm(int start, int countM, int countN, String[] strs, int m, int n, int[][][] dp) {
        if (countM < 0 || countN < 0) { return -1; }
        if (start == strs.length) { return 0; }

        if (dp[start][countM][countN] > -1) { return dp[start][countM][countN]; }

        String str = strs[start];
        int count0 = countM;
        int count1 = countN;
        for (char character: str.toCharArray()) {
            if (character == '0') {
                count0 -= 1;
            } else {
                count1 -= 1;
            }
        }


        int result = Math.max(
                1 + findMaxForm(start + 1, count0, count1, strs, m, n, dp),
                findMaxForm(start + 1, countM, countN, strs, m, n, dp)
        );
        dp[start][countM][countN] = result;
        return result;
    }
}
