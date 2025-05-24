package com.seanlindev.algorithms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
Perform the following shift operations on a string:

Right shift: Replace every letter with the successive letter of the English alphabet, where 'z' is replaced by 'a'. For example, "abc" can be right-shifted to "bcd" or "xyz" can be right-shifted to "yza".
Left shift: Replace every letter with the preceding letter of the English alphabet, where 'a' is replaced by 'z'. For example, "bcd" can be left-shifted to "abc" or "yza" can be left-shifted to "xyz".
We can keep shifting the string in both directions to form an endless shifting sequence.

For example, shift "abc" to form the sequence: ... <-> "abc" <-> "bcd" <-> ... <-> "xyz" <-> "yza" <-> .... <-> "zab" <-> "abc" <-> ...
You are given an array of strings strings, group together all strings[i] that belong to the same shifting sequence. You may return the answer in any order.



Example 1:

Input: strings = ["abc","bcd","acef","xyz","az","ba","a","z"]

Output: [["acef"],["a","z"],["abc","bcd","xyz"],["az","ba"]]

Example 2:

Input: strings = ["a"]

Output: [["a"]]



Constraints:

1 <= strings.length <= 200
1 <= strings[i].length <= 50
strings[i] consists of lowercase English letters.
 */
public class GroupShiftedStrings {
    public List<List<String>> groupStrings(String[] strings) {
        Map<String, List<String>> groupMap = new HashMap<>();
        for (String str: strings) {
            if (str.length() == 1) {
                groupMap.putIfAbsent("1", new ArrayList<>());
                groupMap.get("1").add(str);
            } else {
                StringBuilder builder = new StringBuilder();
                for (int i = 0; i < str.length() - 1; i++) {
                    int diff = str.charAt(i + 1) - str.charAt(i);
                    if (diff < 0) { diff += 26; }
                    builder.append(String.valueOf(diff) + ",");
                }
                String group = builder.toString();
                groupMap.putIfAbsent(group, new ArrayList<>());
                groupMap.get(group).add(str);
            }
        }

        return new ArrayList<>(groupMap.values());
    }
}
