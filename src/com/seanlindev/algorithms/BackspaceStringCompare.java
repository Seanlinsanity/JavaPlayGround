package com.seanlindev.algorithms;

/*
Given two strings s and t, return true if they are equal when both are typed into empty text editors. '#' means a backspace character.

Note that after backspacing an empty text, the text will continue empty.



Example 1:

Input: s = "ab#c", t = "ad#c"
Output: true
Explanation: Both s and t become "ac".
Example 2:

Input: s = "ab##", t = "c#d#"
Output: true
Explanation: Both s and t become "".
Example 3:

Input: s = "a#c", t = "b"
Output: false
Explanation: s becomes "c" while t becomes "b".


Constraints:

1 <= s.length, t.length <= 200
s and t only contain lowercase letters and '#' characters.


Follow up: Can you solve it in O(n) time and O(1) space?
 */

public class BackspaceStringCompare {
    public boolean backspaceCompare(String s, String t) {
        int index1 = s.length() - 1;
        int index2 = t.length() - 1;
        while (index1 >= 0 || index2 >= 0) {
            index1 = getNextIndex(s, index1);
            index2 = getNextIndex(t, index2);

            if (index1 < 0 && index2 < 0) {
                return true;
            }

            if (index1 < 0 || index2 < 0) {
                return false;
            }

            if (s.charAt(index1) != t.charAt(index2)) {
                return false;
            }

            index1 -= 1;
            index2 -= 1;
        }

        return true;
    }

    int getNextIndex(String str, int index) {
        char backspaceChar = '#';
        int backspaceCount = 0;
        while (index >= 0) {
            if (str.charAt(index) == backspaceChar) {
                backspaceCount += 1;
            } else if (backspaceCount == 0) {
                break;
            } else {
                backspaceCount -= 1;
            }

            index -= 1;
        }
        return index;
    }
}
