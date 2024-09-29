package com.seanlindev.algorithms;

/*
You are given an m x n integer matrix grid, and three integers row, col, and color. Each value in the grid represents the color of the grid square at that location.

Two squares are called adjacent if they are next to each other in any of the 4 directions.

Two squares belong to the same connected component if they have the same color and they are adjacent.

The border of a connected component is all the squares in the connected component that are either adjacent to (at least) a square not in the component, or on the boundary of the grid (the first or last row or column).

You should color the border of the connected component that contains the square grid[row][col] with color.

Return the final grid.



Example 1:

Input: grid = [[1,1],[1,2]], row = 0, col = 0, color = 3
Output: [[3,3],[3,2]]
Example 2:

Input: grid = [[1,2,2],[2,3,2]], row = 0, col = 1, color = 3
Output: [[1,3,3],[2,3,3]]
Example 3:

Input: grid = [[1,1,1],[1,1,1],[1,1,1]], row = 1, col = 1, color = 2
Output: [[2,2,2],[2,1,2],[2,2,2]]


Constraints:

m == grid.length
n == grid[i].length
1 <= m, n <= 50
1 <= grid[i][j], color <= 1000
0 <= row < m
0 <= col < n
 */
public class ColoringBorder {
    public int[][] colorBorder(int[][] grid, int row, int col, int color) {
        int rows = grid.length;
        int cols = grid[0].length;
        int findColor = grid[row][col];
        dfs(grid, rows, cols, row, col, findColor);

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == 0) {
                    grid[r][c] = color;
                } else if (grid[r][c] == -1) {
                    grid[r][c] = findColor;
                }
            }
        }
        return grid;

        // int[][] copy = new int[rows][cols];
        // for (int r = 0; r < rows; r++) {
        //     for (int c = 0; c < cols; c++) {
        //         if (grid[r][c] == 0) {
        //             if (r - 1 < 0 || c - 1 < 0 || r + 1 == rows || c + 1 == cols) {
        //                 copy[r][c] = color;
        //             } else if (grid[r + 1][c] != 0 || grid[r - 1][c] != 0 || grid[r][c + 1] != 0 || grid[r][c - 1] != 0) {
        //                 copy[r][c] = color;
        //             } else {
        //                 copy[r][c] = findColor;
        //             }
        //         } else {
        //             copy[r][c] = grid[r][c];
        //         }
        //     }
        // }

        // return copy;
    }

    boolean dfs(int[][] grid, int rows, int cols, int r, int c, int findColor) {
        if (r < 0 || r >= rows || c < 0 || c >= cols) { return true; }
        if (grid[r][c] == 0 || grid[r][c] == -1 || grid[r][c] == -2) { return false; }
        if (grid[r][c] != findColor) { return true; }

        grid[r][c] = -2;
        boolean top = dfs(grid, rows, cols, r - 1, c, findColor);
        boolean bottom = dfs(grid, rows, cols, r + 1, c, findColor);
        boolean left = dfs(grid, rows, cols, r, c - 1, findColor);
        boolean right = dfs(grid, rows, cols, r, c + 1, findColor);
        if (top || bottom || left || right) {
            grid[r][c] = 0;
        } else {
            grid[r][c] = -1;
        }

        return false;
    }
}
