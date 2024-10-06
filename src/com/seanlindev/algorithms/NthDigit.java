package com.seanlindev.algorithms;

/*
Given an integer n, return the nth digit of the infinite integer sequence [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ...].



Example 1:

Input: n = 3
Output: 3
Example 2:

Input: n = 11
Output: 0
Explanation: The 11th digit of the sequence 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ... is a 0, which is part of the number 10.


Constraints:

1 <= n <= 231 - 1
 */
public class NthDigit {
    public int findNthDigit(int n) {
        int length = 1;
        long range = 9;
        long num = n; //num=10000
        while (num > range) {
            num -= range; //num=9991
            length += 1; //base=2
            range = length * (9 * (long)Math.pow(10, length - 1)); //range=2*90
        }


        long targetNum = (int)Math.pow(10, length - 1) + ((num % length > 0) ? (num / length) + 1 : num / length) - 1; //target=10 + 10 - 1= 19
        String targetStr = String.valueOf(targetNum);
        long modulo = num % length == 0 ? length : num % length; //2
        char character = targetStr.charAt((int)modulo - 1);
        return Integer.parseInt(String.valueOf(character));
    }
}
