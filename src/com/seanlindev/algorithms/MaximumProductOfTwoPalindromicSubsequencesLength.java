package com.seanlindev.algorithms;

import java.util.HashMap;
import java.util.Map;

/*
Given a string s, find two disjoint palindromic subsequences of s such that the product of their lengths is maximized. The two subsequences are disjoint if they do not both pick a character at the same index.

Return the maximum possible product of the lengths of the two palindromic subsequences.

A subsequence is a string that can be derived from another string by deleting some or no characters without changing the order of the remaining characters. A string is palindromic if it reads the same forward and backward.



Example 1:

example-1
Input: s = "leetcodecom"
Output: 9
Explanation: An optimal solution is to choose "ete" for the 1st subsequence and "cdc" for the 2nd subsequence.
The product of their lengths is: 3 * 3 = 9.
Example 2:

Input: s = "bb"
Output: 1
Explanation: An optimal solution is to choose "b" (the first character) for the 1st subsequence and "b" (the second character) for the 2nd subsequence.
The product of their lengths is: 1 * 1 = 1.
Example 3:

Input: s = "accbcaxxcxx"
Output: 25
Explanation: An optimal solution is to choose "accca" for the 1st subsequence and "xxcxx" for the 2nd subsequence.
The product of their lengths is: 5 * 5 = 25.


Constraints:

2 <= s.length <= 12
s consists of lowercase English letters only.

 */
public class MaximumProductOfTwoPalindromicSubsequencesLength {
    public int maxProduct(String s) {
        int N = s.length();
        int res = 0;
        Map<Integer, Integer> pali = new HashMap<>();

        for (int mask = 1; mask < (1 << N); mask++) {
            StringBuilder subseq = new StringBuilder();
            for (int i = 0; i < N; i++) {
                if ((mask & (1 << i)) != 0) {
                    subseq.append(s.charAt(i));
                }
            }

            if (isPal(subseq.toString())) {
                pali.put(mask, subseq.length());
            }
        }

        for (int m1 : pali.keySet()) {
            for (int m2 : pali.keySet()) {
                if ((m1 & m2) == 0) {
                    res = Math.max(res, pali.get(m1) * pali.get(m2));
                }
            }
        }

        return res;
    }

    private boolean isPal(String s) {
        int i = 0, j = s.length() - 1;
        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }
}
