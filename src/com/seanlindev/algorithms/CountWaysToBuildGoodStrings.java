package com.seanlindev.algorithms;

/*
Given the integers zero, one, low, and high, we can construct a string by starting with an empty string, and then at each step perform either of the following:

Append the character '0' zero times.
Append the character '1' one times.
This can be performed any number of times.

A good string is a string constructed by the above process having a length between low and high (inclusive).

Return the number of different good strings that can be constructed satisfying these properties. Since the answer can be large, return it modulo 109 + 7.



Example 1:

Input: low = 3, high = 3, zero = 1, one = 1
Output: 8
Explanation:
One possible valid good string is "011".
It can be constructed as follows: "" -> "0" -> "01" -> "011".
All binary strings from "000" to "111" are good strings in this example.
Example 2:

Input: low = 2, high = 3, zero = 1, one = 2
Output: 5
Explanation: The good strings are "00", "11", "000", "110", and "011".


Constraints:

1 <= low <= high <= 105
1 <= zero, one <= low
 */
public class CountWaysToBuildGoodStrings {
    int modulo = (int)Math.pow(10, 9) + 7;
    public int countGoodStrings(int low, int high, int zero, int one) {
        long[] dp = new long[high + 1];
        dp[zero] += 1;
        dp[one] += 1;
        int minLength = Math.min(zero, one);
        for (int i = minLength + 1; i <= high; i++) {
            if (i - zero >= 0) {
                dp[i] += dp[i - zero];
            }

            if (i - one >= 0) {
                dp[i] += dp[i - one];
            }

            dp[i] = dp[i] % modulo;
        }

        long count = 0;
        for (int i = low; i <= high; i++) {
            count += (long)dp[i];
            count = count % modulo;
        }

        return (int)count;
        // return countGoodStringsRecursion(low, high, zero, one, 0, dp);
    }

    int countGoodStringsRecursion(int low, int high, int zero, int one, int length, int[] dp) {
        if (length > high) { return 0; }
        if (length == high) { return 1; }
        if (dp[length] != -1) { return dp[length]; }

        long count = (long)(countGoodStringsRecursion(low, high, zero, one, zero + length, dp) % modulo) +
                (long)(countGoodStringsRecursion(low, high, zero, one, one + length, dp) % modulo);

        if (length >= low) {
            count += 1;
        }

        int result = (int)(count % modulo);
        dp[length] = result;
        return result;
    }
}
