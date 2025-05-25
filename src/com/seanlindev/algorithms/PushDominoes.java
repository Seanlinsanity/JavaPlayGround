package com.seanlindev.algorithms;

/*
There are n dominoes in a line, and we place each domino vertically upright. In the beginning, we simultaneously push some of the dominoes either to the left or to the right.

After each second, each domino that is falling to the left pushes the adjacent domino on the left. Similarly, the dominoes falling to the right push their adjacent dominoes standing on the right.

When a vertical domino has dominoes falling on it from both sides, it stays still due to the balance of the forces.

For the purposes of this question, we will consider that a falling domino expends no additional force to a falling or already fallen domino.

You are given a string dominoes representing the initial state where:

dominoes[i] = 'L', if the ith domino has been pushed to the left,
dominoes[i] = 'R', if the ith domino has been pushed to the right, and
dominoes[i] = '.', if the ith domino has not been pushed.
Return a string representing the final state.



Example 1:

Input: dominoes = "RR.L"
Output: "RR.L"
Explanation: The first domino expends no additional force on the second domino.
Example 2:


Input: dominoes = ".L.R...LR..L.."
Output: "LL.RR.LLRRLL.."


Constraints:

n == dominoes.length
1 <= n <= 105
dominoes[i] is either 'L', 'R', or '.'.
 */
public class PushDominoes {
    public String pushDominoes(String dominoes) {
        char[] chars = dominoes.toCharArray();
        int[] rightIndex = new int[chars.length];
        int currentIdx = -1;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == 'R') {
                currentIdx = i;
            } else if (chars[i] == 'L') {
                currentIdx = -1;
            }
            rightIndex[i] = currentIdx;
        }

        int[] leftIndex = new int[chars.length];
        currentIdx = -1;
        for (int i = chars.length - 1; i >= 0; i--) {
            if (chars[i] == 'L') {
                currentIdx = i;
            } else if (chars[i] == 'R') {
                currentIdx = -1;
            }
            leftIndex[i] = currentIdx;
        }

        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == 'R' || chars[i] == 'L') {
                continue;
            }

            int rightFailingIndex = rightIndex[i];
            int leftFailingIndex = leftIndex[i];
            if (rightFailingIndex >= 0 && leftFailingIndex < 0) {
                chars[i] = 'R';
            } else if (rightFailingIndex < 0 && leftFailingIndex >= 0) {
                chars[i] = 'L';
            } else if (rightFailingIndex >= 0 && leftFailingIndex >= 0) {
                if (Math.abs(rightFailingIndex - i) > Math.abs(leftFailingIndex - i)) {
                    chars[i] = 'L';
                } else if (Math.abs(rightFailingIndex - i) < Math.abs(leftFailingIndex - i)) {
                    chars[i] = 'R';
                }
            }
        }

        return String.valueOf(chars);
    }
}
