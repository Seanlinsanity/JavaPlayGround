package com.seanlindev.algorithms;

import java.util.*;

/*
Description
There is a new alien language which uses the latin alphabet. However, the order among letters are unknown to you. You receive a list of non-empty words from the dictionary, where words are sorted lexicographically by the rules of this new language. Derive the order of letters in this language.

You may assume all letters are in lowercase.
The dictionary is invalid, if string a is prefix of string b and b is appear before a.
If the order is invalid, return an empty string.
There may be multiple valid order of letters, return the smallest in normal lexicographical order.
The letters in one string are of the same rank by default and are sorted in Human dictionary order.
Example
Example 1:

Input：["wrt","wrf","er","ett","rftt"]
Output："wertf"
Explanation：
from "wrt"and"wrf" ,we can get 't'<'f'
from "wrt"and"er" ,we can get 'w'<'e'
from "er"and"ett" ,we can get 'r'<'t'
from "ett"and"rftt" ,we can get 'e'<'r'
So return "wertf"
Example 2:

Input：["z","x"]
Output："zx"
Explanation：
from "z" and "x"，we can get 'z' < 'x'
So return "zx"
 */

public class AlienDictionary {
    public String alienOrder(String[] words) {
        // Build graph
        Map<Character, Set<Character>> graph = new HashMap<>();
        for (int i = 0; i < words.length - 1; i++) {
            String word1 = words[i];
            String word2 = words[i + 1];
            int shortLength = Math.min(word1.length(), word2.length());
            int index = 0;
            while(index < shortLength) {
                if (word1.charAt(index) == word2.charAt(index)) {
                    graph.putIfAbsent(word1.charAt(index), new HashSet<>());
                    index += 1;
                } else {
                    graph.putIfAbsent(word1.charAt(index), new HashSet<>());
                    graph.get(word1.charAt(index)).add(word2.charAt(index));
                    break;
                }
            }

            if (index == shortLength && word1.length() > word2.length()) {
                return "";
            }
        }

        //DFS
        List<Character> path = new ArrayList<>();
        Set<Character> cycle = new HashSet<>();
        Set<Character> visited = new HashSet<>();
        for (Character key: graph.keySet()) {
            boolean success = dfs(graph, key, path, visited, cycle);
            if (!success) {
                return "";
            }
        }

        //Build string by reservse path
        StringBuilder builder = new StringBuilder();
        for (int i = path.size() - 1; i >= 0; i--) {
            builder.append(path.get(i));
        }

        return builder.toString();
    }

    public boolean dfs(Map<Character, Set<Character>> graph, Character start, List<Character> path, Set<Character> visited, Set<Character> cycle) {
        if (cycle.contains(start)) { return false; }
        if (visited.contains(start)) { return true; }

        visited.add(start);
        cycle.add(start);

        Set<Character> next = graph.get(start);
        if (next != null) {
            for (Character node: next) {
                boolean success = dfs(graph, node, path, visited, cycle);
                if (!success) { return false; }
            }
        }

        cycle.remove(start);
        path.add(start);
        return true;
    }
}
