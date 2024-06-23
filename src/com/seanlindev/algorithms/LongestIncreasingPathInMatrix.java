package com.seanlindev.algorithms;

/*
Given an m x n integers matrix, return the length of the longest increasing path in matrix.

From each cell, you can either move in four directions: left, right, up, or down. You may not move diagonally or move outside the boundary (i.e., wrap-around is not allowed).



Example 1:


Input: matrix = [[9,9,4],[6,6,8],[2,1,1]]
Output: 4
Explanation: The longest increasing path is [1, 2, 6, 9].
Example 2:


Input: matrix = [[3,4,5],[3,2,6],[2,2,1]]
Output: 4
Explanation: The longest increasing path is [3, 4, 5, 6]. Moving diagonally is not allowed.
Example 3:

Input: matrix = [[1]]
Output: 1


Constraints:

m == matrix.length
n == matrix[i].length
1 <= m, n <= 200
0 <= matrix[i][j] <= 231 - 1
 */
public class LongestIncreasingPathInMatrix {
    // public int longestIncreasingPath(int[][] matrix) {
    //     int m = matrix.length;
    //     int n = matrix[0].length;
    //     int[][] dp = new int[m][n];
    //     for (int d[] : dp) Arrays.fill(d, -1);

    //     int max = Integer.MIN_VALUE;
    //     for (int i = 0; i < m; i++) {
    //         for (int j = 0; j < n; j++) {
    //             if (dp[i][j] == -1) dfs(matrix, dp, m, n, i, j, -1);

    //             max = Math.max(max, dp[i][j]);
    //         }
    //     }
    //     return max;
    // }

    // public int dfs(
    //     int[][] matrix,
    //     int[][] dp,
    //     int m,
    //     int n,
    //     int i,
    //     int j,
    //     int parent
    // ) {
    //     if (
    //         i >= m || j >= n || i < 0 || j < 0 || matrix[i][j] <= parent
    //     ) return 0;
    //     parent = matrix[i][j];
    //     if (dp[i][j] != -1) return dp[i][j];
    //     int left = dfs(matrix, dp, m, n, i, j - 1, parent);
    //     int right = dfs(matrix, dp, m, n, i, j + 1, parent);
    //     int bottom = dfs(matrix, dp, m, n, i + 1, j, parent);
    //     int top = dfs(matrix, dp, m, n, i - 1, j, parent);
    //     dp[i][j] = 1 + Math.max(Math.max(left, right), Math.max(top, bottom));
    //     return dp[i][j];
    // }

    public int longestIncreasingPath(int[][] matrix) {
        int rows = matrix.length;
        int columns = matrix[0].length;
        int[][] dp = new int[rows][columns];
        int result = 0;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                result = Math.max(dfs(r, c, matrix, rows, columns, dp), result);
            }
        }

        return result;
    }

    int dfs(int row, int column, int[][] matrix, int rows, int columns, int[][] dp) {
        if (dp[row][column] > 0) { return dp[row][column]; }

        int val = matrix[row][column];
        int maxLength = 1;
        int[][] next = new int[][]{
                {row + 1, column},
                {row - 1, column},
                {row, column + 1},
                {row, column - 1}
        };

        for (int[] path: next) {
            int nextRow = path[0];
            int nextColumn = path[1];
            if (nextRow >= 0 && nextRow < rows && nextColumn >= 0 && nextColumn < columns && matrix[nextRow][nextColumn] > val) {
                maxLength = Math.max(maxLength, 1 + dfs(nextRow, nextColumn, matrix, rows, columns, dp));
            }
        }

        dp[row][column] = maxLength;
        return maxLength;
    }
}
