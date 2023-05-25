package com.seanlindev.algorithms;

import java.util.HashSet;
import java.util.Set;

public class LengthOfLongestSubstring {
    public int lengthOfLongestSubstring(String s) {
        char[] chars = s.toCharArray();
        if (chars.length == 0) { return 0; }
        int i = 0;
        int result = 0;

        Set<Character> charSet = new HashSet<>();
        for (int j = 0; j < chars.length; j++) {
            while (charSet.contains(Character.valueOf(chars[j]))) {
                charSet.remove(Character.valueOf(chars[i]));
                i++;
            }

            charSet.add(Character.valueOf(chars[j]));
            int count = (j - i) + 1;
            result = Math.max(result, count);
        }

        return result;
    }
}
