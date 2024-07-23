package com.seanlindev.algorithms;

/*
Given a signed 32-bit integer x, return x with its digits reversed. If reversing x causes the value to go outside the signed 32-bit integer range [-231, 231 - 1], then return 0.

Assume the environment does not allow you to store 64-bit integers (signed or unsigned).



Example 1:

Input: x = 123
Output: 321
Example 2:

Input: x = -123
Output: -321
Example 3:

Input: x = 120
Output: 21


Constraints:

-231 <= x <= 231 - 1
 */

public class ReverseInteger {
    public int reverse(int x) {
        boolean isNegative = x < 0;

        x = Math.abs(x);

        int num = 0;

        while (x > 0) {
            if (Integer.MAX_VALUE / 10 < num) return 0;
            if (Integer.MAX_VALUE / 10 == num && x % 10 > Integer.MAX_VALUE % 10) return 0;

            num = 10 * num + x % 10;
            x /= 10;
        }

        return isNegative ? -num : num;
    }
}
