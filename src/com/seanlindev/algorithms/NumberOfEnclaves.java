package com.seanlindev.algorithms;

/*
You are given an m x n binary matrix grid, where 0 represents a sea cell and 1 represents a land cell.

A move consists of walking from one land cell to another adjacent (4-directionally) land cell or walking off the boundary of the grid.

Return the number of land cells in grid for which we cannot walk off the boundary of the grid in any number of moves.



Example 1:


Input: grid = [[0,0,0,0],[1,0,1,0],[0,1,1,0],[0,0,0,0]]
Output: 3
Explanation: There are three 1s that are enclosed by 0s, and one 1 that is not enclosed because its on the boundary.
Example 2:


Input: grid = [[0,1,1,0],[0,0,1,0],[0,0,1,0],[0,0,0,0]]
Output: 0
Explanation: All 1s are either on the boundary or can reach the boundary.


Constraints:

m == grid.length
n == grid[i].length
1 <= m, n <= 500
grid[i][j] is either 0 or 1.
 */
public class NumberOfEnclaves {
    public int numEnclaves(int[][] grid) {
        int rows = grid.length;
        int columns = grid[0].length;
        // top & bottom
        for (int c = 0; c < columns; c++) {
            dfs(grid, rows, columns, 0, c);
            dfs(grid, rows, columns, rows - 1, c);
        }

        // left & right
        for (int r = 0; r < rows; r++) {
            dfs(grid, rows, columns, r, 0);
            dfs(grid, rows, columns, r, columns - 1);
        }

        int count = 0;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                if (grid[r][c] == 1) {
                    count += 1;
                }
            }
        }

        return count;
    }

    private void dfs(int[][] grid, int rows, int columns, int row, int column) {
        if (row < 0 || row >= rows || column < 0 || column >= columns) { return; }
        if (grid[row][column] != 1) { return; }

        grid[row][column] = 2;
        int[][] neigbhors = {
                {row - 1, column},
                {row + 1, column},
                {row, column + 1},
                {row, column - 1}
        };
        for (int[] neighbor: neigbhors) {
            dfs(grid, rows, columns, neighbor[0], neighbor[1]);
        }
    }
}
