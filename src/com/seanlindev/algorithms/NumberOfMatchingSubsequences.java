package com.seanlindev.algorithms;

import java.util.HashMap;
import java.util.Map;
/*
Given a string s and an array of strings words, return the number of words[i] that is a subsequence of s.

A subsequence of a string is a new string generated from the original string with some characters (can be none) deleted without changing the relative order of the remaining characters.

For example, "ace" is a subsequence of "abcde".


Example 1:

Input: s = "abcde", words = ["a","bb","acd","ace"]
Output: 3
Explanation: There are three strings in words that are a subsequence of s: "a", "acd", "ace".
Example 2:

Input: s = "dsahjpjauf", words = ["ahjpjau","ja","ahbwzgqnuk","tnmlanowax"]
Output: 2


Constraints:

1 <= s.length <= 5 * 104
1 <= words.length <= 5000
1 <= words[i].length <= 50
s and words[i] consist of only lowercase English letters.
 */

public class NumberOfMatchingSubsequences {
    public int numMatchingSubseq(String s, String[] words) {
        int result = 0;
        Map<String, Boolean> wordMatchMap = new HashMap<>();
        for (String word: words) {
            Boolean cache = wordMatchMap.get(word);
            if (cache != null) {
                if (cache) { result += 1; }
                continue;
            }

            boolean isMatch = isSubsequence(s, word);
            if (isMatch) {
                result += 1;
            }
            wordMatchMap.put(word, isMatch);
        }
        return result;
    }

    boolean isSubsequence(String s, String word) {
        // edge case
        if (s.length() < word.length()) { return false; }

        var index1 = 0;
        var index2 = 0;
        while (index1 < s.length()) {
            char char1 = s.charAt(index1);
            char char2 = word.charAt(index2);
            if (char1 == char2) {
                index1 += 1;
                index2 += 1;
            } else {
                index1 += 1;
            }

            if (index2 == word.length()) {
                return true;
            }
        }

        return false;
    }
}
