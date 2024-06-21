package com.seanlindev.algorithms;

/*
In an alien language, surprisingly, they also use English lowercase letters, but possibly in a different order. The order of the alphabet is some permutation of lowercase letters.

Given a sequence of words written in the alien language, and the order of the alphabet, return true if and only if the given words are sorted lexicographically in this alien language.



Example 1:

Input: words = ["hello","leetcode"], order = "hlabcdefgijkmnopqrstuvwxyz"
Output: true
Explanation: As 'h' comes before 'l' in this language, then the sequence is sorted.
Example 2:

Input: words = ["word","world","row"], order = "worldabcefghijkmnpqstuvxyz"
Output: false
Explanation: As 'd' comes after 'l' in this language, then words[0] > words[1], hence the sequence is unsorted.
Example 3:

Input: words = ["apple","app"], order = "abcdefghijklmnopqrstuvwxyz"
Output: false
Explanation: The first three characters "app" match, and the second string is shorter (in size.) According to lexicographical rules "apple" > "app", because 'l' > '∅', where '∅' is defined as the blank character which is less than any other character (More info).


Constraints:

1 <= words.length <= 100
1 <= words[i].length <= 20
order.length == 26
All characters in words[i] and order are English lowercase letters.
 */
public class VerifyingAlienDictionary {
    public boolean isAlienSorted(String[] words, String order) {
        int[] charIndex = new int[order.length()];
        for (int i = 0; i < order.length(); i++) {
            char character = order.charAt(i);
            charIndex[character - 'a'] = i;
        }

        for (int i = 0; i < words.length - 1; i++) {
            String word1 = words[i];
            String word2 = words[i + 1];
            for (int j = 0; j < word1.length(); j++) {
                if (j == word2.length()) {
                    return false;
                }

                int index1 = charIndex[word1.charAt(j) - 'a'];
                int index2 = charIndex[word2.charAt(j) - 'a'];
                if (index1 < index2) {
                    break;
                } else if (index1 > index2) {
                    return false;
                }
            }
        }

        return true;
    }
}
