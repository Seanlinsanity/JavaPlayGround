package com.seanlindev.algorithms;

import java.util.*;

/*
You are given an undirected weighted graph of n nodes (0-indexed), represented by an edge list where edges[i] = [a, b] is an undirected edge connecting the nodes a and b with a probability of success of traversing that edge succProb[i].

Given two nodes start and end, find the path with the maximum probability of success to go from start to end and return its success probability.

If there is no path from start to end, return 0. Your answer will be accepted if it differs from the correct answer by at most 1e-5.



Example 1:



Input: n = 3, edges = [[0,1],[1,2],[0,2]], succProb = [0.5,0.5,0.2], start = 0, end = 2
Output: 0.25000
Explanation: There are two paths from start to end, one having a probability of success = 0.2 and the other has 0.5 * 0.5 = 0.25.
Example 2:



Input: n = 3, edges = [[0,1],[1,2],[0,2]], succProb = [0.5,0.5,0.3], start = 0, end = 2
Output: 0.30000
Example 3:



Input: n = 3, edges = [[0,1]], succProb = [0.5], start = 0, end = 2
Output: 0.00000
Explanation: There is no path between 0 and 2.


Constraints:

2 <= n <= 10^4
0 <= start, end < n
start != end
0 <= a, b < n
a != b
0 <= succProb.length == edges.length <= 2*10^4
0 <= succProb[i] <= 1
 */
public class MaxProbability {
    //DijkstraAlgorithm

    //n = 3, edges = [[0,1],[1,2],[0,2]], succProb = [0.5,0.5,0.2], start = 0, end = 2
    public double maxProbability(int n, int[][] edges, double[] succProb, int start, int end) {
        // create the graph
        List<double[]>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < edges.length; i++) {
            double from =  edges[i][0];
            double to =  edges[i][1];
            double weight = succProb[i];
            double[] m = new double[2];
            m[0] = to;
            m[1] = weight;
            graph[edges[i][0]].add(m);
            double[] k = new double[2];
            k[0] = from;
            k[1] = weight;
            graph[edges[i][1]].add(k);
        }

        // call dijkstra and return
        return dijkstra(start, end, graph);
    }

    class State{
        int id;
        double probabilityToStart;

        public State(int id, double probabilityToStart){
            this.id = id;
            this.probabilityToStart = probabilityToStart;
        }
    }

    private double dijkstra(int start, int end, List<double[]>[] graph){
        double[] probabilities = new double[graph.length];
        boolean[] visited = new boolean[graph.length];

        PriorityQueue<State> heap = new PriorityQueue<State>((a, b) ->
                Double.compare(b.probabilityToStart, a.probabilityToStart)
        );
        heap.add(new State(start, 1));

        while (!heap.isEmpty()){
            State current = heap.poll();
            int currentId = current.id;
            double currentProbability = current.probabilityToStart;

            if (visited[currentId]) {
                continue;
            }

            visited[currentId] = true;
            probabilities[currentId] = currentProbability;

            if (currentId == end) {
                return currentProbability;
            }

            List<double[]> adjacency = graph[currentId];
            for (double[] next: adjacency) {
                double probabilityToNext = currentProbability * next[1];
                int idx = (int) next[0];
                heap.offer(new State(idx, probabilityToNext));
            }
        }

        return 0;
    }
}
