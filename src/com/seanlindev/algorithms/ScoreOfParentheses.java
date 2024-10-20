package com.seanlindev.algorithms;

import java.util.Stack;

/*
Given a balanced parentheses string s, return the score of the string.

The score of a balanced parentheses string is based on the following rule:

"()" has score 1.
AB has score A + B, where A and B are balanced parentheses strings.
(A) has score 2 * A, where A is a balanced parentheses string.


Example 1:

Input: s = "()"
Output: 1
Example 2:

Input: s = "(())"
Output: 2
Example 3:

Input: s = "()()"
Output: 2


Constraints:

2 <= s.length <= 50
s consists of only '(' and ')'.
s is a balanced parentheses string.
 */
public class ScoreOfParentheses {
    public int scoreOfParentheses(String s) {
        Stack<String> stack = new Stack<>();
        for (char character: s.toCharArray()) {
            if (character != ')') {
                stack.push(String.valueOf(character));
                continue;
            }

            Integer num = -1;
            while (!stack.peek().equals("(")) {
                num = Integer.valueOf(stack.pop());
            }

            if (num == -1) {
                num = 1;
            } else {
                num = num * 2;
            }

            stack.pop();
            if (stack.size() > 0 && !stack.peek().equals("(")) {
                String pop = stack.pop();
                num += Integer.valueOf(pop);
            }

            stack.push(String.valueOf(num));
        }

        return Integer.valueOf(stack.peek());
    }
}
