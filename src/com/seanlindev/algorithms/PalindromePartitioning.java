package com.seanlindev.algorithms;

import java.util.ArrayList;
import java.util.List;

/*
Given a string s, partition s such that every
substring
 of the partition is a
palindrome
. Return all possible palindrome partitioning of s.



Example 1:

Input: s = "aab"
Output: [["a","a","b"],["aa","b"]]
Example 2:

Input: s = "a"
Output: [["a"]]


Constraints:

1 <= s.length <= 16
s contains only lowercase English letters.
 */

public class PalindromePartitioning {
    public List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<>();

        // edge case
        if (s.length() == 1) {
            result.add(List.of(s));
            return result;
        }

        backtracking(0, new ArrayList<>(), s, result);
        return result;
    }

    void backtracking(int index, List<String> currentSubstrings, String s, List<List<String>> result) {
        if (index == s.length()) {
            result.add(new ArrayList<>(currentSubstrings));
        }

        for (int i = index; i < s.length(); i++) {
            String substring = s.substring(index, i + 1);
            if (isPalindrome(substring)) {
                currentSubstrings.add(substring);
                backtracking(i + 1, currentSubstrings, s, result);
                currentSubstrings.remove(currentSubstrings.size() - 1);
            }
        }
    }

    boolean isPalindrome(String str) {
        if (str.length() == 1) { return true; }
        int left = 0;
        int right = str.length() - 1;
        while (left < right) {
            if (str.charAt(left) != str.charAt(right)) {
                return false;
            }

            left += 1;
            right -= 1;
        }

        return true;
    }
}
