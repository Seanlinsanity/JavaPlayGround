package com.seanlindev.algorithms;

import java.util.LinkedList;
import java.util.Queue;

/*
Given an n x n grid containing only values 0 and 1, where 0 represents water and 1 represents land, find a water cell such that its distance to the nearest land cell is maximized, and return the distance. If no land or water exists in the grid, return -1.

The distance used in this problem is the Manhattan distance: the distance between two cells (x0, y0) and (x1, y1) is |x0 - x1| + |y0 - y1|.



Example 1:


Input: grid = [[1,0,1],[0,0,0],[1,0,1]]
Output: 2
Explanation: The cell (1, 1) is as far as possible from all the land with distance 2.
Example 2:


Input: grid = [[1,0,0],[0,0,0],[0,0,0]]
Output: 4
Explanation: The cell (2, 2) is as far as possible from all the land with distance 4.


Constraints:

n == grid.length
n == grid[i].length
1 <= n <= 100
grid[i][j] is 0 or 1
 */
public class AsFarFromLandAsPossible {
    public int maxDistance(int[][] grid) {
        int rows = grid.length;
        int columns = grid[0].length;
        Queue<int[]> queue = new LinkedList<>();
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                if (grid[r][c] == 1) {
                    queue.add(new int[]{ r, c });
                }
            }
        }

        if (queue.size() == 0 || queue.size() == rows * columns) { return -1; }

        int distance = 0;
        while (queue.size() > 0) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] node = queue.remove();
                int currR = node[0];
                int currC = node[1];
                distance = grid[currR][currC];

                int[][] neighbors = {
                        { currR - 1, currC },
                        { currR + 1, currC },
                        { currR, currC + 1 },
                        { currR, currC - 1 },
                };
                for (int[] neighbor: neighbors) {
                    int neighborR = neighbor[0];
                    int neighborC = neighbor[1];
                    if (neighborR < 0 || neighborR >= rows || neighborC < 0 || neighborC >= columns) {
                        continue;
                    }

                    if (grid[neighborR][neighborC] != 0) {
                        continue;
                    }
                    grid[neighborR][neighborC] = grid[currR][currC] + 1;
                    queue.add(neighbor);
                }
            }
        }

        return distance - 1;
    }
}
