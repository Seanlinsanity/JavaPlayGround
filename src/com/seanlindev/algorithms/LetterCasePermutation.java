package com.seanlindev.algorithms;

import java.util.ArrayList;
import java.util.List;

/*
Given a string s, you can transform every letter individually to be lowercase or uppercase to create another string.

Return a list of all possible strings we could create. Return the output in any order.



Example 1:

Input: s = "a1b2"
Output: ["a1b2","a1B2","A1b2","A1B2"]
Example 2:

Input: s = "3z4"
Output: ["3z4","3Z4"]


Constraints:

1 <= s.length <= 12
s consists of lowercase English letters, uppercase English letters, and digits.
 */

public class LetterCasePermutation {
    public List<String> letterCasePermutation(String s) {
        List<String> result = new ArrayList<>();
        backTracking(0, new ArrayList<>(), s, result);
        return result;
    }

    void backTracking(int index, List<Character> chars, String s, List<String> result) {
        if (index == s.length()) {
            StringBuilder builder = new StringBuilder();
            for (Character character: chars) {
                builder.append(String.valueOf(character));
            }
            result.add(builder.toString());
            return;
        }

        Character currentChar = s.charAt(index);
        if (Character.isDigit(currentChar)) {
            chars.add(currentChar);
            backTracking(index + 1, chars, s, result);
            chars.remove(chars.size() - 1);
        } else {
            chars.add(Character.toLowerCase(currentChar));
            backTracking(index + 1, chars, s, result);
            chars.remove(chars.size() - 1);

            chars.add(Character.toUpperCase(currentChar));
            backTracking(index + 1, chars, s, result);
            chars.remove(chars.size() - 1);
        }
    }
}
