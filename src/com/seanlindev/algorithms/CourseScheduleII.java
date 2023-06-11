package com.seanlindev.algorithms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1. You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course bi first if you want to take course ai.

For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
Return the ordering of courses you should take to finish all courses. If there are many valid answers, return any of them. If it is impossible to finish all courses, return an empty array.



Example 1:

Input: numCourses = 2, prerequisites = [[1,0]]
Output: [0,1]
Explanation: There are a total of 2 courses to take. To take course 1 you should have finished course 0. So the correct course order is [0,1].
Example 2:

Input: numCourses = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]]
Output: [0,2,1,3]
Explanation: There are a total of 4 courses to take. To take course 3 you should have finished both courses 1 and 2. Both courses 1 and 2 should be taken after you finished course 0.
So one correct course order is [0,1,2,3]. Another correct ordering is [0,2,1,3].
Example 3:

Input: numCourses = 1, prerequisites = []
Output: [0]


Constraints:

1 <= numCourses <= 2000
0 <= prerequisites.length <= numCourses * (numCourses - 1)
prerequisites[i].length == 2
0 <= ai, bi < numCourses
ai != bi
All the pairs [ai, bi] are distinct.
 */
public class CourseScheduleII {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        Map<Integer, List<Integer>> coursePrerequisiteMap = new HashMap<>();
        for (int[] prerequisite:  prerequisites) {
            coursePrerequisiteMap.putIfAbsent(prerequisite[0], new ArrayList<>());
            List<Integer> list = coursePrerequisiteMap.get(prerequisite[0]);
            list.add(prerequisite[1]);
        }
        //[[0,1],[1,0]]
        //{0:[1], 1:[0]}
        boolean[] isNotCycles = new boolean[numCourses]; //[false, false, false, false]
        boolean[] visited = new boolean[numCourses]; //[false, false, false, false]
        List<Integer> result = new ArrayList<>(); //[]
        for (int i = 0; i < numCourses; i++) {
            boolean success = dfs(i, coursePrerequisiteMap, visited, isNotCycles, result);
            if (!success) { return new int[]{}; }
        }

        int[] arrResult = new int[result.size()];
        for (int i = 0; i < arrResult.length; i++) {
            arrResult[i] = result.get(i);
        }
        return arrResult;
    }

    public boolean dfs(int course, Map<Integer, List<Integer>> map, boolean[] visited, boolean[] isNotCycles, List<Integer> result) {
        if (visited[course]) {
            return isNotCycles[course];
        }

        visited[course] = true;
        if (map.get(course) != null) {
            for (Integer next: map.get(course)) {
                boolean success = dfs(next, map, visited, isNotCycles, result);
                if (!success) {
                    isNotCycles[course] = false;
                    return false;
                }
            }
        }

        isNotCycles[course] = true;
        result.add(course);
        return true;
    }
}
