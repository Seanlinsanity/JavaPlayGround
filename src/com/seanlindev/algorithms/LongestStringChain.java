package com.seanlindev.algorithms;

import java.util.Arrays;

/*
You are given an array of words where each word consists of lowercase English letters.

wordA is a predecessor of wordB if and only if we can insert exactly one letter anywhere in wordA without changing the order of the other characters to make it equal to wordB.

For example, "abc" is a predecessor of "abac", while "cba" is not a predecessor of "bcad".
A word chain is a sequence of words [word1, word2, ..., wordk] with k >= 1, where word1 is a predecessor of word2, word2 is a predecessor of word3, and so on. A single word is trivially a word chain with k == 1.

Return the length of the longest possible word chain with words chosen from the given list of words.



Example 1:

Input: words = ["a","b","ba","bca","bda","bdca"]
Output: 4
Explanation: One of the longest word chains is ["a","ba","bda","bdca"].
Example 2:

Input: words = ["xbc","pcxbcf","xb","cxbc","pcxbc"]
Output: 5
Explanation: All the words can be put in a word chain ["xb", "xbc", "cxbc", "pcxbc", "pcxbcf"].
Example 3:

Input: words = ["abcd","dbqca"]
Output: 1
Explanation: The trivial word chain ["abcd"] is one of the longest word chains.
["abcd","dbqca"] is not a valid word chain because the ordering of the letters is changed.


Constraints:

1 <= words.length <= 1000
1 <= words[i].length <= 16
words[i] only consists of lowercase English letters.
 */
public class LongestStringChain {
    public int longestStrChain(String[] words) {
        Arrays.sort(words, (a, b) -> Integer.compare(a.length(), b.length()));
        // System.out.println(Arrays.toString(words));
        int[] dp = new int[words.length];
        Arrays.fill(dp, 1);
        int result = 0;
        for (int i = words.length - 1; i >= 0; i--) {
            for (int j = i + 1; j < words.length; j++) {
                if (isPredecessor(words[i], words[j])) {
                    dp[i] = Math.max(dp[i], 1 + dp[j]);
                }
            }
            result = Math.max(result, dp[i]);
        }

        return result;
    }

    boolean isPredecessor(String word1, String word2) {
        if (word2.length() - word1.length() != 1) { return false; }

        int idx1 = 0;
        int idx2 = 0;
        while (idx1 < word1.length() && idx2 < word2.length()) {
            if (word1.charAt(idx1) == word2.charAt(idx2)) {
                idx1 += 1;
                idx2 += 1;
            } else if (idx2 == idx1) {
                idx2 += 1;
            } else {
                return false;
            }
        }

        return idx1 == word1.length();
    }
}
