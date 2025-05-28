package com.seanlindev.algorithms;

import java.util.HashSet;
import java.util.Set;

/*
Given a string s, return the length of the longest repeating substrings. If no repeating substring exists, return 0.



Example 1:

Input: s = "abcd"
Output: 0
Explanation: There is no repeating substring.
Example 2:

Input: s = "abbaba"
Output: 2
Explanation: The longest repeating substrings are "ab" and "ba", each of which occurs twice.
Example 3:

Input: s = "aabcaabdaab"
Output: 3
Explanation: The longest repeating substring is "aab", which occurs 3 times.


Constraints:

1 <= s.length <= 2000
s consists of lowercase English letters.

s =
"abbaba"
        a/[ab, abb, abba, abbab, abbaba]
        ab/[abb, abba, abbab, abbaba]
        abb/[abba, abbab, abbaba]
        abba/[abbab, abbaba]
        abbab/[abbaba]

    i   X   0   1   2   3   4   5
j       X   a   b   b   a   b   a
x   X   X   0   0   0   0   0   0
0   a   0   X   0   0   1   0   1
1   b   0   0   X   1   0   2   0
2   b   0   0   0   X   0   1   0
3   a   0   0   0   0   X   0   2
4   b   0   0   0   0   0   X   0
5   a   0   0   0   0   0   0   X



 */
public class LongestRepeatingSubstring {
    // "abbaba"
    public int longestRepeatingSubstring(String s) {
        if (s.length() == 1) return 0;

        int result = 0;
        int[][] dp = new int[s.length() + 1][s.length() + 1];
        for (int i = 1; i < s.length() + 1; i++) {
            for (int j = i + 1; j < s.length() + 1; j++) {
                if (s.charAt(i - 1) == s.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                    result = Math.max(result, dp[i][j]);
                }
            }
        }

        return result;

        // int low = 1;
        // int high = s.length() - 1;
        // while (low <= high) {
        //     int mid = (low + high) / 2;
        //     if (isValid(mid, s)) {
        //         low = mid + 1;
        //     } else {
        //         high = mid - 1;
        //     }
        // }

        // return high;
    }

    boolean isValid(int length, String s) {
        Set<String> strSet = new HashSet<>();
        for (int i = 0; i < s.length() - length + 1; i++) {
            String substring = s.substring(i, i + length);
            if (strSet.contains(substring)) {
                return true;
            }
            strSet.add(substring);
        }

        return false;
    }
}
