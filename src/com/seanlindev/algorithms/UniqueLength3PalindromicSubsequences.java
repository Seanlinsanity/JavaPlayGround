package com.seanlindev.algorithms;

import java.util.HashSet;
import java.util.Set;

/*
Given a string s, return the number of unique palindromes of length three that are a subsequence of s.

Note that even if there are multiple ways to obtain the same subsequence, it is still only counted once.

A palindrome is a string that reads the same forwards and backwards.

A subsequence of a string is a new string generated from the original string with some characters (can be none) deleted without changing the relative order of the remaining characters.

For example, "ace" is a subsequence of "abcde".


Example 1:

Input: s = "aabca"
Output: 3
Explanation: The 3 palindromic subsequences of length 3 are:
- "aba" (subsequence of "aabca")
- "aaa" (subsequence of "aabca")
- "aca" (subsequence of "aabca")
Example 2:

Input: s = "adc"
Output: 0
Explanation: There are no palindromic subsequences of length 3 in "adc".
Example 3:

Input: s = "bbcbaba"
Output: 4
Explanation: The 4 palindromic subsequences of length 3 are:
- "bbb" (subsequence of "bbcbaba")
- "bcb" (subsequence of "bbcbaba")
- "bab" (subsequence of "bbcbaba")
- "aba" (subsequence of "bbcbaba")


Constraints:

3 <= s.length <= 105
s consists of only lowercase English letters.
 */
public class UniqueLength3PalindromicSubsequences {
    public int countPalindromicSubsequence(String s) {
        Set<String> uniquePalindromes = new HashSet<>();
        for (char c = 'a'; c <= 'z'; c++) {
            int first = s.indexOf(c);
            int last = s.lastIndexOf(c);
            if (first < last) { // Ensure there are characters in between
                for (int j = first + 1; j < last; j++) {
                    uniquePalindromes.add(String.valueOf(c) + s.charAt(j) + c);
                }
            }
        }
        return uniquePalindromes.size();

        // Approach 2
        // char[] letters = new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k',
        //                             'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
        //                             'w', 'x', 'y', 'z'};
        // Set<Character> left = new HashSet<>();
        // Map<Character, Integer> right = new HashMap<>();
        // for(char c : s.toCharArray()) {
        //     right.put(c, right.getOrDefault(c, 0) + 1);
        // }
        // Set<String> res = new HashSet<>();

        // for(int mid = 0; mid < s.length(); mid++) {
        //     char c = s.charAt(mid);

        //     right.put(c, right.get(c)-1);
        //     if(right.get(c) == 0) {
        //         right.remove(c);
        //     }

        //     for(int i=0; i<26; i++) {
        //         if(left.contains(letters[i]) && right.containsKey(letters[i])) {
        //             res.add("" + letters[i] + c + letters[i]);
        //         }
        //     }

        //     left.add(c);
        // }

        // return res.size();

        // Approach 3
        // Set<String> set = new HashSet<>();
        // Set<String> dp = new HashSet<>();
        // countPalindromeRecursion(0, "", s, set, dp);
        // return set.size();
    }

    void countPalindromeRecursion(int index, String currentStr, String s, Set<String> set, Set<String> dp) {
        if (currentStr.length() == 3) {
            if (currentStr.charAt(0) == currentStr.charAt(2)) {
                set.add(currentStr);
            }
            return;
        }

        if (index == s.length()) { return; }
        String key = String.valueOf(index) + currentStr;
        if (dp.contains(key)) { return; }
        countPalindromeRecursion(index + 1, currentStr + s.charAt(index), s, set, dp);
        countPalindromeRecursion(index + 1, currentStr, s, set, dp);
        dp.add(key);
    }
}
