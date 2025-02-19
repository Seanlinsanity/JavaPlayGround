package com.seanlindev.algorithms;

import java.util.PriorityQueue;

/*
You are a hiker preparing for an upcoming hike. You are given heights, a 2D array of size rows x columns, where heights[row][col] represents the height of cell (row, col). You are situated in the top-left cell, (0, 0), and you hope to travel to the bottom-right cell, (rows-1, columns-1) (i.e., 0-indexed). You can move up, down, left, or right, and you wish to find a route that requires the minimum effort.

A route's effort is the maximum absolute difference in heights between two consecutive cells of the route.

Return the minimum effort required to travel from the top-left cell to the bottom-right cell.



Example 1:



Input: heights = [[1,2,2],[3,8,2],[5,3,5]]
Output: 2
Explanation: The route of [1,3,5,3,5] has a maximum absolute difference of 2 in consecutive cells.
This is better than the route of [1,2,2,2,5], where the maximum absolute difference is 3.
Example 2:



Input: heights = [[1,2,3],[3,8,4],[5,3,5]]
Output: 1
Explanation: The route of [1,2,3,4,5] has a maximum absolute difference of 1 in consecutive cells, which is better than route [1,3,5,3,5].
Example 3:


Input: heights = [[1,2,1,1,1],[1,2,1,2,1],[1,2,1,2,1],[1,2,1,2,1],[1,1,1,2,1]]
Output: 0
Explanation: This route does not require any effort.


Constraints:

rows == heights.length
columns == heights[i].length
1 <= rows, columns <= 100
1 <= heights[i][j] <= 106
 */
public class PathWithMinimumEffort {
    public int minimumEffortPath(int[][] heights) {
        int rows = heights.length;
        int cols = heights[0].length;
        boolean[][] visited = new boolean[rows][cols];
        // diff, r, c
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
        minHeap.add(new int[]{0, 0, 0});
        while (!minHeap.isEmpty()) {
            int[] node = minHeap.poll();
            int diff = node[0];
            int r = node[1];
            int c = node[2];

            if (r == rows - 1 && c == cols - 1) { return diff; }

            visited[r][c] = true;
            int[][] neighbors = new int[][]{
                    {r + 1, c},
                    {r - 1, c},
                    {r, c + 1},
                    {r, c - 1}
            };
            for (int[] neighbor: neighbors) {
                int nR = neighbor[0];
                int nC = neighbor[1];
                if (nR < 0 || nR >= rows || nC < 0 || nC >= cols) { continue; }
                if (visited[nR][nC]) { continue; }
                minHeap.add(new int[]{ Math.max(diff, Math.abs(heights[r][c] - heights[nR][nC])), nR, nC });
            }
        }

        return -1;
    }
}
