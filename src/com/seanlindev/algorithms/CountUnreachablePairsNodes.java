package com.seanlindev.algorithms;

import java.util.HashSet;
import java.util.Set;

/*
You are given an integer n. There is an undirected graph with n nodes, numbered from 0 to n - 1. You are given a 2D integer array edges where edges[i] = [ai, bi] denotes that there exists an undirected edge connecting nodes ai and bi.

Return the number of pairs of different nodes that are unreachable from each other.



Example 1:


Input: n = 3, edges = [[0,1],[0,2],[1,2]]
Output: 0
Explanation: There are no pairs of nodes that are unreachable from each other. Therefore, we return 0.
Example 2:


Input: n = 7, edges = [[0,2],[0,5],[2,4],[1,6],[5,4]]
Output: 14
Explanation: There are 14 pairs of nodes that are unreachable from each other:
[[0,1],[0,3],[0,6],[1,2],[1,3],[1,4],[1,5],[2,3],[2,6],[3,4],[3,5],[3,6],[4,6],[5,6]].
Therefore, we return 14.


Constraints:

1 <= n <= 105
0 <= edges.length <= 2 * 105
edges[i].length == 2
0 <= ai, bi < n
ai != bi
There are no repeated edges.
 */
public class CountUnreachablePairsNodes {
    public long countPairs(int n, int[][] edges) {
        if (n == 1) { return 0; }
        if (edges.length == 0) {
            long result = (long)n * (long)(n - 1) / (long)2;
            return result;
        }

        int[] parents = new int[n];
        int[] ranks = new int[n];
        for (int i = 0; i < n; i++) {
            parents[i] = i;
            ranks[i] = 1;
        }

        for (int[] edge: edges) {
            union(edge[0], edge[1], parents, ranks);
        }

        Set<Integer> parentSet = new HashSet<>();
        long result = 0;
        long remain = n;
        for (int i = 0; i < n; i++) {
            int parent = find(i, parents);
            if (!parentSet.contains(parent)) {
                parentSet.add(parent);
                long size = ranks[parent];
                remain -= size;
                result += size * remain;
            }
        }

        if (parentSet.size() < 2) { return 0; }
        return result;
    }

    int find(int node, int[] parents) {
        int parent = parents[node];
        while (parents[parent] != parent) {
            parents[node] = parents[parent];
            parent = parents[parent];
        }

        return parent;
    }

    void union(int node1, int node2, int[] parents, int[] ranks) {
        int parent1 = find(node1, parents);
        int parent2 = find(node2, parents);

        if (parent1 == parent2) { return; }

        int rank1 = ranks[parent1];
        int rank2 = ranks[parent2];
        if (rank1 < rank2) {
            parents[parent1] = parent2;
            ranks[parent2] += ranks[parent1];
        } else {
            parents[parent2] = parent1;
            ranks[parent1] += ranks[parent2];
        }
    }
}
