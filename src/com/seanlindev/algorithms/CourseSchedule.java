package com.seanlindev.algorithms;

import java.util.ArrayList;
import java.util.List;

/*
There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1. You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course bi first if you want to take course ai.

For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
Return true if you can finish all courses. Otherwise, return false.



Example 1:

Input: numCourses = 2, prerequisites = [[1,0]]
Output: true
Explanation: There are a total of 2 courses to take.
To take course 1 you should have finished course 0. So it is possible.
Example 2:

Input: numCourses = 2, prerequisites = [[1,0],[0,1]]
Output: false
Explanation: There are a total of 2 courses to take.
To take course 1 you should have finished course 0, and to take course 0 you should also have finished course 1. So it is impossible.


Constraints:

1 <= numCourses <= 2000
0 <= prerequisites.length <= 5000
prerequisites[i].length == 2
0 <= ai, bi < numCourses
All the pairs prerequisites[i] are unique.
 */
public class CourseSchedule {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            adj.add(new ArrayList<>());
        }

        for (int i = 0; i < prerequisites.length; i++) {
            adj.get(prerequisites[i][0]).add(prerequisites[i][1]);
        }

        for (int i = 0; i < numCourses; i++) {
            if (adj.get(i).size() == 0) {
                continue;
            }

            int[] visited = new int[numCourses];
            if (dfs(adj, visited, i) == false) {
                return false;
            }
        }
        return true;
    }

    private boolean dfs(List<List<Integer>> adj, int[] visited, int curr) {
        if (visited[curr] == 1) {
            return false;
        }

        if (adj.get(curr).size() == 0) {
            return true;
        }

        visited[curr] = 1;
        for (int i = 0; i < adj.get(curr).size(); i++) {
            if (dfs(adj, visited, adj.get(curr).get(i)) == false) {
                return false;
            }
        }
        visited[curr] = 0;
        adj.get(curr).removeAll(adj.get(curr));
        return true;
    }

    public boolean canFinish2(int numCourses, int[][] prerequisites) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            adj.add(new ArrayList<>());
        }

        for (int i = 0; i < prerequisites.length; i++) {
            adj.get(prerequisites[i][0]).add(prerequisites[i][1]);
        }

        int[] visited = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            if (visited[i] == 0) {
                if (isCyclic(adj, visited, i)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isCyclic(List<List<Integer>> adj, int[] visited, int curr) {
        if (visited[curr] == 2) {
            return true;
        }

        visited[curr] = 2;
        for (int i = 0; i < adj.get(curr).size(); i++) {
            if (visited[adj.get(curr).get(i)] != 1) {
                if (isCyclic(adj, visited, adj.get(curr).get(i))) {
                    return true;
                }
            }
        }
        visited[curr] = 1;
        return false;
    }
}
