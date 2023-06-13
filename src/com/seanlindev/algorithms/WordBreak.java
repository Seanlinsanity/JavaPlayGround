package com.seanlindev.algorithms;

import java.util.List;

/*
Given a string s and a dictionary of strings wordDict, return true if s can be segmented into a space-separated sequence of one or more dictionary words.

Note that the same word in the dictionary may be reused multiple times in the segmentation.



Example 1:

Input: s = "leetcode", wordDict = ["leet","code"]
Output: true
Explanation: Return true because "leetcode" can be segmented as "leet code".
Example 2:

Input: s = "applepenapple", wordDict = ["apple","pen"]
Output: true
Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
Note that you are allowed to reuse a dictionary word.
Example 3:

Input: s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
Output: false


Constraints:

1 <= s.length <= 300
1 <= wordDict.length <= 1000
1 <= wordDict[i].length <= 20
s and wordDict[i] consist of only lowercase English letters.
All the strings of wordDict are unique.
 */
public class WordBreak {
    public boolean wordBreak(String s, List<String> wordDict) {
        Boolean[] memo = new Boolean[s.length() + 1];
        return wordBreak(0, s, wordDict, memo);
    }

    public boolean wordBreak(int start, String s, List<String> wordDict, Boolean[] memo) {
        if (start == s.length()) {
            memo[start] = true;
            return true;
        }

        if (memo[start] != null) {
            return memo[start];
        }

        Boolean isMatch = false;
        for (String str: wordDict) {
            if (start + str.length() > s.length()) {
                continue;
            }
            if (s.substring(start, start + str.length()).equals(str)) {
                if (wordBreak(start + str.length(), s, wordDict, memo)) {
                    isMatch = true;
                    break;
                }
            }
        }

        memo[start] = isMatch;
        return isMatch;
    }

    public boolean wordBreak2(String s, List<String> wordDict) {
        boolean[] dp = new boolean[s.length() + 1];


        for (int i = 0; i < dp.length; i++) {
            dp[i] = false;
        }

        dp[s.length()] = true;

        for (int i = s.length() - 1; i >= 0; i--) {
            for (String word : wordDict) {
                if (
                        ((i + word.length()) <= s.length()) &&
                                (s.substring(i, i + word.length()).startsWith(word))
                ) {
                    dp[i] = dp[i + word.length()];
                }
                if (dp[i]) {
                    break;
                }
            }
        }
        return dp[0];
    }
}
