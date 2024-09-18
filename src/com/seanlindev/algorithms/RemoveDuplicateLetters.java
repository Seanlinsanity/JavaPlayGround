package com.seanlindev.algorithms;

import java.util.Stack;

/*
Given a string s, remove duplicate letters so that every letter appears once and only once. You must make sure your result is
the smallest in lexicographical order
 among all possible results.



Example 1:

Input: s = "bcabc"
Output: "abc"
Example 2:

Input: s = "cbacdcbc"
Output: "acdb"


Constraints:

1 <= s.length <= 104
s consists of lowercase English letters.

 */
public class RemoveDuplicateLetters {
    public String removeDuplicateLetters(String s) {
        // edge
        if (s.length() == 1) { return s; }

        int[] charIndexList = new int[26];
        for (int i = 0; i < s.length(); i++) {
            char character = s.charAt(i);
            charIndexList[character - 'a'] = i;
        }

        Stack<Character> stack = new Stack<>();
        boolean[] existed = new boolean[26];
        for (int i = 0; i < s.length(); i++) {
            char character = s.charAt(i);
            if (!existed[character - 'a']) {
                while (stack.size() > 0) {
                    if (stack.peek() > character && charIndexList[stack.peek() - 'a'] > i) {
                        char popChar = stack.pop();
                        existed[popChar - 'a'] = false;
                    } else {
                        break;
                    }
                }
                stack.push(character);
                existed[character - 'a'] = true;
            }
        }

        StringBuilder builder = new StringBuilder();
        for (Character character: stack) {
            builder.append(character);
        }

        return builder.toString();
    }
}
