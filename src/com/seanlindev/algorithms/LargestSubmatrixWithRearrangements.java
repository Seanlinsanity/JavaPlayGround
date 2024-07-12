package com.seanlindev.algorithms;

import java.util.Arrays;
import java.util.Collections;

/*
You are given a binary matrix matrix of size m x n, and you are allowed to rearrange the columns of the matrix in any order.

Return the area of the largest submatrix within matrix where every element of the submatrix is 1 after reordering the columns optimally.



Example 1:


Input: matrix = [[0,0,1],[1,1,1],[1,0,1]]
Output: 4
Explanation: You can rearrange the columns as shown above.
The largest submatrix of 1s, in bold, has an area of 4.
Example 2:


Input: matrix = [[1,0,1,0,1]]
Output: 3
Explanation: You can rearrange the columns as shown above.
The largest submatrix of 1s, in bold, has an area of 3.
Example 3:

Input: matrix = [[1,1,0],[1,0,1]]
Output: 2
Explanation: Notice that you must rearrange entire columns, and there is no way to make a submatrix of 1s larger than an area of 2.


Constraints:

m == matrix.length
n == matrix[i].length
1 <= m * n <= 105
matrix[i][j] is either 0 or 1.
 */
public class LargestSubmatrixWithRearrangements {
    public int largestSubmatrix(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        // Step 1: Convert to heights matrix
        for (int i = 1; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 1) matrix[i][j] += matrix[i-1][j];
            }
        }

        int maxArea = 0;
        // Step 2 & 3: Sort each row and calculate max area
        for (int[] row : matrix) {
            Arrays.sort(row);
            for (int j = n - 1; j >= 0; j--) {
                int width = n - j;
                maxArea = Math.max(maxArea, row[j] * width);
            }
        }
        return maxArea;

//        int rows = matrix.length;
//        int columns = matrix[0].length;
//        Integer[] prevHeights = new Integer[columns];
//        Arrays.fill(prevHeights, 0);
//        Integer area = 0;
//        for (int r = 0; r < rows; r++) {
//            for (int c = 0; c < columns; c++) {
//                if (matrix[r][c] == 0) {
//                    prevHeights[c] = 0;
//                } else {
//                    prevHeights[c] += 1;
//                }
//            }
//
//            Integer[] sortHeights = Arrays.copyOf(prevHeights, columns);
//            Arrays.sort(sortHeights, Collections.reverseOrder());
//            for (int c = 0; c < columns; c++) {
//                area = Math.max(area, sortHeights[c] * (c + 1));
//            }
//        }
//
//        return area;
    }
}
