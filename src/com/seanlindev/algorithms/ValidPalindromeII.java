package com.seanlindev.algorithms;

/*
Given a string s, return true if the s can be palindrome after deleting at most one character from it.



Example 1:

Input: s = "aba"
Output: true
Example 2:

Input: s = "abca"
Output: true
Explanation: You could delete the character 'c'.
Example 3:

Input: s = "abc"
Output: false


Constraints:

1 <= s.length <= 105
s consists of lowercase English letters.
 */

public class ValidPalindromeII {
    public boolean validPalindrome(String s) {
        return isPalindrome(0, s.length() - 1, false, s.toCharArray());
    }

    boolean isPalindrome(int left, int right, boolean isDeleted, char[] chars) {
        while (left < right) {
            if (chars[left] == chars[right]) {
                left += 1;
                right -= 1;
            } else if (!isDeleted) {
                return isPalindrome(left + 1, right, true, chars) ||
                        isPalindrome(left, right - 1, true, chars);
            } else {
                return false;
            }
        }

        return true;
    }
}
