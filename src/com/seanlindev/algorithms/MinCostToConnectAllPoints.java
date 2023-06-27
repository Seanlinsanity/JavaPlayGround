package com.seanlindev.algorithms;

import java.util.*;

/*
You are given an array points representing integer coordinates of some points on a 2D-plane, where points[i] = [xi, yi].

The cost of connecting two points [xi, yi] and [xj, yj] is the manhattan distance between them: |xi - xj| + |yi - yj|, where |val| denotes the absolute value of val.

Return the minimum cost to make all points connected. All points are connected if there is exactly one simple path between any two points.



Example 1:


Input: points = [[0,0],[2,2],[3,10],[5,2],[7,0]]
Output: 20
Explanation:

We can connect the points as shown above to get the minimum cost of 20.
Notice that there is a unique path between every pair of points.
Example 2:

Input: points = [[3,12],[-2,5],[-4,1]]
Output: 18


Constraints:

1 <= points.length <= 1000
-106 <= xi, yi <= 106
All pairs (xi, yi) are distinct.
 */

//Prim's algorithm => minimum spanning tree
public class MinCostToConnectAllPoints {
    public class Node {
        public int[] point;
        public int cost;

        public Node(int[] point, int cost) {
            this.point = point;
            this.cost = cost;
        }
    }

    public int minCostConnectPoints(int[][] points) {
        //edge case
        if (points.length == 1) { return 0; }

        //build the graph
        Map<int[], List<int[]>> graph = buildGraph(points);

        //Prim's algorithm
        int cost = 0;
        Set<int[]> visited = new HashSet<>();
        PriorityQueue<Node> heap = new PriorityQueue<>((a, b) -> Integer.compare(a.cost, b.cost));

        Node root = new Node(points[0], 0);
        heap.add(root);

        while (visited.size() < points.length) {
            Node minNode = heap.poll();
            if (visited.contains(minNode.point)) {
                continue;
            }

            visited.add(minNode.point);
            cost += minNode.cost;

            List<int[]> adjList = graph.get(minNode.point);
            for (int[] next: adjList) {
                Node nextNode = new Node(
                        next,
                        Math.abs(next[0] - minNode.point[0]) + Math.abs(next[1] - minNode.point[1])
                );
                heap.add(nextNode);
            }
        }

        return cost;
    }

    private Map<int[], List<int[]>> buildGraph(int[][] points) {
        Map<int[], List<int[]>> graph = new HashMap<>();
        for (int i = 0; i < points.length; i++) {
            List<int[]> list = new ArrayList<>();
            for (int j = 0; j < points.length; j++) {
                if (i != j) {
                    list.add(points[j]);
                }
            }
            graph.put(points[i], list);
        }
        return graph;
    }
}
