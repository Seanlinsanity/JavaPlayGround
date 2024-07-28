package com.seanlindev.algorithms;

/*
Given a string s, partition the string into one or more substrings such that the characters in each substring are unique. That is, no letter appears in a single substring more than once.

Return the minimum number of substrings in such a partition.

Note that each character should belong to exactly one substring in a partition.



Example 1:

Input: s = "abacaba"
Output: 4
Explanation:
Two possible partitions are ("a","ba","cab","a") and ("ab","a","ca","ba").
It can be shown that 4 is the minimum number of substrings needed.
Example 2:

Input: s = "ssssss"
Output: 6
Explanation:
The only valid partition is ("s","s","s","s","s","s").


Constraints:

1 <= s.length <= 105
s consists of only English lowercase letters.
 */
public class OptimalPartitionOfString {
    public int partitionString(String s) {
        boolean[] charsExisted = new boolean[26];
        int count = 0;
        for (int index = 0; index < s.length(); index++) {
            if (index == 0) {
                charsExisted[s.charAt(index) - 'a'] = true;
                count = 1;
                continue;
            }

            if (charsExisted[s.charAt(index) - 'a']) {
                charsExisted = new boolean[26];
                count += 1;
            }
            charsExisted[s.charAt(index) - 'a'] = true;
        }

        return count;
    }
}
