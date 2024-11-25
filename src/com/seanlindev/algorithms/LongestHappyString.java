package com.seanlindev.algorithms;

import java.util.PriorityQueue;

/*
A string s is called happy if it satisfies the following conditions:

s only contains the letters 'a', 'b', and 'c'.
s does not contain any of "aaa", "bbb", or "ccc" as a substring.
s contains at most a occurrences of the letter 'a'.
s contains at most b occurrences of the letter 'b'.
s contains at most c occurrences of the letter 'c'.
Given three integers a, b, and c, return the longest possible happy string. If there are multiple longest happy strings, return any of them. If there is no such string, return the empty string "".

A substring is a contiguous sequence of characters within a string.



Example 1:

Input: a = 1, b = 1, c = 7
Output: "ccaccbcc"
Explanation: "ccbccacc" would also be a correct answer.
Example 2:

Input: a = 7, b = 1, c = 0
Output: "aabaa"
Explanation: It is the only correct answer in this case.


Constraints:

0 <= a, b, c <= 100
a + b + c > 0
 */
public class LongestHappyString {
    public String longestDiverseString(int a, int b, int c) {
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>((x, y) -> Integer.compare(y[0], x[0]));
        if (a > 0) {
            maxHeap.add(new int[]{ a, 0 });
        }

        if (b > 0) {
            maxHeap.add(new int[]{ b, 1 });
        }

        if (c > 0) {
            maxHeap.add(new int[]{ c, 2 });
        }

        int[] prev = null; //[6,c]
        StringBuilder builder = new StringBuilder();
        while (!maxHeap.isEmpty()) { //{[40,b],[4,a]}
            int[] pollVal = maxHeap.poll(); //[40,b]
            int count = Math.min(pollVal[0], 2); //2
            char character = (char)('a' + pollVal[1]);
            if (prev != null && prev[0] > pollVal[0]) {
                count = 1; //1
            }
            for (int i = 1; i <= count; i++) {
                builder.append(String.valueOf(character)); //bbc
            }

            if (prev != null) {
                maxHeap.add(prev); //{[40,b],[4,a]}
            }

            if (pollVal[0] - count > 0) {
                prev = new int[]{ pollVal[0] - count, pollVal[1] }; //[6,c]
            } else {
                prev = null;
            }
        }

        return builder.toString();
    }
}
