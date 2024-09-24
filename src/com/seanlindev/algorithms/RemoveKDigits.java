package com.seanlindev.algorithms;

import java.util.Stack;

/*
Given string num representing a non-negative integer num, and an integer k, return the smallest possible integer after removing k digits from num.



Example 1:

Input: num = "1432219", k = 3
Output: "1219"
Explanation: Remove the three digits 4, 3, and 2 to form the new number 1219 which is the smallest.
Example 2:

Input: num = "10200", k = 1
Output: "200"
Explanation: Remove the leading 1 and the number is 200. Note that the output must not contain leading zeroes.
Example 3:

Input: num = "10", k = 2
Output: "0"
Explanation: Remove all the digits from the number and it is left with nothing which is 0.


Constraints:

1 <= k <= num.length <= 105
num consists of only digits.
num does not have any leading zeros except for the zero itself.
 */
public class RemoveKDigits {
    public String removeKdigits(String num, int k) {
        int count = 0;
        Stack<Integer> stack = new Stack<>();
        for (char character: num.toCharArray()) {
            int value = Integer.valueOf(String.valueOf(character));
            while (stack.size() > 0 && stack.peek() > value && count < k) {
                stack.pop();
                count += 1;
            }

            stack.push(value);
        }

        while (stack.size() > 0 && count < k) {
            stack.pop();
            count += 1;
        }

        StringBuilder builder = new StringBuilder();
        boolean isLeadingZero = true;
        for (int value: stack) {
            if (value == 0 && isLeadingZero) {
                continue;
            }

            if (value != 0) {
                isLeadingZero = false;
            }
            builder.append(String.valueOf(value));
        }

        String result = builder.toString();
        return result.length() != 0 ? result : "0";
    }
}
