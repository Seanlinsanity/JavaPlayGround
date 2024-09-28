package com.seanlindev.algorithms;

/*
Given an m x n binary matrix filled with 0's and 1's, find the largest square containing only 1's and return its area.



Example 1:


Input: matrix = [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]]
Output: 4
Example 2:


Input: matrix = [["0","1"],["1","0"]]
Output: 1
Example 3:

Input: matrix = [["0"]]
Output: 0


Constraints:

m == matrix.length
n == matrix[i].length
1 <= m, n <= 300
matrix[i][j] is '0' or '1'.
 */
public class MaximalSquare {
    public int maximalSquare(char[][] matrix) {
        int rows = matrix.length;
        int columns = matrix[0].length;
        int[][] copy = new int[rows][columns];
        int result = 0;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                if (matrix[r][c] == '0') {
                    copy[r][c] = 0;
                } else {
                    copy[r][c] = 1;
                    result = 1;
                }
            }
        }

        if (rows == 1 || columns == 1) { return result; }

        for (int r = rows - 2; r >= 0; r--) {
            for (int c = columns - 2; c >= 0; c--) {
                if (copy[r][c] == 0) {
                    continue;
                }

                int right = copy[r][c + 1];
                int bottom = copy[r + 1][c];
                int diag = copy[r + 1][c + 1];
                int min = Math.min(diag, Math.min(right, bottom));
                int max = copy[r][c] + min;
                copy[r][c] = max;
                result = Math.max(result, max);
            }
        }

        return result * result;
    }
}
