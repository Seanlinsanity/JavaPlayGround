package com.seanlindev.algorithms;

import java.util.*;

/*
There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1. You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course ai first if you want to take course bi.

For example, the pair [0, 1] indicates that you have to take course 0 before you can take course 1.
Prerequisites can also be indirect. If course a is a prerequisite of course b, and course b is a prerequisite of course c, then course a is a prerequisite of course c.

You are also given an array queries where queries[j] = [uj, vj]. For the jth query, you should answer whether course uj is a prerequisite of course vj or not.

Return a boolean array answer, where answer[j] is the answer to the jth query.



Example 1:


Input: numCourses = 2, prerequisites = [[1,0]], queries = [[0,1],[1,0]]
Output: [false,true]
Explanation: The pair [1, 0] indicates that you have to take course 1 before you can take course 0.
Course 0 is not a prerequisite of course 1, but the opposite is true.
Example 2:

Input: numCourses = 2, prerequisites = [], queries = [[1,0],[0,1]]
Output: [false,false]
Explanation: There are no prerequisites, and each course is independent.
Example 3:


Input: numCourses = 3, prerequisites = [[1,2],[1,0],[2,0]], queries = [[1,0],[1,2]]
Output: [true,true]


Constraints:

2 <= numCourses <= 100
0 <= prerequisites.length <= (numCourses * (numCourses - 1) / 2)
prerequisites[i].length == 2
0 <= ai, bi <= n - 1
ai != bi
All the pairs [ai, bi] are unique.
The prerequisites graph has no cycles.
1 <= queries.length <= 104
0 <= ui, vi <= n - 1
ui != vi
 */

public class CourseScheduleIV {
    public List<Boolean> checkIfPrerequisite(int numCourses, int[][] prerequisites, int[][] queries) {
        Map<Integer, List<Integer>> adjMap = new HashMap<>();
        for (int[] prerequisite: prerequisites) {
            int from = prerequisite[0];
            int to = prerequisite[1];
            adjMap.putIfAbsent(from, new ArrayList<>());
            List<Integer> list = adjMap.get(from);
            list.add(to);
        }

        Map<Integer, Set<Integer>> courseRequisitesMap = new HashMap<>();
        for (int i = 0; i < numCourses; i++) {
            dfs(i, adjMap, courseRequisitesMap);
        }

        List<Boolean> result = new ArrayList<>();
        for (int[] query: queries) {
            int course1 = query[0];
            int course2 = query[1];
            Set<Integer> courseRequisites = courseRequisitesMap.get(course1);
            result.add(courseRequisites.contains(course2));
        }

        return result;
    }
    //1, {1:[0]}, {1:[1,0], 0:[0]}
    //0, {1:[0]}, {0:[0]}}
    Set<Integer> dfs(Integer start, Map<Integer, List<Integer>> adjMap, Map<Integer, Set<Integer>> courseRequisitesMap) {
        Set<Integer> cache = courseRequisitesMap.get(start);
        if (cache != null) {
            return cache;
        }

        List<Integer> adjList = adjMap.get(start);
        Set<Integer> requisites = new HashSet<>();
        requisites.add(start);
        if (adjList == null) {
            courseRequisitesMap.put(start, requisites);
            return requisites;
        }

        for (Integer adj: adjList) {
            requisites.addAll(dfs(adj, adjMap, courseRequisitesMap));
        }

        courseRequisitesMap.put(start, requisites);
        return requisites;
    }
}
