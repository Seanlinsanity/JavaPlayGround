package com.seanlindev.algorithms;

import java.util.ArrayList;
import java.util.List;

/*
You are given an integer num. You can swap two digits at most once to get the maximum valued number.

Return the maximum valued number you can get.



Example 1:

Input: num = 2736
Output: 7236
Explanation: Swap the number 2 and the number 7.
Example 2:

Input: num = 9973
Output: 9973
Explanation: No swap.


Constraints:

0 <= num <= 108
 */
public class MaximumSwap {
    public int maximumSwap(int num) {
        if (num == 0) { return 0; }

        List<Integer> numList = new ArrayList<>();
        int current = num;
        while (current > 0) {
            numList.add(current % 10);
            current = current / 10;
        }
        reverse(numList);

        int max = -1;
        int maxIndex = -1;
        int swapIndex1 = -1;
        int swapIndex2 = -1;
        for (int i = numList.size() - 1; i >= 0; i--) {
            if (max > numList.get(i)) {
                swapIndex1 = maxIndex;
                swapIndex2 = i;
            }
            if (numList.get(i) > max) {
                max = numList.get(i);
                maxIndex = i;
            }
        }

        if (swapIndex1 > 0) {
            int temp = numList.get(swapIndex1);
            numList.set(swapIndex1, numList.get(swapIndex2));
            numList.set(swapIndex2, temp);
        }

        int result = 0;
        int base = 1;
        for (int i = numList.size() - 1; i >= 0; i--) {
            result += (numList.get(i) * base);
            base = base * 10;
        }
        return result;
    }

    void reverse(List<Integer> numList) {
        int l = 0;
        int r = numList.size() - 1;
        while (l < r) {
            int temp = numList.get(l);
            numList.set(l, numList.get(r));
            numList.set(r, temp);
            l += 1;
            r -= 1;
        }
    }
}
