package com.seanlindev.algorithms;

import java.util.Arrays;

/*
You are given an integer array matchsticks where matchsticks[i] is the length of the ith matchstick. You want to use all the matchsticks to make one square. You should not break any stick, but you can link them up, and each matchstick must be used exactly one time.

Return true if you can make this square and false otherwise.



Example 1:


Input: matchsticks = [1,1,2,2,2]
Output: true
Explanation: You can form a square with length 2, one side of the square came two sticks with length 1.
Example 2:

Input: matchsticks = [3,3,3,3,4]
Output: false
Explanation: You cannot find a way to form a square with all the matchsticks.


Constraints:

1 <= matchsticks.length <= 15
1 <= matchsticks[i] <= 108
 */
public class MatchsticksToSquare {
    public boolean makesquare(int[] matchsticks) {
        int sum = Arrays.stream(matchsticks).sum();
        if (sum % 4 != 0) { return false; }

        int target = sum / 4;
        for (int length: matchsticks) {
            if (length > target) { return false; }
        }

        Arrays.sort(matchsticks);
        reverse(matchsticks);
        return makesquareTracking(matchsticks, 0, new int[]{0, 0, 0, 0}, target);
    }

    private void reverse(int[] matchsticks) {
        for (int i = 0, j = matchsticks.length - 1; i < j; i++, j--) {
            int temp = matchsticks[i];
            matchsticks[i] = matchsticks[j];
            matchsticks[j] = temp;
        }
    }

    boolean makesquareTracking(int[] matchsticks, int index, int[] lengthArr, int target) {
        if (index == matchsticks.length) {
            return (lengthArr[0] == target && lengthArr[1] == target && lengthArr[2] == target && lengthArr[3] == target);
        }

        int length = matchsticks[index];
        for (int i = 0; i < 4; i++) {
            if (lengthArr[i] + length <= target) {
                lengthArr[i] += length;
                boolean success = makesquareTracking(matchsticks, index + 1, lengthArr, target);
                if (success) {
                    return true;
                }
                lengthArr[i] -= length;
            }
        }

        return false;
    }
}
