package com.seanlindev.algorithms;

/*
You are given a string s and an integer k. You can choose any character of the string and change it to any other uppercase English character. You can perform this operation at most k times.

Return the length of the longest substring containing the same letter you can get after performing the above operations.



Example 1:

Input: s = "ABAB", k = 2
Output: 4
Explanation: Replace the two 'A's with two 'B's or vice versa.
Example 2:

Input: s = "AABABBA", k = 1
Output: 4
Explanation: Replace the one 'A' in the middle with 'B' and form "AABBBBA".
The substring "BBBB" has the longest repeating letters, which is 4.
There may exists other ways to achive this answer too.


Constraints:

1 <= s.length <= 105
s consists of only uppercase English letters.
0 <= k <= s.length
 */
public class LongestRepeatingCharacterReplacement {
    int[] charCount = new int[26];
    public int characterReplacement(String s, int k) {
        if (k == s.length()) { return k; }
        if (s.length() == 1) { return 1; }

        int left = 0;
        int result = 0;
        for (int i = 0; i < s.length(); i++) {
            charCount[s.charAt(i) - 'A'] += 1;

            while ((i - left + 1) - mostFrequnetCharCount() > k) {
                charCount[s.charAt(left) - 'A'] -= 1;
                left += 1;
            }

            result = Math.max(i - left + 1, result);
        }
        
        return result;
    }

    public int mostFrequnetCharCount() {
        int result = 0;
        for (int count: charCount) {
            result = Math.max(result, count);
        }
        return result;
    }
}
