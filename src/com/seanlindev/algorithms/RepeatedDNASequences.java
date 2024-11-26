package com.seanlindev.algorithms;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
The DNA sequence is composed of a series of nucleotides abbreviated as 'A', 'C', 'G', and 'T'.

For example, "ACGAATTCCG" is a DNA sequence.
When studying DNA, it is useful to identify repeated sequences within the DNA.

Given a string s that represents a DNA sequence, return all the 10-letter-long sequences (substrings) that occur more than once in a DNA molecule. You may return the answer in any order.



Example 1:

Input: s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"
Output: ["AAAAACCCCC","CCCCCAAAAA"]
Example 2:

Input: s = "AAAAAAAAAAAAA"
Output: ["AAAAAAAAAA"]


Constraints:

1 <= s.length <= 105
s[i] is either 'A', 'C', 'G', or 'T'.
 */
public class RepeatedDNASequences {
    public List<String> findRepeatedDnaSequences(String s) {
        int left = 0;
        int right = left + 9;
        Set<String> resultSet = new HashSet<>();
        Set<String> sequenceSet = new HashSet<>();
        while (left + 9 < s.length()) {
            String current = s.substring(left, left + 10);
            if (sequenceSet.contains(current)) {
                resultSet.add(current);
            } else {
                sequenceSet.add(current);
            }

            left += 1;
        }

        return new ArrayList<>(resultSet);
    }
}
