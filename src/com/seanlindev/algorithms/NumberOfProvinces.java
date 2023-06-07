package com.seanlindev.algorithms;

/*
There are n cities. Some of them are connected, while some are not. If city a is connected directly with city b, and city b is connected directly with city c, then city a is connected indirectly with city c.

A province is a group of directly or indirectly connected cities and no other cities outside of the group.

You are given an n x n matrix isConnected where isConnected[i][j] = 1 if the ith city and the jth city are directly connected, and isConnected[i][j] = 0 otherwise.

Return the total number of provinces.



Example 1:


Input: isConnected = [[1,1,0],[1,1,0],[0,0,1]]
Output: 2
Example 2:


Input: isConnected = [[1,0,0],[0,1,0],[0,0,1]]
Output: 3


Constraints:

1 <= n <= 200
n == isConnected.length
n == isConnected[i].length
isConnected[i][j] is 1 or 0.
isConnected[i][i] == 1
isConnected[i][j] == isConnected[j][i]
 */
public class NumberOfProvinces {
    public int findConnectedCitiesNum(int[][] isConnected) {
        int numCount = isConnected.length;
        int[] parent = new int[numCount];
        int[] rank = new int[numCount];

        for (int i = 0; i < numCount; i++) {
            parent[i] = i;
            rank[i] = 1;
        }

        int result = numCount;
        for (int i = 0; i < numCount; i++) {
            for (int j = 0; j < numCount; j++) {
                if (i == j || isConnected[i][j] == 0) { continue; }

                result -= union(i, j, parent, rank);
            }
        }
        return result;
    }

    private int find(int node, int[] parent) {
        int result = parent[node];
        while (result != parent[result]) {
            parent[node] = parent[result];
            result = parent[result];
        }

        return result;
    }

    private int union(int n1, int n2, int[] parent, int[] rank) {
        int p1 = find(n1, parent);
        int p2 = find(n2, parent);

        if (p1 == p2) {
            return 0;
        }

        if (rank[p2] > rank[p1]) {
            parent[p1] = p2;
            rank[p2] += rank[p1];
        } else {
            parent[p2] = p1;
            rank[p1] += rank[p2];
        }

        return 1;
    }
}
