package com.seanlindev.algorithms;

/*
A positive integer is magical if it is divisible by either a or b.

Given the three integers n, a, and b, return the nth magical number. Since the answer may be very large, return it modulo 109 + 7.



Example 1:

Input: n = 1, a = 2, b = 3
Output: 2
Example 2:

Input: n = 4, a = 2, b = 3
Output: 6


Constraints:

1 <= n <= 109
2 <= a, b <= 4 * 104
 */
public class NthMagicalNumber {
    public int nthMagicalNumber(int n, int a, int b) {
        long mod = (long)(Math.pow(10, 9) + 7);
        if (a == b) {
            long num = (long) a * (long)n;
            return (int)(num % mod);
        }

        int gcd = greatestCommonDivisor(a, b);
        long leastCommonMultiple = ((long)a * (long)b / (long)gcd);
        long low = 0;
        long high = (long)Math.min(a, b) * (long)n;
        long result = high;
        while (low <= high) {
            long mid = (high - low) / 2 + low;
            if ((mid / a) + (mid / b) - (mid / leastCommonMultiple) < n) {
                low = mid + 1;
            } else {
                if ((mid / a) + (mid / b) - (mid / leastCommonMultiple) == n) {
                    result = Math.min(result, mid);
                }

                high = mid - 1;
            }
        }

        return (int)(result % mod);
    }

    int greatestCommonDivisor(int x, int y) {
        if (y == 0) { return x; }

        return greatestCommonDivisor(y, x % y);
    }
}
