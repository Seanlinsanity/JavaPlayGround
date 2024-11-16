package com.seanlindev.algorithms;

import java.util.LinkedList;
import java.util.Queue;

/*
You are given an n x n binary matrix grid where 1 represents land and 0 represents water.

An island is a 4-directionally connected group of 1's not connected to any other 1's. There are exactly two islands in grid.

You may change 0's to 1's to connect the two islands to form one island.

Return the smallest number of 0's you must flip to connect the two islands.



Example 1:

Input: grid = [[0,1],[1,0]]
Output: 1
Example 2:

Input: grid = [[0,1,0],[0,0,0],[0,0,1]]
Output: 2
Example 3:

Input: grid = [[1,1,1,1,1],[1,0,0,0,1],[1,0,1,0,1],[1,0,0,0,1],[1,1,1,1,1]]
Output: 1


Constraints:

n == grid.length == grid[i].length
2 <= n <= 100
grid[i][j] is either 0 or 1.
There are exactly two islands in grid.
 */
public class ShortestBridge {
    public int shortestBridge(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;

        Queue<int[]> queue = new LinkedList<>();
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == 1) {
                    // DFS
                    dfs(r, c, rows, cols, grid, queue);
                    break;
                }
            }

            if (queue.size() > 0) { break; }
        }

        // BFS
        int distance = 0;
        int[][] diffs = {{1,0},{-1,0},{0,1},{0,-1}};
        while (queue.size() > 0) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] node = queue.remove();
                int r = node[0];
                int c = node[1];

                for (int[] diff: diffs) {
                    int adjR = r + diff[0];
                    int adjC = c + diff[1];
                    if (adjR < 0 || adjR >= rows || adjC < 0 || adjC >= cols) { continue; }

                    if (grid[adjR][adjC] == 1) { return distance; }

                    if (grid[adjR][adjC] != 0) { continue; }

                    grid[adjR][adjC] = 3;
                    queue.add(new int[]{ adjR, adjC });
                }
            }

            distance += 1;
        }

        return distance;
    }

    void dfs(int r, int c, int rows, int cols, int[][] grid, Queue<int[]> queue) {
        if (r < 0 || r >= rows || c < 0 || c >= cols || grid[r][c] != 1) { return; }

        grid[r][c] = 2;
        if (isEdge(r, c, rows, cols, grid)) {
            queue.add(new int[]{ r, c });
        }

        dfs(r + 1, c, rows, cols, grid, queue);
        dfs(r - 1, c, rows, cols, grid, queue);
        dfs(r, c + 1, rows, cols, grid, queue);
        dfs(r, c - 1, rows, cols, grid, queue);
    }

    boolean isEdge(int r, int c, int rows, int cols, int[][] grid) {
        if (r + 1 < rows && grid[r + 1][c] == 0) {
            return true;
        }

        if (r - 1 >= 0 && grid[r - 1][c] == 0) {
            return true;
        }

        if (c + 1 < cols && grid[r][c + 1] == 0) {
            return true;
        }

        if (c - 1 >= 0 && grid[r][c - 1] == 0) {
            return true;
        }

        return false;
    }
}
