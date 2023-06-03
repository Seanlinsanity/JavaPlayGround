package com.seanlindev.algorithms;

import java.util.Arrays;

/*
Given two strings s and t, return true if t is an anagram of s, and false otherwise.

An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all the original letters exactly once.



Example 1:

Input: s = "anagram", t = "nagaram"
Output: true
Example 2:

Input: s = "rat", t = "car"
Output: false


Constraints:

1 <= s.length, t.length <= 5 * 104
s and t consist of lowercase English letters.
 */
public class ValidAnagram {
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) { return false; }

        int[] firstArray = new int[26];
        int[] secondArray = new int[26];

        for (int i = 0; i < s.length(); i++) {
            int index1 = s.charAt(i) - 'a';
            int count1 = firstArray[index1];
            count1 += 1;
            firstArray[index1] = count1;

            int index2 = t.charAt(i) - 'a';
            int count2 = secondArray[index2];
            count2 += 1;
            secondArray[index2] = count2;
        }

        return Arrays.equals(firstArray, secondArray);
    }
}
