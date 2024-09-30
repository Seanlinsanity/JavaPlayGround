package com.seanlindev.algorithms;

import java.util.HashMap;
import java.util.Map;

/*
Alice and Bob continue their games with piles of stones. There are a number of piles arranged in a row, and each pile has a positive integer number of stones piles[i]. The objective of the game is to end with the most stones.

Alice and Bob take turns, with Alice starting first.

On each player's turn, that player can take all the stones in the first X remaining piles, where 1 <= X <= 2M. Then, we set M = max(M, X). Initially, M = 1.

The game continues until all the stones have been taken.

Assuming Alice and Bob play optimally, return the maximum number of stones Alice can get.



Example 1:

Input: piles = [2,7,9,4,4]

Output: 10

Explanation:

If Alice takes one pile at the beginning, Bob takes two piles, then Alice takes 2 piles again. Alice can get 2 + 4 + 4 = 10 stones in total.
If Alice takes two piles at the beginning, then Bob can take all three piles left. In this case, Alice get 2 + 7 = 9 stones in total.
So we return 10 since it's larger.

Example 2:

Input: piles = [1,2,3,4,5,100]

Output: 104



Constraints:

1 <= piles.length <= 100
1 <= piles[i] <= 104
 */

public class StoneGameII {
    public int stoneGameII(int[] piles) {
        Map<String, Integer> dp = new HashMap<>();
        return stoneGameIIRecursion(piles, 0, 1, true, dp);
    }

    int stoneGameIIRecursion(int[] piles, int index, int M, boolean isAlice, Map<String, Integer> dp) {
        if (index >= piles.length) { return 0; }

        String key = String.valueOf(isAlice) + ":" + index + ":" + M;
        if (dp.get(key) != null) { return dp.get(key); }

        int total = 0;
        int result = isAlice ? 0 : Integer.MAX_VALUE;
        for (int x = 1; x <= M * 2; x++) {
            if (index + x - 1 >= piles.length) { break; }

            total += piles[index + x - 1];
            if (isAlice) {
                result = Math.max(result, total + stoneGameIIRecursion(piles, index + x, Math.max(x, M), false, dp));
            } else {
                result = Math.min(result, stoneGameIIRecursion(piles, index + x, Math.max(x, M), true, dp));
            }
        }

        dp.put(key, result);
        return result;
    }
}
