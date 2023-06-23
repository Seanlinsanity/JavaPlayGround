package com.seanlindev.algorithms;

import java.util.LinkedList;
import java.util.Queue;

/*
You are given an m x n grid where each cell can have one of three values:

0 representing an empty cell,
1 representing a fresh orange, or
2 representing a rotten orange.
Every minute, any fresh orange that is 4-directionally adjacent to a rotten orange becomes rotten.

Return the minimum number of minutes that must elapse until no cell has a fresh orange. If this is impossible, return -1.



Example 1:


Input: grid = [[2,1,1],[1,1,0],[0,1,1]]
Output: 4
Example 2:

Input: grid = [[2,1,1],[0,1,1],[1,0,1]]
Output: -1
Explanation: The orange in the bottom left corner (row 2, column 0) is never rotten, because rotting only happens 4-directionally.
Example 3:

Input: grid = [[0,2]]
Output: 0
Explanation: Since there are already no fresh oranges at minute 0, the answer is just 0.


Constraints:

m == grid.length
n == grid[i].length
1 <= m, n <= 10
grid[i][j] is 0, 1, or 2.
 */
public class RottingOranges {
    public int orangesRotting(int[][] grid) {
        int rows = grid.length;
        int columns = grid[0].length;
        Queue<int[]> queue = new LinkedList<>();
        int freshCount = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (grid[i][j] == 2) {
                    queue.add(new int[]{i, j});
                } else if (grid[i][j] == 1) {
                    freshCount += 1;
                }
            }
        }

        if (freshCount == 0) { return 0; }

        /*
        [2,2,2],
        [2,2,0],
        [0,2,2]
        freshCount=6
        queue= [[0,0]]
        */
        int minute = 0;
        int rottingCount = 0;
        while (queue.size() > 0) { //[[2,2]]
            int size = queue.size(); //2
            for (int i = 0; i < size; i++) {
                int[] rottingOrange = queue.remove(); //[2,1]
                int row = rottingOrange[0];
                int column = rottingOrange[1];
                int[][] next = {
                        {row - 1, column},
                        {row + 1, column},
                        {row, column - 1},
                        {row, column + 1}
                };
                for (int[] position: next) {
                    if (position[0] < 0 || position[0] >= rows || position[1] < 0 || position[1] >= columns) {
                        continue;
                    }

                    if (grid[position[0]][position[1]] != 1) {
                        continue;
                    }

                    rottingCount += 1; //5
                    grid[position[0]][position[1]] = 2;
                    queue.add(position);
                }
            }

            if (queue.size() > 0) {
                minute += 1;
            }
        }

        return rottingCount == freshCount ? minute : -1;
    }
}
