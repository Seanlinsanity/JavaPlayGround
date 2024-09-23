package com.seanlindev.algorithms;

import java.util.*;

/*
You are given an integer n, the number of nodes in a directed graph where the nodes are labeled from 0 to n - 1. Each edge is red or blue in this graph, and there could be self-edges and parallel edges.

You are given two arrays redEdges and blueEdges where:

redEdges[i] = [ai, bi] indicates that there is a directed red edge from node ai to node bi in the graph, and
blueEdges[j] = [uj, vj] indicates that there is a directed blue edge from node uj to node vj in the graph.
Return an array answer of length n, where each answer[x] is the length of the shortest path from node 0 to node x such that the edge colors alternate along the path, or -1 if such a path does not exist.



Example 1:

Input: n = 3, redEdges = [[0,1],[1,2]], blueEdges = []
Output: [0,1,-1]
Example 2:

Input: n = 3, redEdges = [[0,1]], blueEdges = [[2,1]]
Output: [0,1,-1]


Constraints:

1 <= n <= 100
0 <= redEdges.length, blueEdges.length <= 400
redEdges[i].length == blueEdges[j].length == 2
0 <= ai, bi, uj, vj < n
 */

public class ShortestPathWithAlternatingColors {
    public int[] shortestAlternatingPaths(int n, int[][] redEdges, int[][] blueEdges) {
        if (n == 1) { return new int[]{0}; }

        // build graph
        Map<Integer, List<Integer>> redGraph = buildGraph(redEdges);
        Map<Integer, List<Integer>> blueGraph = buildGraph(blueEdges);

        int[] result = new int[n];
        Arrays.fill(result, -1);

        // bfs
        bfs(n, true, result, redGraph, blueGraph);
        bfs(n, false, result, redGraph, blueGraph);

        return result;
    }

    Map<Integer, List<Integer>> buildGraph(int[][] edges) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int[] edge: edges) {
            int from = edge[0];
            int to = edge[1];
            graph.putIfAbsent(from, new ArrayList<>());
            graph.get(from).add(to);
        }
        return graph;
    }

    void bfs(int n, boolean isRed, int[] result, Map<Integer, List<Integer>> redGraph, Map<Integer, List<Integer>> blueGraph) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        boolean[] rVisited = new boolean[n];
        boolean[] bVisited = new boolean[n];
        if (isRed) {
            rVisited[0] = true;
        } else {
            bVisited[0] = true;
        }
        int step = 0;
        while (queue.size() > 0) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int node = queue.remove();
                if (result[node] == -1) {
                    result[node] = step;
                } else {
                    result[node] = Math.min(result[node], step);
                }

                List<Integer> neighbors = isRed ? redGraph.get(node) : blueGraph.get(node);
                if (neighbors != null) {
                    for (int neighbor: neighbors) {
                        if (isRed && bVisited[neighbor]) { continue; }
                        if (!isRed && rVisited[neighbor]) { continue; }

                        if (isRed) {
                            bVisited[neighbor] = true;
                        } else {
                            rVisited[neighbor] = true;
                        }

                        queue.add(neighbor);
                    }
                }
            }

            step += 1;
            isRed = !isRed;
        }
    }
}
