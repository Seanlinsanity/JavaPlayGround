package com.seanlindev.algorithms;

import java.util.LinkedList;
import java.util.Queue;

/*
Given an n x n binary matrix grid, return the length of the shortest clear path in the matrix. If there is no clear path, return -1.

A clear path in a binary matrix is a path from the top-left cell (i.e., (0, 0)) to the bottom-right cell (i.e., (n - 1, n - 1)) such that:

All the visited cells of the path are 0.
All the adjacent cells of the path are 8-directionally connected (i.e., they are different and they share an edge or a corner).
The length of a clear path is the number of visited cells of this path.



Example 1:


Input: grid = [[0,1],[1,0]]
Output: 2
Example 2:


Input: grid = [[0,0,0],[1,1,0],[1,1,0]]
Output: 4
Example 3:

Input: grid = [[1,0,0],[1,1,0],[1,1,0]]
Output: -1


Constraints:

n == grid.length
n == grid[i].length
1 <= n <= 100
grid[i][j] is 0 or 1
 */
public class ShortestPathInBinaryMatrix {
    public int shortestPathBinaryMatrix(int[][] grid) {
        int n = grid.length;

        // edge case
        if (grid[0][0] != 0 || grid[n - 1][n - 1] != 0) { return -1; }

        int[][] direct = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}, {1, 1}, {-1, -1}, {1, -1}, {-1, 1}};
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0, 0});
        int pathLength = 0;
        while (queue.size() > 0) {
            pathLength += 1;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] node = queue.remove();
                int row = node[0];
                int column = node[1];
                if (row == n - 1 && column == n - 1) {
                    return pathLength;
                }

                grid[row][column] = 2;
                for (int[] dir: direct) {
                    int nRow = row + dir[0];
                    int nColumn = column + dir[1];
                    if (nRow < 0 || nRow >= n || nColumn < 0 || nColumn >= n || grid[nRow][nColumn] != 0) {
                        continue;
                    }

                    grid[nRow][nColumn] = 2;
                    queue.add(new int[]{nRow, nColumn});
                }
            }
        }

        return -1;
    }
}
