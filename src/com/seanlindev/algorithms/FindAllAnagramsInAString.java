package com.seanlindev.algorithms;

import java.util.ArrayList;
import java.util.List;

/*
Given two strings s and p, return an array of all the start indices of p's anagrams in s. You may return the answer in any order.



Example 1:

Input: s = "cbaebabacd", p = "abc"
Output: [0,6]
Explanation:
The substring with start index = 0 is "cba", which is an anagram of "abc".
The substring with start index = 6 is "bac", which is an anagram of "abc".
Example 2:

Input: s = "abab", p = "ab"
Output: [0,1,2]
Explanation:
The substring with start index = 0 is "ab", which is an anagram of "ab".
The substring with start index = 1 is "ba", which is an anagram of "ab".
The substring with start index = 2 is "ab", which is an anagram of "ab".


Constraints:

1 <= s.length, p.length <= 3 * 104
s and p consist of lowercase English letters.
 */

public class FindAllAnagramsInAString {
    public List<Integer> findAnagrams(String s, String p) {
        if (p.length() > s.length()) { return new ArrayList<>(); }

        int[] pCharCount = new int[26];
        for (char c: p.toCharArray()) {
            pCharCount[c - 'a'] += 1;
        }

        int[] currentCharCount = new int[26];
        for (int i = 0; i < p.length() - 1; i++) {
            char c = s.charAt(i);
            currentCharCount[c - 'a'] += 1;
        }

        List<Integer> result = new ArrayList<>();
        int left = 0, right = p.length() - 1;
        while (right < s.length()) {
            currentCharCount[s.charAt(right) - 'a'] += 1;
            if (isAnagram(currentCharCount, pCharCount)) {
                result.add(left);
            }

            right += 1;
            currentCharCount[s.charAt(left) - 'a'] -= 1;
            left += 1;
        }

        return result;
    }

    boolean isAnagram(int[] charCount1, int[] charCount2) {
        for (int i = 0; i < 26; i++) {
            if (charCount1[i] != charCount2[i]) {
                return false;
            }
        }

        return true;
    }
}
