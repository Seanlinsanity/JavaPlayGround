package com.seanlindev.algorithms;

import java.util.*;

/*
On a 2D plane, we place n stones at some integer coordinate points. Each coordinate point may have at most one stone.

A stone can be removed if it shares either the same row or the same column as another stone that has not been removed.

Given an array stones of length n where stones[i] = [xi, yi] represents the location of the ith stone, return the largest possible number of stones that can be removed.



Example 1:

Input: stones = [[0,0],[0,1],[1,0],[1,2],[2,1],[2,2]]
Output: 5
Explanation: One way to remove 5 stones is as follows:
1. Remove stone [2,2] because it shares the same row as [2,1].
2. Remove stone [2,1] because it shares the same column as [0,1].
3. Remove stone [1,2] because it shares the same row as [1,0].
4. Remove stone [1,0] because it shares the same column as [0,0].
5. Remove stone [0,1] because it shares the same row as [0,0].
Stone [0,0] cannot be removed since it does not share a row/column with another stone still on the plane.
Example 2:

Input: stones = [[0,0],[0,2],[1,1],[2,0],[2,2]]
Output: 3
Explanation: One way to make 3 moves is as follows:
1. Remove stone [2,2] because it shares the same row as [2,0].
2. Remove stone [2,0] because it shares the same column as [0,0].
3. Remove stone [0,2] because it shares the same row as [0,0].
Stones [0,0] and [1,1] cannot be removed since they do not share a row/column with another stone still on the plane.
Example 3:

Input: stones = [[0,0]]
Output: 0
Explanation: [0,0] is the only stone on the plane, so you cannot remove it.


Constraints:

1 <= stones.length <= 1000
0 <= xi, yi <= 104
No two stones are at the same coordinate point.
 */
public class MostStonesRemovedSameRowOrColumn {
    public int removeStones(int[][] stones) {
        // edge case
        if (stones.length == 1) { return 0; }

        Map<String, List<Integer>> connectionMap = new HashMap<>();
        for (int i = 0; i < stones.length; i++) {
            int[] stone = stones[i];
            int x = stone[0];
            connectionMap.putIfAbsent("x" + x, new ArrayList<>());
            connectionMap.get("x" + x).add(i);
            int y = stone[1];
            connectionMap.putIfAbsent("y" + y, new ArrayList<>());
            connectionMap.get("y" + y).add(i);
        }

        List<int[]> edges = new ArrayList<>();
        for (List<Integer> connection: connectionMap.values()) {
            for (int i = 0; i < connection.size() - 1; i++) {
                edges.add(new int[]{ connection.get(i), connection.get(i + 1)});
            }
        }

        //union-find
        int[] parents = new int[stones.length];
        int[] ranks = new int[stones.length];
        for (int i = 0; i < stones.length; i++) {
            parents[i] = i;
        }
        Arrays.fill(ranks, 1);

        for (int[] edge: edges) {
            union(parents, ranks, edge[0], edge[1]);
        }

        int remove = 0;
        Set<Integer> parentSet = new HashSet<>();
        for (int i = 0; i < stones.length; i++) {
            int parent = find(parents, i);
            if (parentSet.contains(parent)) { continue; }

            parentSet.add(parent);
            remove += (ranks[parent] - 1);
        }

        return remove;
    }

    int find(int[] parents, int i) {
        int parent = parents[i];
        while (parents[parent] != parent) {
            parents[i] = parents[parent];
            parent = parents[parent];
        }

        return parent;
    }

    void union(int[] parents, int[] ranks, int i, int j) {
        int parent1 = find(parents, i);
        int parent2 = find(parents, j);

        if (parent1 == parent2) { return; }

        int rank1 = ranks[parent1];
        int rank2 = ranks[parent2];
        if (rank1 > rank2) {
            parents[parent2] = parent1;
            ranks[parent1] += ranks[parent2];
        } else {
            parents[parent1] = parent2;
            ranks[parent2] += ranks[parent1];
        }
    }
}
