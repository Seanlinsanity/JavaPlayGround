package com.seanlindev.algorithms;

/*
You are given row x col grid representing a map where grid[i][j] = 1 represents land and grid[i][j] = 0 represents water.

Grid cells are connected horizontally/vertically (not diagonally). The grid is completely surrounded by water, and there is exactly one island (i.e., one or more connected land cells).

The island doesn't have "lakes", meaning the water inside isn't connected to the water around the island. One cell is a square with side length 1. The grid is rectangular, width and height don't exceed 100. Determine the perimeter of the island.



Example 1:


Input: grid = [[0,1,0,0],[1,1,1,0],[0,1,0,0],[1,1,0,0]]
Output: 16
Explanation: The perimeter is the 16 yellow stripes in the image above.
Example 2:

Input: grid = [[1]]
Output: 4
Example 3:

Input: grid = [[1,0]]
Output: 4


Constraints:

row == grid.length
col == grid[i].length
1 <= row, col <= 100
grid[i][j] is 0 or 1.
There is exactly one island in grid.
 */
public class IslandPerimeter {
    public int islandPerimeter(int[][] grid) {
        int rows = grid.length;
        int columns = grid[0].length;
        boolean[][] visited = new boolean[rows][columns];
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                if (grid[row][column] == 1) {
                    return dfs(row, column, rows, columns, grid, visited);
                }
            }
        }

        return 0;
    }

    int dfs(int row, int column, int rows, int columns, int[][] grid, boolean[][] visited) {
        if (row < 0 || row >= rows || column < 0 || column >= columns) {
            return 1;
        }

        if (grid[row][column] == 0) {
            return 1;
        }

        if (visited[row][column]) {
            return 0;
        }

        visited[row][column] = true;

        return dfs(row - 1, column, rows, columns, grid, visited) +
                dfs(row + 1, column, rows, columns, grid, visited) +
                dfs(row, column - 1, rows, columns, grid, visited) +
                dfs(row, column + 1, rows, columns, grid, visited);
    }
}
