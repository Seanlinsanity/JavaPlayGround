package com.seanlindev.algorithms;

/*
Implement pow(x, n), which calculates x raised to the power n (i.e., xn).



Example 1:

Input: x = 2.00000, n = 10
Output: 1024.00000
Example 2:

Input: x = 2.10000, n = 3
Output: 9.26100
Example 3:

Input: x = 2.00000, n = -2
Output: 0.25000
Explanation: 2-2 = 1/22 = 1/4 = 0.25


Constraints:

-100.0 < x < 100.0
-231 <= n <= 231-1
n is an integer.
Either x is not zero or n > 0.
-104 <= xn <= 104
 */
public class XToPowerOfN {
    public double myPow(double x, int n) {
        double num = x;
        int power = n;
        if (power < 0) {
            num = 1.0 / num;
            power = power * -1;
        }

        return powRecursion(num, power);
    }

    public double powRecursion(double num, int power) {
        if (num == 0) { return 0; }
        if (power == 0) { return 1; }

        double halfPowerResult = powRecursion(num, power / 2);
        double result = halfPowerResult * halfPowerResult;
        if (power % 2 != 0) {
            result = result * num;
        }

        return result;
    }
}
