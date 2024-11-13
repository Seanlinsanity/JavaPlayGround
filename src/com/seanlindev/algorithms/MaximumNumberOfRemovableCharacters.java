package com.seanlindev.algorithms;

/*
You are given two strings s and p where p is a subsequence of s. You are also given a distinct 0-indexed integer array removable containing a subset of indices of s (s is also 0-indexed).

You want to choose an integer k (0 <= k <= removable.length) such that, after removing k characters from s using the first k indices in removable, p is still a subsequence of s. More formally, you will mark the character at s[removable[i]] for each 0 <= i < k, then remove all marked characters and check if p is still a subsequence.

Return the maximum k you can choose such that p is still a subsequence of s after the removals.

A subsequence of a string is a new string generated from the original string with some characters (can be none) deleted without changing the relative order of the remaining characters.



Example 1:

Input: s = "abcacb", p = "ab", removable = [3,1,0]
Output: 2
Explanation: After removing the characters at indices 3 and 1, "abcacb" becomes "accb".
"ab" is a subsequence of "accb".
If we remove the characters at indices 3, 1, and 0, "abcacb" becomes "ccb", and "ab" is no longer a subsequence.
Hence, the maximum k is 2.
Example 2:

Input: s = "abcbddddd", p = "abcd", removable = [3,2,1,4,5,6]
Output: 1
Explanation: After removing the character at index 3, "abcbddddd" becomes "abcddddd".
"abcd" is a subsequence of "abcddddd".
Example 3:

Input: s = "abcab", p = "abc", removable = [0,1,2,3,4]
Output: 0
Explanation: If you remove the first index in the array removable, "abc" is no longer a subsequence.


Constraints:

1 <= p.length <= s.length <= 105
0 <= removable.length < s.length
0 <= removable[i] < s.length
p is a subsequence of s.
s and p both consist of lowercase English letters.
The elements in removable are distinct.
 */
public class MaximumNumberOfRemovableCharacters {
    public int maximumRemovals(String s, String p, int[] removable) {
        int low = 0;
        int high = removable.length - 1;
        int result = 0;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (isValid(s, p, removable, mid)) {
                low = mid + 1;
                result = Math.max(result, mid + 1);
            } else {
                high = mid - 1;
            }
        }

        return result;
    }

    boolean isValid(String s, String p, int[] removable, int index) {
        char[] sChar = s.toCharArray();
        char[] pChar = p.toCharArray();
        for (int i = 0; i <= index; i++) {
            sChar[removable[i]] = '0';
        }

        int idx1 = 0;
        int idx2 = 0;
        while (idx1 < sChar.length && idx2 < pChar.length) {
            if (pChar[idx2] == sChar[idx1]) {
                idx2 += 1;
                idx1 += 1;
            } else {
                idx1 += 1;
            }
        }

        return idx2 == pChar.length;
    }
}
