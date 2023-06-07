package com.seanlindev.algorithms;

/*
Given two strings s1 and s2, return true if s2 contains a permutation of s1, or false otherwise.

In other words, return true if one of s1's permutations is the substring of s2.



Example 1:

Input: s1 = "ab", s2 = "eidbaooo"
Output: true
Explanation: s2 contains one permutation of s1 ("ba").
Example 2:

Input: s1 = "ab", s2 = "eidboaoo"
Output: false


Constraints:

1 <= s1.length, s2.length <= 104
s1 and s2 consist of lowercase English letters.
 */
public class PermutationInString {
    public boolean checkInclusion(String s1, String s2) {
        if (s2.length() < s1.length()) return false;

        int[] arr = new int[26];
        int[] arr2 = new int[26];
        for (int i = 0; i < s1.length(); i++) {
            arr[s1.charAt(i) - 'a']++;
            arr2[s2.charAt(i) - 'a']++;
        }

        int matches = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == arr2[i]) {
                matches += 1;
            }
        }

        int left = 0;
        int right = s1.length();
        while (right < s2.length()) {
            if (matches == 26) { return true; }

            int leftCharIndex = s2.charAt(left) - 'a';
            if (arr2[leftCharIndex] == arr[leftCharIndex]) {
                matches -= 1;
            }
            arr2[leftCharIndex] -= 1;
            if (arr2[leftCharIndex] == arr[leftCharIndex]) {
                matches += 1;
            }

            int rightCharIndex = s2.charAt(right) - 'a';
            if (arr2[rightCharIndex] == arr[rightCharIndex]) {
                matches -= 1;
            }
            arr2[rightCharIndex] += 1;
            if (arr2[rightCharIndex] == arr[rightCharIndex]) {
                matches += 1;
            }

            left += 1;
            right += 1;
        }

        return matches == 26;
    }
}
