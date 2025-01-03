package com.seanlindev.algorithms;

/*
Given an n x n array of integers matrix, return the minimum sum of any falling path through matrix.

A falling path starts at any element in the first row and chooses the element in the next row that is either directly below or diagonally left/right. Specifically, the next element from position (row, col) will be (row + 1, col - 1), (row + 1, col), or (row + 1, col + 1).



Example 1:


Input: matrix = [[2,1,3],[6,5,4],[7,8,9]]
Output: 13
Explanation: There are two falling paths with a minimum sum as shown.
Example 2:


Input: matrix = [[-19,57],[-40,-5]]
Output: -59
Explanation: The falling path with a minimum sum is shown.


Constraints:

n == matrix.length == matrix[i].length
1 <= n <= 100
-100 <= matrix[i][j] <= 100
 */
public class MinimumFallingPathSum {
    public int minFallingPathSum(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        for (int r = rows - 2; r >= 0; r--) {
            for (int c = cols - 1; c >= 0; c--) {
                int currentVal = matrix[r][c];
                matrix[r][c] = currentVal + matrix[r + 1][c];
                if (c - 1 >= 0) {
                    matrix[r][c] = Math.min(matrix[r][c], currentVal + matrix[r + 1][c - 1]);
                }
                if (c + 1 < cols) {
                    matrix[r][c] = Math.min(matrix[r][c], currentVal + matrix[r + 1][c + 1]);
                }
            }
        }

        int result = Integer.MAX_VALUE;
        for (int c = 0; c < cols; c++) {
            result = Math.min(result, matrix[0][c]);
        }

        return result;
    }
}
