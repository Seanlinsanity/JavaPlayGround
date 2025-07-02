package com.seanlindev.algorithms;

import java.util.Stack;

/*
Given a string s representing a valid expression, implement a basic calculator to evaluate it, and return the result of the evaluation.

Note: You are not allowed to use any built-in function which evaluates strings as mathematical expressions, such as eval().



Example 1:

Input: s = "1 + 1"
Output: 2
Example 2:

Input: s = " 2-1 + 2 "
Output: 3
Example 3:

Input: s = "(1+(4+5+2)-3)+(6+8)"
Output: 23


Constraints:

1 <= s.length <= 3 * 105
s consists of digits, '+', '-', '(', ')', and ' '.
s represents a valid expression.
'+' is not used as a unary operation (i.e., "+1" and "+(2 + 3)" is invalid).
'-' could be used as a unary operation (i.e., "-1" and "-(2 + 3)" is valid).
There will be no two consecutive operators in the input.
Every number and running calculation will fit in a signed 32-bit integer.
 */

public class BasicCalculator {
    public int calculate(String s) {
        // O(S)
        StringBuilder builder = new StringBuilder();
        builder.append("(");
        builder.append(s);
        builder.append(")");
        s = builder.toString();

        Stack<String> stack = new Stack<>();
        int i = 0;
        // O(S)
        while (i < s.length()) {
            char c = s.charAt(i);
            if (c >= '0' && c <= '9') {
                // find number
                int j = i;
                while (j < s.length() && s.charAt(j) >= '0' && s.charAt(j) <= '9') {
                    j += 1;
                }
                String num = s.substring(i, j);
                stack.push(num);
                i = j;
                continue;
            }

            if (c == '+' || c == '-' || c == '(') {
                stack.push(String.valueOf(c));
            } else if (c == ')') {
                int num = 0;
                while (stack.size() > 0 && !stack.peek().equals("(")) {
                    String popVal = stack.pop();
                    String sign = stack.peek().equals("(") ? "+" : stack.pop();
                    if (sign.equals("+")) {
                        num += Integer.valueOf(popVal);
                    } else {
                        num -= Integer.valueOf(popVal);
                    }
                }
                stack.pop(); // remove "("
                stack.push(String.valueOf(num));
            }
            i += 1;
        }

        return Integer.valueOf(stack.pop());
    }
}
