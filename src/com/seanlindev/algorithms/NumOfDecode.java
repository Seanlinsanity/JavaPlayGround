package com.seanlindev.algorithms;

/*
A message containing letters from A-Z can be encoded into numbers using the following mapping:

'A' -> "1"
'B' -> "2"
...
'Z' -> "26"
To decode an encoded message, all the digits must be grouped then mapped back into letters using the reverse of the mapping above (there may be multiple ways). For example, "11106" can be mapped into:

"AAJF" with the grouping (1 1 10 6)
"KJF" with the grouping (11 10 6)
Note that the grouping (1 11 06) is invalid because "06" cannot be mapped into 'F' since "6" is different from "06".

Given a string s containing only digits, return the number of ways to decode it.

The test cases are generated so that the answer fits in a 32-bit integer.



Example 1:

Input: s = "12"
Output: 2
Explanation: "12" could be decoded as "AB" (1 2) or "L" (12).
Example 2:

Input: s = "226"
Output: 3
Explanation: "226" could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).
Example 3:

Input: s = "06"
Output: 0
Explanation: "06" cannot be mapped to "F" because of the leading zero ("6" is different from "06").


Constraints:

1 <= s.length <= 100
s contains only digits and may contain leading zero(s).
 */
public class NumOfDecode {
    public int numDecodings(String s) {
        int[] dp = new int[s.length() + 1];

        dp[0] = 1; // empty string
        dp[1] = s.charAt(0) == '0' ? 0 : 1;
        for (int i = 2; i < s.length() + 1; i++) {
            //i = 2
            if (s.charAt(i - 1) != '0') {
                dp[i] += dp[i - 1];
            }
            if (
                    s.charAt(i - 2) == '1' ||
                            (s.charAt(i - 2) == '2' && s.charAt(i - 1) < '7')
            ) {
                dp[i] += dp[i - 2];
            }
        }
        return dp[s.length()];
    }

    public int numDecodings2(String s) {
        if (s.charAt(0) == '0') { return  0; }
        if (s.length() == 1) { return 1; }

        Integer[] memo = new Integer[s.length()];
        int count = numberOfDecode(0, s, memo);
        return count;
    }

    public int numberOfDecode(int start, String s, Integer[] memo) {
        if (memo[start] != null) { return memo[start]; }
        if (s.charAt(start) == '0') {
            memo[start] = 0;
            return 0;
        }

        if (start == s.length() - 1) {
            memo[start] = 1;
            return 1;
        }

        //start = 1
        int count = 0;
        count += numberOfDecode(start + 1, s, memo);

        if (
                start + 1 < s.length() &&
                        (s.charAt(start) == '1' || (s.charAt(start) == '2' && s.charAt(start + 1) <= '6'))
        ) {
            if (start + 1 == s.length() - 1) {
                count += 1;
            } else {
                count += numberOfDecode(start + 2, s, memo);
            }
        }

        memo[start] = count;
        return count;
    }
}
