package com.seanlindev.algorithms;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*
Given two strings s and t of lengths m and n respectively, return the minimum window
substring
 of s such that every character in t (including duplicates) is included in the window. If there is no such substring, return the empty string "".

The testcases will be generated such that the answer is unique.



Example 1:

Input: s = "ADOBECODEBANC", t = "ABC"
Output: "BANC"
Explanation: The minimum window substring "BANC" includes 'A', 'B', and 'C' from string t.
Example 2:

Input: s = "a", t = "a"
Output: "a"
Explanation: The entire string s is the minimum window.
Example 3:

Input: s = "a", t = "aa"
Output: ""
Explanation: Both 'a's from t must be included in the window.
Since the largest window of s only has one 'a', return empty string.


Constraints:

m == s.length
n == t.length
1 <= m, n <= 105
s and t consist of uppercase and lowercase English letters.
 */
public class MinimumWindowSubstring {
    public String minWindow(String s, String t) {
        if (s.length() < t.length()) { return ""; }

        //build target map
        Map<Character, Integer> target = new HashMap<>(); //{a: 1}
        for (char character: t.toCharArray()) {
            target.putIfAbsent(character, 0);
            int count = target.get(character);
            target.put(character, count + 1);
        }

        Map<Character, Integer> window = new HashMap<>(); //{}
        Set<Character> matches = new HashSet<>(); //{}
        int left = 0;
        int right = 0;
        int length = Integer.MAX_VALUE;
        int start = -1;
        while (right < s.length()) {
            char currentChar = s.charAt(right);
            if (target.get(currentChar) != null) {
                //add window
                window.putIfAbsent(currentChar, 0);
                int count = window.get(currentChar);
                window.put(currentChar, count + 1); //window:{a: 1}

                //check match
                if (count + 1 >= target.get(currentChar)) {
                    matches.add(currentChar); //{a}
                }

                while (matches.size() == target.keySet().size()) {
                    //find min ans
                    if (right - left + 1 < length) {
                        length = right - left + 1;
                        start = left;
                    }

                    if (target.get(s.charAt(left)) != null) {
                        int lastCount = window.get(s.charAt(left));
                        window.put(s.charAt(left), lastCount - 1);
                        if (lastCount - 1 < target.get(s.charAt(left))) {
                            matches.remove(s.charAt(left));
                        }
                    }

                    left += 1;
                }
            }

            right += 1;
        }

        return (length == Integer.MAX_VALUE) ? "" : s.substring(start, start + length);
    }
}
