package com.seanlindev.algorithms;

/*
Given an integer n, return the number of prime numbers that are strictly less than n.



Example 1:

Input: n = 10
Output: 4
Explanation: There are 4 prime numbers less than 10, they are 2, 3, 5, 7.
Example 2:

Input: n = 0
Output: 0
Example 3:

Input: n = 1
Output: 0


Constraints:

0 <= n <= 5 * 106
 */
public class CountPrimes {
    public int countPrimes(int n) {
        boolean[] hasDivisor = new boolean[n];
        for (int num = 2; num <= (int)Math.sqrt(n); num++) {
            if (hasDivisor[num]) { continue; }

            for (int j = num; num * j < n; j++) {
                int currentNum = num * j;
                hasDivisor[currentNum] = true;
            }
        }

        int count = 0;
        for (int i = 2; i < n; i++) {
            if (!hasDivisor[i]) {
                count += 1;
            }
        }

        return count;
    }
}
