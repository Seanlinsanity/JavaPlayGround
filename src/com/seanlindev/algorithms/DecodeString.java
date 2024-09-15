package com.seanlindev.algorithms;

import java.util.Stack;

/*
Given an encoded string, return its decoded string.

The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times. Note that k is guaranteed to be a positive integer.

You may assume that the input string is always valid; there are no extra white spaces, square brackets are well-formed, etc. Furthermore, you may assume that the original data does not contain any digits and that digits are only for those repeat numbers, k. For example, there will not be input like 3a or 2[4].

The test cases are generated so that the length of the output will never exceed 105.



Example 1:

Input: s = "3[a]2[bc]"
Output: "aaabcbc"
Example 2:

Input: s = "3[a2[c]]"
Output: "accaccacc"
Example 3:

Input: s = "2[abc]3[cd]ef"
Output: "abcabccdcdcdef"


Constraints:

1 <= s.length <= 30
s consists of lowercase English letters, digits, and square brackets '[]'.
s is guaranteed to be a valid input.
All the integers in s are in the range [1, 300].
 */
public class DecodeString {
    public String decodeString(String s) {
        StringBuilder builder;
        Stack<String> stack = new Stack<>(); //s=[]
        for (char c: s.toCharArray()) {
            if (c == ']') {
                // get encoded str
                String baseStr = "";
                while (!stack.peek().equals("[")) { //s=[3,[] => [aaa,2,[]
                    String popStr = stack.pop(); //[aaa,2,[,b]
                    baseStr = popStr + baseStr; //bc
                }
                stack.pop(); // remove '[' //s=[3] => [aaa,2]

                // get repeated
                String repeatedStr = "";
                while (!stack.isEmpty() && isNumber(stack.peek())) {
                    String popStr = stack.pop(); //s=[]
                    repeatedStr = popStr + repeatedStr; //3
                }
                int repeated = Integer.valueOf(repeatedStr);

                // decoded str
                builder = new StringBuilder();
                for (int i = 0; i < repeated; i++) {
                    builder.append(baseStr);
                }
                stack.add(builder.toString()); //s=[aaa]
            } else {
                stack.add(String.valueOf(c)); //s=[3,[,a] => [aaa,2,[,b,c]
            }
        }

        builder = new StringBuilder();
        for (String str: stack) {
            builder.append(str);
        }

        return builder.toString();
    }

    boolean isNumber(String s) {
        try {
            Integer.valueOf(s);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
}
