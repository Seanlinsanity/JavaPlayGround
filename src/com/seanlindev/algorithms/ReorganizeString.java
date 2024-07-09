package com.seanlindev.algorithms;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/*
Given a string s, rearrange the characters of s so that any two adjacent characters are not the same.

Return any possible rearrangement of s or return "" if not possible.



Example 1:

Input: s = "aab"
Output: "aba"
Example 2:

Input: s = "aaab"
Output: ""


Constraints:

1 <= s.length <= 500
s consists of lowercase English letters.
 */
public class ReorganizeString {
    class Node {
        char val;
        int count;
        Node(char val, int count) {
            this.val = val;
            this.count = count;
        }
    }
    
    public String reorganizeString(String s) {
        Map<Character, Integer> charCountMap = new HashMap<>();
        for (char character: s.toCharArray()) {
            charCountMap.putIfAbsent(character, 0);
            charCountMap.put(character, charCountMap.get(character) + 1);
        }

        PriorityQueue<Node> maxHeap = new PriorityQueue<>((a, b) -> Integer.compare(b.count, a.count));
        for (char character: charCountMap.keySet()) {
            maxHeap.add(new Node(character, charCountMap.get(character)));
        }

        StringBuilder stringBuilder = new StringBuilder();
        Node prevNode = null;
        while (!maxHeap.isEmpty()) {
            Node node = maxHeap.poll();
            stringBuilder.append(node.val);
            if (prevNode != null && prevNode.count > 0) {
                maxHeap.add(prevNode);
            }
            prevNode = new Node(node.val, node.count - 1);
        }

        String result = stringBuilder.toString();
        return result.length() == s.length() ? result : "";
    }
}
