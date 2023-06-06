package com.seanlindev.algorithms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupAnagrams {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String str: strs) {
            String key = generateAnagramKey(str);
            List<String> list = map.get(key);
            if (list == null) {
                list = new ArrayList<String>();
            }
            list.add(str);
            map.put(key, list);
        }

        List<List<String>> result = new ArrayList<>();
        result.addAll(map.values());
        return result;
    }

    public String generateAnagramKey(String str) {
        int[] characterCounts = new int[26];
        for (char character: str.toCharArray()) {
            int count = characterCounts[character - 'a'];
            count += 1;
            characterCounts[character - 'a'] = count;
        }

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < characterCounts.length; i++) {
            if (characterCounts[i] == 0) { continue; }
            int aChar = 'a';
            char currentChar = (char)(aChar + i);
            builder.append(String.valueOf(characterCounts[i]));
            builder.append(String.valueOf(currentChar));
        }
        return builder.toString();
    }

}
