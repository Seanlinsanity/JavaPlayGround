package com.seanlindev.algorithms;

/*
In this problem, a tree is an undirected graph that is connected and has no cycles.

You are given a graph that started as a tree with n nodes labeled from 1 to n, with one additional edge added. The added edge has two different vertices chosen from 1 to n, and was not an edge that already existed. The graph is represented as an array edges of length n where edges[i] = [ai, bi] indicates that there is an edge between nodes ai and bi in the graph.

Return an edge that can be removed so that the resulting graph is a tree of n nodes. If there are multiple answers, return the answer that occurs last in the input.



Example 1:


Input: edges = [[1,2],[1,3],[2,3]]
Output: [2,3]
Example 2:


Input: edges = [[1,2],[2,3],[3,4],[1,4],[1,5]]
Output: [1,4]


Constraints:

n == edges.length
3 <= n <= 1000
edges[i].length == 2
1 <= ai < bi <= edges.length
ai != bi
There are no repeated edges.
The given graph is connected.
 */
public class RedundantConnection {
    //Union-Find Algorithm

    int[] parentArray;
    //[[1,2],[2,3],[3,4],[1,4],[1,5]]
    public int[] findRedundantConnection(int[][] edges) {
        parentArray = new int[edges.length]; //[0,0,0,0,0]
        for (int i = 0; i < edges.length; i++) parentArray[i] = i + 1; //[1, 2, 3, 4, 5]

        for (int[] edge : edges) {
            boolean success = union(edge[0], edge[1]);
            if (!success) { return edge; }
        }

        return new int[2];
    }
    //1 -> 1
    //2 -> 2
    //2 -> 1
    //3 -> 3
    //3 -> 1
    //4 -> 4
    //1 -> 1
    //4 -> 1
    public int find(int x) {
        int parent = parentArray[x - 1];
        while(parent != parentArray[parent - 1]) {
            parent = parentArray[parent - 1];
        }
        return parent;
    }
    //1, 2 -> [1, 1, 3, 4, 5]
    //2, 3 -> [1, 1, 1, 4, 5]
    //3, 4 -> [1, 1, 1, 1, 5]
    //1, 4 ->
    public boolean union(int x, int y) {
        int parent1 = find(x); //1
        int parent2 = find(y); //3
        if (parent1 == parent2) { return false; }
        parentArray[parent2 - 1] = parent1;
        return true;
    }
}
