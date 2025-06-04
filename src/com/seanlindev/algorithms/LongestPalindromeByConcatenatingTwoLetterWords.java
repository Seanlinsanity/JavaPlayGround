package com.seanlindev.algorithms;

/*
You are given an array of strings words. Each element of words consists of two lowercase English letters.

Create the longest possible palindrome by selecting some elements from words and concatenating them in any order. Each element can be selected at most once.

Return the length of the longest palindrome that you can create. If it is impossible to create any palindrome, return 0.

A palindrome is a string that reads the same forward and backward.



Example 1:

Input: words = ["lc","cl","gg"]
Output: 6
Explanation: One longest palindrome is "lc" + "gg" + "cl" = "lcggcl", of length 6.
Note that "clgglc" is another longest palindrome that can be created.
Example 2:

Input: words = ["ab","ty","yt","lc","cl","ab"]
Output: 8
Explanation: One longest palindrome is "ty" + "lc" + "cl" + "yt" = "tylcclyt", of length 8.
Note that "lcyttycl" is another longest palindrome that can be created.
Example 3:

Input: words = ["cc","ll","xx"]
Output: 2
Explanation: One longest palindrome is "cc", of length 2.
Note that "ll" is another longest palindrome that can be created, and so is "xx".


Constraints:

1 <= words.length <= 105
words[i].length == 2
words[i] consists of lowercase English letters.

 */
public class LongestPalindromeByConcatenatingTwoLetterWords {
    public int longestPalindrome(String[] words) {
        final int alphabetSize = 26;
        int[][] count = new int[alphabetSize][alphabetSize];
        for (String word : words) {
            count[word.charAt(0) - 'a'][word.charAt(1) - 'a']++;
        }
        int answer = 0;
        boolean central = false;
        for (int i = 0; i < alphabetSize; i++) {
            if (count[i][i] % 2 == 0) {
                answer += count[i][i];
            } else {
                answer += count[i][i] - 1;
                central = true;
            }
            for (int j = i + 1; j < alphabetSize; j++) {
                answer += 2 * Math.min(count[i][j], count[j][i]);
            }
        }
        if (central) {
            answer++;
        }
        return 2 * answer;

        // int pair = 0;
        // Map<String, Integer> strCountMap = new HashMap<>();
        // for (String word: words) {
        //     String reverse = String.valueOf(word.charAt(1)) + String.valueOf(word.charAt(0));
        //     int count = strCountMap.getOrDefault(reverse, 0);
        //     if (count > 0) {
        //         pair += 1;
        //         if (count > 1) {
        //             strCountMap.put(reverse, count - 1);
        //         } else {
        //             strCountMap.remove(reverse);
        //         }
        //     } else {
        //         strCountMap.put(word, strCountMap.getOrDefault(word, 0) + 1);
        //     }
        // }

        // int result = pair * 4;
        // for (String key: strCountMap.keySet()) {
        //     if (key.charAt(0) == key.charAt(1)) {
        //         result += 2;
        //         break;
        //     }
        // }

        // return result;
    }
}
