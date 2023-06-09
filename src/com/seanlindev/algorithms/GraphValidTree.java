package com.seanlindev.algorithms;

import java.util.*;

/*
Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes), write a function to check whether these edges make up a valid tree.

You can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0, 1] is the same as [1, 0] and thus will not appear together in edges.

Example
Example 1:

Input: n = 5 edges = [[0, 1], [0, 2], [0, 3], [1, 4]]
Output: true.
Example 2:

Input: n = 5 edges = [[0, 1], [1, 2], [2, 3], [1, 3], [1, 4]]
Output: false.
 */
public class GraphValidTree {
    private Map<Integer, List<Integer>> adjacencyList = new HashMap<>();

    public boolean validTree(int n, int[][] edges) {
        if (n == 0 || n == 1) return true;

        if (edges.length == 0) return false;

        for (var edge : edges) {
            var node1 = edge[0];
            var node2 = edge[1];
            adjacencyList.putIfAbsent(node1, new ArrayList<>());
            adjacencyList.putIfAbsent(node2, new ArrayList<>());
            adjacencyList.get(node1).add(node2);
            adjacencyList.get(node2).add(node1);
        }

        Set<Integer> visited = new HashSet<>();

        return (
                depthFirstSearch(edges[0][0], -1, visited) && visited.size() == n
        );
    }

    private boolean depthFirstSearch(
            int node,
            int previous,
            Set<Integer> visited
    ) {
        if (visited.contains(node)) return false;

        visited.add(node);

        for (var neighbor : adjacencyList.get(node)) {
            if (neighbor == previous) continue;

            if (!depthFirstSearch(neighbor, node, visited)) return false;
        }

        return true;
    }

     public boolean validTree1(int n, int[][] edges) {
         // write your code here
         if (edges.length != n - 1) { return false; }

         int[] parent = new int[n];
         int[] rank = new int[n];
         for (int i = 0; i < parent.length; i++) {
             parent[i] = i;
             rank[i] = 1;
         }

         for (int[] edge: edges) {
             boolean isConnected = union(edge[0], edge[1], parent, rank);
             if (isConnected) {
             return false;
             }
         }

         return true;
     }

     public int find(int node, int[] parent) {
         int par = parent[node];
         while (par != parent[par]) {
             parent[node] = parent[par];
             par = parent[par];
         }
         return par;
     }

     public boolean union(int node1, int node2, int[] parent, int[] rank) {
         int par1 = find(node1, parent);
         int par2 = find(node2, parent);
         if (par1 == par2) { return true; }

         int rank1 = rank[par1];
         int rank2 = rank[par2];
         if (rank1 >= rank2) {
             parent[node2] = node1;
             rank[node1] = rank1 + rank2;
         } else {
             parent[node1] = node2;
             rank[node2] = rank1 + rank2;
         }

         return false;
     }
}
