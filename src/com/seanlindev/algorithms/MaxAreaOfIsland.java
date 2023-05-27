package com.seanlindev.algorithms;

/*
You are given an m x n binary matrix grid. An island is a group of 1's (representing land) connected 4-directionally (horizontal or vertical.) You may assume all four edges of the grid are surrounded by water.

The area of an island is the number of cells with a value 1 in the island.

Return the maximum area of an island in grid. If there is no island, return 0.



Example 1:


Input: grid = [[0,0,1,0,0,0,0,1,0,0,0,0,0],[0,0,0,0,0,0,0,1,1,1,0,0,0],[0,1,1,0,1,0,0,0,0,0,0,0,0],[0,1,0,0,1,1,0,0,1,0,1,0,0],[0,1,0,0,1,1,0,0,1,1,1,0,0],[0,0,0,0,0,0,0,0,0,0,1,0,0],[0,0,0,0,0,0,0,1,1,1,0,0,0],[0,0,0,0,0,0,0,1,1,0,0,0,0]]
Output: 6
Explanation: The answer is not 11, because the island must be connected 4-directionally.
Example 2:

Input: grid = [[0,0,0,0,0,0,0,0]]
Output: 0


Constraints:

m == grid.length
n == grid[i].length
1 <= m, n <= 50
grid[i][j] is either 0 or 1.
 */
public class MaxAreaOfIsland {
    int area = 0;

    public int maxAreaOfIsland(int[][] grid) {
        int result = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    area = 0;
                    dfs(i, j, grid);
                    result = Math.max(result, area);
                }
            }
        }

        return result;
    }

    void dfs(int startRow, int startColumn, int[][] grid) {
        if (startRow < 0 ||
                startRow >= grid.length ||
                startColumn < 0 ||
                startColumn >= grid[0].length ||
                grid[startRow][startColumn] == 0) {
            return;
        }

        grid[startRow][startColumn] = 0;
        area += 1;
        dfs(startRow, startColumn - 1, grid);
        dfs(startRow, startColumn + 1, grid);
        dfs(startRow - 1, startColumn, grid);
        dfs(startRow + 1, startColumn, grid);
    }
}
