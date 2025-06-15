package com.seanlindev.algorithms;

/*
You are given an m x n binary matrix grid.

A row or column is considered palindromic if its values read the same forward and backward.

You can flip any number of cells in grid from 0 to 1, or from 1 to 0.

Return the minimum number of cells that need to be flipped to make all rows and columns palindromic, and the total number of 1's in grid divisible by 4.



Example 1:

Input: grid = [[1,0,0],[0,1,0],[0,0,1]]

Output: 3

Explanation:



Example 2:

Input: grid = [[0,1],[0,1],[0,0]]

Output: 2

Explanation:



Example 3:

Input: grid = [[1],[1]]

Output: 2

Explanation:





Constraints:

m == grid.length
n == grid[i].length
1 <= m * n <= 2 * 105
0 <= grid[i][j] <= 1
 */
public class MinimumNumberOfFlipsToMakeBinaryGridPalindromicII {
    public int minFlips(int[][] grid) {
        int flips = 0, totalOnes = 0, diff = 0;
        int rows = grid.length, cols = grid[0].length;

        // Handle quadrants
        for (int i = 0; i < rows / 2; ++i) {
            for (int j = 0; j < cols / 2; ++j) {
                int onesCount = grid[i][j] + grid[i][cols - 1 - j] + grid[rows - 1 - i][j] + grid[rows - 1 - i][cols - 1 - j];
                flips += Math.min(onesCount, 4 - onesCount);
            }
        }

        // Handle middle column
        if (cols % 2 > 0) {
            for (int i = 0; i < rows / 2; ++i) {
                // Check if the cells are different
                if (grid[i][cols / 2] != grid[rows - 1 - i][cols / 2]) {
                    diff++;
                }
                totalOnes += grid[i][cols / 2] + grid[rows - 1 - i][cols / 2];
            }
        }

        // Handle middle row
        if (rows % 2 > 0) {
            for (int j = 0; j < cols / 2; ++j) {
                // Check if the cells are different
                if (grid[rows / 2][j] != grid[rows / 2][cols - 1 - j]) {
                    diff++;
                }
                totalOnes += grid[rows / 2][j] + grid[rows / 2][cols - 1 - j];
            }
        }

        // Handle center point
        if (cols % 2 > 0 && rows % 2 > 0) {
            flips += grid[rows / 2][cols / 2];
        }

        // Ensure total number of 1's is divisible by 4
        if (diff == 0 && totalOnes % 4 > 0) {
            flips += 2;
        }

        return (flips + diff);
    }
}
