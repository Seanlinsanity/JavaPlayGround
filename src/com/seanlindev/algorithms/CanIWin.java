package com.seanlindev.algorithms;

/*
In the "100 game" two players take turns adding, to a running total, any integer from 1 to 10. The player who first causes the running total to reach or exceed 100 wins.

What if we change the game so that players cannot re-use integers?

For example, two players might take turns drawing from a common pool of numbers from 1 to 15 without replacement until they reach a total >= 100.

Given two integers maxChoosableInteger and desiredTotal, return true if the first player to move can force a win, otherwise, return false. Assume both players play optimally.



Example 1:

Input: maxChoosableInteger = 10, desiredTotal = 11
Output: false
Explanation:
No matter which integer the first player choose, the first player will lose.
The first player can choose an integer from 1 up to 10.
If the first player choose 1, the second player can only choose integers from 2 up to 10.
The second player will win by choosing 10 and get a total = 11, which is >= desiredTotal.
Same with other integers chosen by the first player, the second player will always win.
Example 2:

Input: maxChoosableInteger = 10, desiredTotal = 0
Output: true
Example 3:

Input: maxChoosableInteger = 10, desiredTotal = 1
Output: true


Constraints:

1 <= maxChoosableInteger <= 20
0 <= desiredTotal <= 300
 */
public class CanIWin {
    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        if (maxChoosableInteger >= desiredTotal) { return true; }
        int total = (1 + maxChoosableInteger) * maxChoosableInteger / 2;
        if (total < desiredTotal) { return false; }

        int visited = 0;
        Boolean[] memo = new Boolean[1 << (maxChoosableInteger + 1)];
        return canIWinRecursion(maxChoosableInteger, visited, desiredTotal, memo);
    }
    // 100000000000
    // (10, [F,F,F,F,F,F,F,F,F,F,F], 40, T)
    // (10, [F,T,F,F,F,F,F,F,F,F,F], 39, F)
    //(10, [F,T,T,F,F,F,F,F,F,F,F], 37, T)
    //(10, [F,T,T,T,F,F,F,F,F,F,F], 34, F)
    //(10, [F,T,T,T,T,F,F,F,F,F,F], 30, T)
    //(10, [F,T,T,T,T,T,F,F,F,F,F], 25, F)
    //(10, [F,T,T,T,T,T,T,F,F,F,F], 19, T)
    //(10, [F,T,T,T,T,T,T,T,F,F,F], 12, F)
    //(10, [F,T,T,T,T,T,T,T,T,F,F], 4, T)
    // (4, [F,F,F,F,F], 6, T)
    // (4, [F,T,F,F,F], 5, F)
    // (4, [F,T,T,F,F], 3, T)
    // (4, [F,T,F,T,F], 2, F)
    // (4, [F,T,F,F,T], 1, T)
    // (10, [F,F,F,F,F,F,F,F,F,F], 11)
    // (10, [F,T,F,F,F,F,F,F,F,F], 10)
    // (10, [F,T,F,F,F,F,F,F,F,F], 10)

    boolean canIWinRecursion(int maxChoosableInteger, int visited, int desiredTotal, Boolean[] memo) {
        if (memo[visited] != null) { return memo[visited]; }

        int key = visited;
        for (int num = 1; num <= maxChoosableInteger; num++) {
            if ((visited & (1 << num)) > 0) { continue; }

            visited = visited | (1 << num);
            if (num >= desiredTotal || !canIWinRecursion(maxChoosableInteger, visited, desiredTotal - num, memo)) {
                visited = visited ^ (1 << num);
                memo[key] = true;
                return true;
            }
            visited = visited ^ (1 << num);
        }
        memo[key] = false;
        return false;
    }
}
