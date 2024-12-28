package com.seanlindev.algorithms;

import java.util.*;

/*
You are given an array of variable pairs equations and an array of real numbers values, where equations[i] = [Ai, Bi] and values[i] represent the equation Ai / Bi = values[i]. Each Ai or Bi is a string that represents a single variable.

You are also given some queries, where queries[j] = [Cj, Dj] represents the jth query where you must find the answer for Cj / Dj = ?.

Return the answers to all queries. If a single answer cannot be determined, return -1.0.

Note: The input is always valid. You may assume that evaluating the queries will not result in division by zero and that there is no contradiction.

Note: The variables that do not occur in the list of equations are undefined, so the answer cannot be determined for them.



Example 1:

Input: equations = [["a","b"],["b","c"]], values = [2.0,3.0], queries = [["a","c"],["b","a"],["a","e"],["a","a"],["x","x"]]
Output: [6.00000,0.50000,-1.00000,1.00000,-1.00000]
Explanation:
Given: a / b = 2.0, b / c = 3.0
queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ?
return: [6.0, 0.5, -1.0, 1.0, -1.0 ]
note: x is undefined => -1.0
Example 2:

Input: equations = [["a","b"],["b","c"],["bc","cd"]], values = [1.5,2.5,5.0], queries = [["a","c"],["c","b"],["bc","cd"],["cd","bc"]]
Output: [3.75000,0.40000,5.00000,0.20000]
Example 3:

Input: equations = [["a","b"]], values = [0.5], queries = [["a","b"],["b","a"],["a","c"],["x","y"]]
Output: [0.50000,2.00000,-1.00000,-1.00000]


Constraints:

1 <= equations.length <= 20
equations[i].length == 2
1 <= Ai.length, Bi.length <= 5
values.length == equations.length
0.0 < values[i] <= 20.0
1 <= queries.length <= 20
queries[i].length == 2
1 <= Cj.length, Dj.length <= 5
Ai, Bi, Cj, Dj consist of lower case English letters and digits.
 */
public class EvaluateDivision {
    class Node {
        String variable;
        double val;

        Node(String variable, double val) {
            this.variable = variable;
            this.val = val;
        }
    }

    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        Map<String, List<Node>> graph = new HashMap<>();
        Set<String> variableSet = new HashSet<>();
        for (int i = 0; i < equations.size(); i++) {
            List<String> equation = equations.get(i);
            double value = values[i];
            String a = equation.get(0);
            String b = equation.get(1);
            graph.putIfAbsent(a, new ArrayList<>());
            graph.get(a).add(new Node(b, value));
            graph.putIfAbsent(b, new ArrayList<>());
            graph.get(b).add(new Node(a, 1 / value));
            variableSet.add(a);
            variableSet.add(b);
        }

        double[] result = new double[queries.size()];
        Arrays.fill(result, -1);
        for (int i = 0; i < queries.size(); i++) {
            List<String> query = queries.get(i);
            String start = query.get(0);
            String end = query.get(1);
            if (!variableSet.contains(start) || !variableSet.contains(end)) {
                continue;
            }

            if (start.equals(end)) {
                result[i] = 1;
                continue;
            }

            // BFS
            Queue<Node> queue = new LinkedList<>();
            Set<String> visited = new HashSet<>();
            queue.add(new Node(start, 1));
            visited.add(start);
            while (queue.size() > 0 && result[i] == -1) {
                int size = queue.size();
                for (int s = 0; s < size; s++) {
                    Node node = queue.remove();

                    List<Node> neighbors = graph.get(node.variable);
                    for (Node neighbor: neighbors) {
                        if (visited.contains(neighbor.variable)) {
                            continue;
                        }

                        if (neighbor.variable.equals(end)) {
                            result[i] = node.val * neighbor.val;
                            break;
                        }

                        visited.add(neighbor.variable);
                        queue.add(new Node(neighbor.variable, node.val * neighbor.val));
                    }

                    if (result[i] != -1) {
                        break;
                    }
                }
            }
        }

        return result;
    }
}
