package com.seanlindev.algorithms;

/*
Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right, which minimizes the sum of all numbers along its path.

Note: You can only move either down or right at any point in time.



Example 1:


Input: grid = [[1,3,1],[1,5,1],[4,2,1]]
Output: 7
Explanation: Because the path 1 → 3 → 1 → 1 → 1 minimizes the sum.
Example 2:

Input: grid = [[1,2,3],[4,5,6]]
Output: 12


Constraints:

m == grid.length
n == grid[i].length
1 <= m, n <= 200
0 <= grid[i][j] <= 200

 */
public class MinimumPathSum {
    public int minPathSum(int[][] grid) {
        int rows = grid.length;
        int columns = grid[0].length;
        int[][] dp = new int[rows][columns];
        for (int row = rows - 1; row >= 0; row--) {
            for (int column = columns - 1; column >= 0; column--) {
                if (row == rows - 1 && column == columns - 1) {
                    dp[row][column] = grid[row][column];
                } else {
                    int num = grid[row][column];
                    int result = Integer.MAX_VALUE;
                    if (row + 1 < rows) {
                        result = Math.min(result, num + dp[row + 1][column]);
                    }

                    if (column + 1 < columns) {
                        result = Math.min(result, num + dp[row][column + 1]);
                    }
                    dp[row][column] = result;
                }
            }
        }

        return dp[0][0];
        // Integer[][] dp = new Integer[rows][columns];
        // return minPathSumRecursion(grid, rows, columns, 0, 0, dp);
    }

    // (0,0)
    // 1 + (1,0)
    //1 + (2,0)
    // 4 +
    //1 + (1,1)
    // 1 + (0,1)
    int minPathSumRecursion(int[][] grid, int rows, int columns, int row, int column, Integer[][] dp) {
        if (dp[row][column] != null) {
            return dp[row][column];
        }

        int num = grid[row][column];
        if (row == rows - 1 && column == columns - 1) {
            return num;
        }

        int result = Integer.MAX_VALUE;
        if (row + 1 < rows) {
            result = Math.min(result, num + minPathSumRecursion(grid, rows, columns, row + 1, column, dp));
        }

        if (column + 1 < columns) {
            result = Math.min(result, num + minPathSumRecursion(grid, rows, columns, row, column + 1, dp));
        }

        dp[row][column] = result;
        return result;
    }
}
