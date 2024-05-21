package com.seanlindev.algorithms;

import java.util.LinkedList;
import java.util.Queue;

/*
Given an m x n binary matrix mat, return the distance of the nearest 0 for each cell.

The distance between two adjacent cells is 1.



Example 1:


Input: mat = [[0,0,0],[0,1,0],[0,0,0]]
Output: [[0,0,0],[0,1,0],[0,0,0]]
Example 2:


Input: mat = [[0,0,0],[0,1,0],[1,1,1]]
Output: [[0,0,0],[0,1,0],[1,2,1]]


Constraints:

m == mat.length
n == mat[i].length
1 <= m, n <= 104
1 <= m * n <= 104
mat[i][j] is either 0 or 1.
There is at least one 0 in mat.
 */
public class Update01Matrix {
    public int[][] updateMatrix(int[][] mat) {
        int rows = mat.length;
        int columns = mat[0].length;
        Queue<int[]> queue = new LinkedList<>();
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                if (mat[row][column] == 0) {
                    queue.add(new int[]{row, column});
                } else {
                    mat[row][column] = Integer.MAX_VALUE;
                }
            }
        }

        while (queue.size() > 0) {
            int[] current = queue.remove();
            int row = current[0];
            int column = current[1];
            int[][] neighbors = new int[][]{
                    {row - 1, column},
                    {row + 1, column},
                    {row, column + 1},
                    {row, column - 1}
            };

            for (int[] neighbor: neighbors) {
                int r = neighbor[0];
                int c = neighbor[1];
                if (r >= rows || r < 0 || c >= columns || c < 0) { continue; }
                if (mat[r][c] < mat[row][column] + 1) { continue; }

                mat[r][c] = mat[row][column] + 1;
                queue.add(new int[]{r, c});
            }
        }

        return mat;
    }
}
