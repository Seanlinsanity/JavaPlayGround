package com.seanlindev.algorithms;

/*
You have two types of tiles: a 2 x 1 domino shape and a tromino shape. You may rotate these shapes.


Given an integer n, return the number of ways to tile an 2 x n board. Since the answer may be very large, return it modulo 109 + 7.

In a tiling, every square must be covered by a tile. Two tilings are different if and only if there are two 4-directionally adjacent cells on the board such that exactly one of the tilings has both squares occupied by a tile.



Example 1:


Input: n = 3
Output: 5
Explanation: The five different ways are shown above.
Example 2:

Input: n = 1
Output: 1


Constraints:

1 <= n <= 1000
 */
public class DominoAndTrominoTiling {
    public int numTilings(int n) {
        long modulo = 1_000_000_000 + 7;
        if (n <= 3) {
            if (n == 1) { return 1; }
            if (n == 2) { return 2; }
            if (n == 3) { return 5; }
        }

        long[] fullDp = new long[n + 1]; //dp[4]=11, dp[5]=11+10+4
        fullDp[1] = 1;
        fullDp[2] = 2;
        fullDp[3] = 5;
        long[] partialDp = new long[n + 1];
        partialDp[1] = 0;
        partialDp[2] = 2;
        partialDp[3] = 4;
        for (int i = 4; i <= n; i++) {
            partialDp[i] = partialDp[i - 1] + 2 * fullDp[i - 2];
            fullDp[i] = (fullDp[i - 1] + fullDp[i - 2] % modulo + (partialDp[i - 1] % modulo)) % modulo;
        }

        return (int)fullDp[n];
    }
}
