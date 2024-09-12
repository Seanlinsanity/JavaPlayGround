package com.seanlindev.algorithms;

/*
You are given a binary string s. You are allowed to perform two types of operations on the string in any sequence:

Type-1: Remove the character at the start of the string s and append it to the end of the string.
Type-2: Pick any character in s and flip its value, i.e., if its value is '0' it becomes '1' and vice-versa.
Return the minimum number of type-2 operations you need to perform such that s becomes alternating.

The string is called alternating if no two adjacent characters are equal.

For example, the strings "010" and "1010" are alternating, while the string "0100" is not.


Example 1:

Input: s = "111000"
Output: 2
Explanation: Use the first operation two times to make s = "100011".
Then, use the second operation on the third and sixth elements to make s = "101010".
Example 2:

Input: s = "010"
Output: 0
Explanation: The string is already alternating.
Example 3:

Input: s = "1110"
Output: 1
Explanation: Use the second operation on the second element to make s = "1010".


Constraints:

1 <= s.length <= 105
s[i] is either '0' or '1'.
 */
public class MinimumFlipsMakeBinaryStringAlternating {
    public int minFlips(String s) {
        // edge case
        if (s.length() == 1) { return 0; }
        if (s.length() == 2) { return s.charAt(0) == s.charAt(1) ? 1 : 0; }

        String newString = s + s;
        StringBuilder builder1 = new StringBuilder();
        StringBuilder builder2 = new StringBuilder();
        for (int i = 0; i < s.length() * 2; i++) {
            builder1.append(i % 2 == 0 ? "0" : "1");
            builder2.append(i % 2 == 0 ? "1" : "0");
        }
        String alternatingStr1 = builder1.toString();
        String alternatingStr2 = builder2.toString();

        int result = s.length();
        int count1 = 0;
        int count2 = 0;
        for (int i = 0; i < s.length(); i++) {
            if (newString.charAt(i) != alternatingStr1.charAt(i)) {
                count1 += 1;
            }

            if (newString.charAt(i) != alternatingStr2.charAt(i)) {
                count2 += 1;
            }
        }

        result = Math.min(result, Math.min(count1, count2));

        int index = s.length();
        while (index < 2 * s.length()) {
            if (newString.charAt(index) != alternatingStr1.charAt(index)) {
                count1 += 1;
            }

            if (newString.charAt(index) != alternatingStr2.charAt(index)) {
                count2 += 1;
            }

            int startIndex = (index - s.length());
            if (newString.charAt(startIndex) != alternatingStr1.charAt(startIndex)) {
                count1 -= 1;
            }

            if (newString.charAt(startIndex) != alternatingStr2.charAt(startIndex)) {
                count2 -= 1;
            }

            result = Math.min(result, Math.min(count1, count2));
            index += 1;
        }

        return result;
    }
}
