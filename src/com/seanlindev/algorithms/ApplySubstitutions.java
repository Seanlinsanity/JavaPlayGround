package com.seanlindev.algorithms;

import java.util.*;

/*
You are given a replacements mapping and a text string that may contain placeholders formatted as %var%, where each var corresponds to a key in the replacements mapping. Each replacement value may itself contain one or more such placeholders. Each placeholder is replaced by the value associated with its corresponding replacement key.

Return the fully substituted text string which does not contain any placeholders.



Example 1:

Input: replacements = [["A","abc"],["B","def"]], text = "%A%_%B%"

Output: "abc_def"

Explanation:

The mapping associates "A" with "abc" and "B" with "def".
Replace %A% with "abc" and %B% with "def" in the text.
The final text becomes "abc_def".
Example 2:

Input: replacements = [["A","bce"],["B","ace"],["C","abc%B%"]], text = "%A%_%B%_%C%"

Output: "bce_ace_abcace"

Explanation:

The mapping associates "A" with "bce", "B" with "ace", and "C" with "abc%B%".
Replace %A% with "bce" and %B% with "ace" in the text.
Then, for %C%, substitute %B% in "abc%B%" with "ace" to obtain "abcace".
The final text becomes "bce_ace_abcace".


Constraints:

1 <= replacements.length <= 10
Each element of replacements is a two-element list [key, value], where:
key is a single uppercase English letter.
value is a non-empty string of at most 8 characters that may contain zero or more placeholders formatted as %<key>%.
All replacement keys are unique.
The text string is formed by concatenating all key placeholders (formatted as %<key>%) randomly from the replacements mapping, separated by underscores.
text.length == 4 * replacements.length - 1
Every placeholder in the text or in any replacement value corresponds to a key in the replacements mapping.
There are no cyclic dependencies between replacement keys.
 */

public class ApplySubstitutions {
    public String applySubstitutions(List<List<String>> replacements, String text) {
        Map<String, String> replaceMapping = new HashMap<>();
        Map<String, List<String>> graph = new HashMap<>();
        Map<String, Integer> inDegree = new HashMap<>();
        // Build Graph O(N * M)
        for (List<String> replacement: replacements) {
            String key = replacement.get(0);
            String value = replacement.get(1);
            int i = 0;
            Set<String> dependentKeySet = new HashSet<>();
            while (i < value.length()) {
                if (value.charAt(i) == '%') {
                    for (int j = i + 1; j < value.length(); j++) {
                        if (value.charAt(j) == '%') {
                            String dependentKey =  value.substring(i + 1, j);
                            dependentKeySet.add(dependentKey);
                            i = j;
                            break;
                        }
                    }
                    i += 1;
                } else {
                    i += 1;
                }
            }
            inDegree.put(key, inDegree.getOrDefault(key, 0) + dependentKeySet.size());
            for (String dependentKey: dependentKeySet) {
                graph.putIfAbsent(dependentKey, new ArrayList<>());
                graph.get(dependentKey).add(key);
            }
            replaceMapping.put(key, value);
        }

        // Topological Sort O((N^2)*M)
        Queue<String> queue = new LinkedList<>();
        for (List<String> replacement: replacements) {
            String key = replacement.get(0);
            if (inDegree.get(key) == 0) {
                queue.add(key);
            }
        }
        Map<String, String> mapping = new HashMap<>();
        while (queue.size() > 0) {
            int size = queue.size();
            for (int s = 0; s < size; s++) {
                String key = queue.remove();
                String value = replaceMapping.get(key);
                int i = 0;
                StringBuilder builder = new StringBuilder();
                while (i < value.length()) {
                    if (value.charAt(i) == '%') {
                        boolean found = false;
                        for (int j = i + 1; j < value.length(); j++) {
                            if (value.charAt(j) == '%') {
                                String dependentKey =  value.substring(i + 1, j);
                                builder.append(mapping.get(dependentKey));
                                i = j + 1;
                                found = true;
                                break;
                            }
                        }
                        if (!found) {
                            builder.append(text.charAt(i));
                            i += 1;
                        }
                    } else {
                        builder.append(value.charAt(i));
                        i += 1;
                    }
                }
                mapping.put(key, builder.toString());

                List<String> next = graph.get(key);
                if (next == null) { continue; }
                for (String nextKey: next) {
                    int count = inDegree.get(nextKey);
                    if (count == 1) {
                        inDegree.remove(nextKey);
                        queue.add(nextKey);
                    } else {
                        inDegree.put(nextKey, count - 1);
                    }
                }
            }
        }

        // Final replacement O(T*M)
        int i = 0;
        StringBuilder builder = new StringBuilder();
        while (i < text.length()) {
            if (text.charAt(i) == '%') {
                boolean found = false;
                for (int j = i + 1; j < text.length(); j++) {
                    if (text.charAt(j) == '%') {
                        String key = text.substring(i + 1, j);
                        builder.append(mapping.get(key));
                        i = j + 1;
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    builder.append(text.charAt(i));
                    i += 1;
                }
            } else {
                builder.append(text.charAt(i));
                i += 1;
            }
        }
        return builder.toString();

    }
}
