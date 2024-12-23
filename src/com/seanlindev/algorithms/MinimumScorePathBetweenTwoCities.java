package com.seanlindev.algorithms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
You are given a positive integer n representing n cities numbered from 1 to n. You are also given a 2D array roads where roads[i] = [ai, bi, distancei] indicates that there is a bidirectional road between cities ai and bi with a distance equal to distancei. The cities graph is not necessarily connected.

The score of a path between two cities is defined as the minimum distance of a road in this path.

Return the minimum possible score of a path between cities 1 and n.

Note:

A path is a sequence of roads between two cities.
It is allowed for a path to contain the same road multiple times, and you can visit cities 1 and n multiple times along the path.
The test cases are generated such that there is at least one path between 1 and n.


Example 1:


Input: n = 4, roads = [[1,2,9],[2,3,6],[2,4,5],[1,4,7]]
Output: 5
Explanation: The path from city 1 to 4 with the minimum score is: 1 -> 2 -> 4. The score of this path is min(9,5) = 5.
It can be shown that no other path has less score.
Example 2:


Input: n = 4, roads = [[1,2,2],[1,3,4],[3,4,7]]
Output: 2
Explanation: The path from city 1 to 4 with the minimum score is: 1 -> 2 -> 1 -> 3 -> 4. The score of this path is min(2,2,4,7) = 2.


Constraints:

2 <= n <= 105
1 <= roads.length <= 105
roads[i].length == 3
1 <= ai, bi <= n
ai != bi
1 <= distancei <= 104
There are no repeated edges.
There is at least one path between 1 and n.
 */
public class MinimumScorePathBetweenTwoCities {
    int result = Integer.MAX_VALUE;

    public int minScore(int n, int[][] roads) {
        Map<Integer, List<int[]>> graph = new HashMap<>();
        for (int[] road: roads) {
            int node1 = road[0];
            int node2 = road[1];
            int distance = road[2];
            graph.putIfAbsent(node1, new ArrayList<>());
            graph.get(node1).add(new int[]{ node2, distance });
            graph.putIfAbsent(node2, new ArrayList<>());
            graph.get(node2).add(new int[]{ node1, distance });
        }

        boolean[] visited = new boolean[n + 1];
        dfs(1, visited, graph);
        return result;
    }

    void dfs(int node, boolean[] visited, Map<Integer, List<int[]>> graph) {
        if (visited[node]) { return; }

        visited[node] = true;
        List<int[]> adjList = graph.get(node);
        if (adjList != null) {
            for (int[] adj: adjList) {
                result = Math.min(result, adj[1]);
                dfs(adj[0], visited, graph);
            }
        }
    }
}
