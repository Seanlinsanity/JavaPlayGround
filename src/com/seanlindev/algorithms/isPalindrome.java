package com.seanlindev.algorithms;

/*
A phrase is a palindrome if, after converting all uppercase letters into lowercase letters and removing all non-alphanumeric characters, it reads the same forward and backward. Alphanumeric characters include letters and numbers.

Given a string s, return true if it is a palindrome, or false otherwise.



Example 1:

Input: s = "A man, a plan, a canal: Panama"
Output: true
Explanation: "amanaplanacanalpanama" is a palindrome.
Example 2:

Input: s = "race a car"
Output: false
Explanation: "raceacar" is not a palindrome.
Example 3:

Input: s = " "
Output: true
Explanation: s is an empty string "" after removing non-alphanumeric characters.
Since an empty string reads the same forward and backward, it is a palindrome.


Constraints:

1 <= s.length <= 2 * 105
s consists only of printable ASCII characters.
 */
public class isPalindrome {
    public boolean isPalindrome(String s) {
        char[] characters = s.toCharArray();
        int left = 0;
        int right = characters.length - 1;
        while (left < right) {
            if (!Character.isLetterOrDigit(characters[right])) {
                right -= 1;
                continue;
            }

            if (!Character.isLetterOrDigit(characters[left])) {
                left += 1;
                continue;
            }

            if (Character.toLowerCase(characters[left]) != Character.toLowerCase(characters[right])) {
                return false;
            }

            left += 1;
            right -= 1;
        }

        return true;
    }
}
