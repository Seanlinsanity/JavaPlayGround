package com.seanlindev.algorithms;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
We want to split a group of n people (labeled from 1 to n) into two groups of any size. Each person may dislike some other people, and they should not go into the same group.

Given the integer n and the array dislikes where dislikes[i] = [ai, bi] indicates that the person labeled ai does not like the person labeled bi, return true if it is possible to split everyone into two groups in this way.



Example 1:

Input: n = 4, dislikes = [[1,2],[1,3],[2,4]]
Output: true
Explanation: The first group has [1,4], and the second group has [2,3].
Example 2:

Input: n = 3, dislikes = [[1,2],[1,3],[2,3]]
Output: false
Explanation: We need at least 3 groups to divide them. We cannot put them in two groups.


Constraints:

1 <= n <= 2000
0 <= dislikes.length <= 104
dislikes[i].length == 2
1 <= ai < bi <= n
All the pairs of dislikes are unique.
 */
public class PossibleBipartition {
    public boolean possibleBipartition(int n, int[][] dislikes) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] dislike: dislikes) {
            int a = dislike[0];
            int b = dislike[1];
            graph.get(a).add(b);
            graph.get(b).add(a);
        }

        int[] nodes = new int[n + 1]; //0: unvisited, 1, -1
        for (int i = 1; i <= n; i++) {
            if (nodes[i] != 0) { continue; }

            if (!bfs(i, nodes, graph)) {
                return false;
            }
        }

        return true;
    }

    boolean bfs(int node, int[] nodes, List<List<Integer>> graph) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(node);
        nodes[node] = 1;
        while (queue.size() > 0) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int currentNode = queue.remove();
                List<Integer> neighbors = graph.get(currentNode);
                for (int neighbor: neighbors) {
                    if (nodes[neighbor] == 0) {
                        nodes[neighbor] = nodes[currentNode] * -1;
                        queue.add(neighbor);
                    } else if (nodes[neighbor] == nodes[currentNode]) {
                        return false;
                    }
                }
            }
        }

        return true;
    }
}
