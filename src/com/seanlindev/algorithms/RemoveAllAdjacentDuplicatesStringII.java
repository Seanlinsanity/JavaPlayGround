package com.seanlindev.algorithms;

import java.util.Stack;

/*
You are given a string s and an integer k, a k duplicate removal consists of choosing k adjacent and equal letters from s and removing them, causing the left and the right side of the deleted substring to concatenate together.

We repeatedly make k duplicate removals on s until we no longer can.

Return the final string after all such duplicate removals have been made. It is guaranteed that the answer is unique.



Example 1:

Input: s = "abcd", k = 2
Output: "abcd"
Explanation: There's nothing to delete.
Example 2:

Input: s = "deeedbbcccbdaa", k = 3
Output: "aa"
Explanation:
First delete "eee" and "ccc", get "ddbbbdaa"
Then delete "bbb", get "dddaa"
Finally delete "ddd", get "aa"
Example 3:

Input: s = "pbbcggttciiippooaais", k = 2
Output: "ps"


Constraints:

1 <= s.length <= 105
2 <= k <= 104
s only contains lowercase English letters.
 */

public class RemoveAllAdjacentDuplicatesStringII {
    public String removeDuplicates(String s, int k) {
        Stack<int[]> stack = new Stack<>();
        for (char character: s.toCharArray()) {
            int charNum = character - 'a';
            if (stack.size() > 0 && stack.peek()[0] == charNum) {
                if (stack.peek()[1] == k - 1) {
                    stack.pop();
                } else {
                    stack.peek()[1] = 1 + stack.peek()[1];
                }
            } else {
                stack.push(new int[]{ charNum , 1 });
            }
        }

        StringBuilder builder = new StringBuilder();
        for (int[] node: stack) {
            char character = (char)('a' + node[0]);
            int count = node[1];
            for (int i = 0; i < count; i++) {
                builder.append(character);
            }
        }

        return builder.toString();
    }
}
