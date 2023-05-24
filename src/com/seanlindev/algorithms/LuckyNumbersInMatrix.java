package com.seanlindev.algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
* Given an m x n matrix of distinct numbers, return all lucky numbers in the matrix in any order.

A lucky number is an element of the matrix such that it is the minimum element in its row and maximum in its column.



Example 1:

Input: matrix = [[3,7,8],[9,11,13],[15,16,17]]
Output: [15]
Explanation: 15 is the only lucky number since it is the minimum in its row and the maximum in its column.
Example 2:

Input: matrix = [[1,10,4,2],[9,3,8,7],[15,16,17,12]]
Output: [12]
Explanation: 12 is the only lucky number since it is the minimum in its row and the maximum in its column.
Example 3:

Input: matrix = [[7,8],[1,2]]
Output: [7]
Explanation: 7 is the only lucky number since it is the minimum in its row and the maximum in its column.


Constraints:

m == mat.length
n == mat[i].length
1 <= n, m <= 50
1 <= matrix[i][j] <= 105.
All elements in the matrix are distinct.
*
* */
public class LuckyNumbersInMatrix {
    public List<Integer> luckyNumbers (int[][] matrix) {
        final List<Integer> results = new ArrayList<>();

        for (int i = 0; i < matrix.length; i++) {
            int currentMin = 0;
            int minimumRowIndex = -1;
            for (int j = 0; j < matrix[i].length; j++) {
                if (minimumRowIndex < 0 || matrix[i][j] < currentMin) {
                    minimumRowIndex = j;
                    currentMin = matrix[i][j];
                }
            }

            boolean isMaximumInColumn = true;
            for (int k = 0; k < matrix.length; k++) {
                if (k == i) {
                    continue;
                }

                if (currentMin <= matrix[k][minimumRowIndex]) {
                    isMaximumInColumn = false;
                    break;
                }
            }

            if (isMaximumInColumn) {
                results.add(currentMin);
            }
        }

        return results;
    }

    public List<Integer> luckyNumbers2 (int[][] matrix) {
        List<Integer> luckyNumbers = new ArrayList<>();

        int rows = matrix.length;
        int columns = matrix[0].length;

        // Find the minimum element in each row
        int[] minRowElements = new int[rows];
        for (int i = 0; i < rows; i++) {
            int min = Integer.MAX_VALUE;
            for (int j = 0; j < columns; j++) {
                min = Math.min(min, matrix[i][j]);
            }
            minRowElements[i] = min;
        }

        // Find the maximum element in each column
        int[] maxColumnElements = new int[columns];
        for (int j = 0; j < columns; j++) {
            int max = Integer.MIN_VALUE;
            for (int i = 0; i < rows; i++) {
                max = Math.max(max, matrix[i][j]);
            }
            maxColumnElements[j] = max;
        }

        // Check if the minimum element in a row is also the maximum element in its column
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (matrix[i][j] == minRowElements[i] && matrix[i][j] == maxColumnElements[j]) {
                    luckyNumbers.add(matrix[i][j]);
                }
            }
        }

        return luckyNumbers;
    }
}
