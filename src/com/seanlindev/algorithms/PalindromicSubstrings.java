package com.seanlindev.algorithms;

/*
Given a string s, return the number of palindromic substrings in it.

A string is a palindrome when it reads the same backward as forward.

A substring is a contiguous sequence of characters within the string.



Example 1:

Input: s = "abc"
Output: 3
Explanation: Three palindromic strings: "a", "b", "c".
Example 2:

Input: s = "aaa"
Output: 6
Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".


Constraints:

1 <= s.length <= 1000
s consists of lowercase English letters.
 */
public class PalindromicSubstrings {
    public int countSubstrings(String s) {
        if (s.length() == 1) { return  1; }

        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            count += countPalindrome(i, i, s);
            count += countPalindrome(i, i + 1, s);
        }

        return count;
    }

    public int countPalindrome(int start, int end, String s) {
        if (start < 0 || end >= s.length()) { return 0; }
        int count = 0;
        while (start >= 0 && end < s.length() && s.charAt(start) == s.charAt(end)) {
            count += 1;
            start -= 1;
            end += 1;
        }

        return count;
    }
}
