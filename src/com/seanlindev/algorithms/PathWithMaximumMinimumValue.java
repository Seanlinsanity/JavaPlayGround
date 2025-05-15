package com.seanlindev.algorithms;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/*
Given an m x n integer matrix grid, return the maximum score of a path starting at (0, 0) and ending at (m - 1, n - 1) moving in the 4 cardinal directions.

The score of a path is the minimum value in that path.

For example, the score of the path 8 → 4 → 5 → 9 is 4.


Example 1:


Input: grid = [[5,4,5],[1,2,6],[7,4,6]]
Output: 4
Explanation: The path with the maximum score is highlighted in yellow.
Example 2:


Input: grid = [[2,2,1,2,2,2],[1,2,2,2,1,2]]
Output: 2
Example 3:


Input: grid = [[3,4,6,3,4],[0,2,1,1,7],[8,8,3,2,7],[3,2,4,9,8],[4,1,2,0,0],[4,6,5,4,3]]
Output: 3


Constraints:

m == grid.length
n == grid[i].length
1 <= m, n <= 100
0 <= grid[i][j] <= 109
 */
public class PathWithMaximumMinimumValue {
    // 4 directions to a cell's possible neighbors.
    public int[][] dirs = new int[][] {
            { 1, 0 },
            { -1, 0 },
            { 0, 1 },
            { 0, -1 },
    };

    public int maximumMinimumPath(int[][] grid) {
        int R = grid.length, C = grid[0].length;
        int left = 0, right = Math.min(grid[0][0], grid[R - 1][C - 1]);

        int result = 0;
        while (left <= right) {
            // Get the middle value between left and right boundaries.
            int middle = (right + left) / 2;

            // Check if we can find a path of value equals middle, and cut
            // the search space by half.
            if (pathExists(grid, middle)) {
                result = Math.max(result, middle);
                left = middle + 1;
            } else {
                right = middle - 1;
            }
        }

        // Once the left and right boundaries coincide, we find the target value,
        // that is, the maximum value of a path.
        return result;
    }

    // Check if we can find a path of value equals val.
    private boolean pathExists(int[][] grid, int val) {
        int R = grid.length, C = grid[0].length;

        // Maintain the visited status of each cell. Initialize the status of
        // all the cells as false (unvisited).
        boolean[][] visited = new boolean[R][C];

        // Put the starting cell grid[0][0] in the deque and mark it as visited.
        Queue<int[]> deque = new LinkedList<>();
        deque.offer(new int[] { 0, 0 });
        visited[0][0] = true;

        // While we still have cells of value larger than or equal to val.
        while (!deque.isEmpty()) {
            int[] curGrid = deque.poll();
            int curRow = curGrid[0];
            int curCol = curGrid[1];

            // If the current cell is the bottom-right cell, return true.
            if (curRow == R - 1 && curCol == C - 1) {
                return true;
            }
            for (int[] dir : dirs) {
                int newRow = curRow + dir[0];
                int newCol = curCol + dir[1];

                // Check if the current cell has any unvisited neighbors with value larger
                // than or equal to val.
                if (
                        0 <= newRow &&
                                newRow < R &&
                                0 <= newCol &&
                                newCol < C &&
                                visited[newRow][newCol] == false &&
                                grid[newRow][newCol] >= val
                ) {
                    // If so, we put this neighbor to the deque and mark it as visited.
                    visited[newRow][newCol] = true;
                    deque.offer(new int[] { newRow, newCol });
                }
            }
        }
        return false;
    }
    // Shortest Path Algorithm
    public int maximumMinimumPath2(int[][] grid) {
        int rows = grid.length, cols = grid[0].length;
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>((a, b) -> Integer.compare(b[0], a[0]));
        boolean[][] visited = new boolean[rows][cols];
        maxHeap.add(new int[]{ grid[0][0], 0, 0 });
        int result = grid[0][0];
        while (maxHeap.size() > 0) {
            int[] cell = maxHeap.poll();
            result = Math.min(result, cell[0]);
            int r = cell[1], c = cell[2];

            if (r == rows - 1 && c == cols - 1) {
                return result;
            }

            visited[r][c] = true;

            int[][] neighbors = {
                    { r + 1, c },
                    { r - 1, c },
                    { r , c - 1},
                    { r , c + 1 }
            };
            for (int[] neighbor: neighbors) {
                int nr = neighbor[0], nc = neighbor[1];
                if (nr < 0 || nr >= rows || nc < 0 || nc >= cols || visited[nr][nc]) { continue; }

                maxHeap.add(new int[]{ grid[nr][nc], nr, nc });
            }
        }

        return -1;
    }
}
