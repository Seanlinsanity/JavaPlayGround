package com.seanlindev.algorithms;

/*
Given a positive integer n, generate an n x n matrix filled with elements from 1 to n2 in spiral order.



Example 1:


Input: n = 3
Output: [[1,2,3],[8,9,4],[7,6,5]]
Example 2:

Input: n = 1
Output: [[1]]


Constraints:

1 <= n <= 20

 */
public class SpiralMatrixII {
    public int[][] generateMatrix(int n) {
        /*
            [1,2,3]
            [8,0,4]
            [7,6,5]
        */
        int[][] matrix = new int[n][n];
        int left = 0, right = n - 1, top = 0, bottom = n - 1;
        int num = 1;

        // l = 0, r = 2, t = 0, b = 2
        // l = 1, r = 1, t = 1, b = 1
        while (left <= right && top <= bottom) {
            for (int c = left; c <= right; c++) { //c=1
                matrix[top][c] = num; //matrix[1][1] = 9
                num += 1;
            }

            for (int r = top + 1; r <= bottom; r++) {//r=2
                matrix[r][right] = num;
                num += 1;
            }

            for (int c = right - 1; c >= left; c--) { //c=0
                matrix[bottom][c] = num; //matrix[1][0]
                num += 1;
            }

            for (int r = bottom - 1; r >= top + 1; r--) { //r=1
                matrix[r][left] = num; //matrix[1][0]
                num += 1;
            }

            left += 1;
            right -= 1;
            top += 1;
            bottom -= 1;
        }

        return matrix;
    }
}
