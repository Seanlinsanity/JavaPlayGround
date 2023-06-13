package com.seanlindev.algorithms;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/*
You are given a network of n nodes, labeled from 1 to n. You are also given times, a list of travel times as directed edges times[i] = (ui, vi, wi), where ui is the source node, vi is the target node, and wi is the time it takes for a signal to travel from source to target.

We will send a signal from a given node k. Return the minimum time it takes for all the n nodes to receive the signal. If it is impossible for all the n nodes to receive the signal, return -1.



Example 1:


Input: times = [[2,1,1],[2,3,1],[3,4,1]], n = 4, k = 2
Output: 2
Example 2:

Input: times = [[1,2,1]], n = 2, k = 1
Output: 1
Example 3:

Input: times = [[1,2,1]], n = 2, k = 2
Output: -1


Constraints:

1 <= k <= n <= 100
1 <= times.length <= 6000
times[i].length == 3
1 <= ui, vi <= n
ui != vi
0 <= wi <= 100
All the pairs (ui, vi) are unique. (i.e., no multiple edges.)
 */
public class NetworkDelayTime {
    public int networkDelayTime(int[][] times, int n, int k) {
        //Build the graph
        List<List<int[]>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            List adj = new ArrayList<>();
            graph.add(adj);
        }
        for (int[] time: times) {
            List<int[]> adj = graph.get(time[0]);
            adj.add(new int[] {time[1], time[2]});
        }

        //Dijkstra
        return dijkstra(n, k, graph);
    }

    public int dijkstra(int n, int source, List<List<int[]>> graph) {
        int result = 0;
        int count = 0;
        boolean[] visited = new boolean[n + 1];
        PriorityQueue<State> minHeap = new PriorityQueue<State>((a , b) -> a.time - b.time);

        minHeap.add(new State(source, 0));
        while (minHeap.size() > 0) {
            State current = minHeap.poll();
            int node = current.node;
            if (visited[node])  {
                continue;
            }

            visited[node] = true;
            count += 1;
            int currentTime = current.time;
            result = Math.max(result, currentTime);
            List<int[]> adj = graph.get(node);

            if (adj.size() == 0) {
                continue;
            }

            for (int[] next: adj) {
                minHeap.add(new State(next[0], next[1] + currentTime));
            }
        }

        return count == n ? result : -1;
    }

    public class State {
        public int node;
        public int time;

        public State(int node, int time) {
            this.node = node;
            this.time = time;
        }
    }
}
