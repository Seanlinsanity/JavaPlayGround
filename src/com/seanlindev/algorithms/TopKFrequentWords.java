package com.seanlindev.algorithms;

import java.util.*;

/*
Given an array of strings words and an integer k, return the k most frequent strings.

Return the answer sorted by the frequency from highest to lowest. Sort the words with the same frequency by their lexicographical order.



Example 1:

Input: words = ["i","love","leetcode","i","love","coding"], k = 2
Output: ["i","love"]
Explanation: "i" and "love" are the two most frequent words.
Note that "i" comes before "love" due to a lower alphabetical order.
Example 2:

Input: words = ["the","day","is","sunny","the","the","the","sunny","is","is"], k = 4
Output: ["the","is","sunny","day"]
Explanation: "the", "is", "sunny" and "day" are the four most frequent words, with the number of occurrence being 4, 3, 2 and 1 respectively.


Constraints:

1 <= words.length <= 500
1 <= words[i].length <= 10
words[i] consists of lowercase English letters.
k is in the range [1, The number of unique words[i]]


Follow-up: Could you solve it in O(n log(k)) time and O(n) extra space?
 */
public class TopKFrequentWords {
    class Node {
        int count;
        String word;

        Node(String word, int count) {
            this.count = count;
            this.word = word;
        }
    }

    public List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> wordCountMap = new HashMap<>();
        for (String word: words) {
            wordCountMap.put(word, wordCountMap.getOrDefault(word, 0) + 1);
        }

        PriorityQueue<Node> minHeap = new PriorityQueue<>((a, b) -> {
            if (a.count == b.count) {
                return b.word.compareTo(a.word);
            }

            return Integer.compare(a.count, b.count);
        });

        for (String word: wordCountMap.keySet()) {
            int count = wordCountMap.get(word);
            if (minHeap.size() < k) {
                minHeap.add(new Node(word, count));
            } else {
                if (minHeap.peek().count < count || (minHeap.peek().count == count && word.compareTo(minHeap.peek().word) < 0)) {
                    minHeap.poll();
                    minHeap.add(new Node(word, count));
                }
            }
        }

        List<String> result = new ArrayList<>();
        while (minHeap.size() > 0) {
            result.add(minHeap.poll().word);
        }

        List<String> reversed = new ArrayList<>();
        for (int i = result.size() - 1; i >= 0; i--) {
            reversed.add(result.get(i));
        }

        return reversed;
    }
}
