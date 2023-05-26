package com.seanlindev.algorithms;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
Given an m x n 2D binary grid which represents a map of '1's (land) and '0's (water), return the number of islands.

An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.



Example 1:

Input: grid = [
  ["1","1","1","1","0"],
  ["1","1","0","1","0"],
  ["1","1","0","0","0"],
  ["0","0","0","0","0"]
]
Output: 1
Example 2:

Input: grid = [
  ["1","1","0","0","0"],
  ["1","1","0","0","0"],
  ["0","0","1","0","0"],
  ["0","0","0","1","1"]
]
Output: 3


Constraints:

m == grid.length
n == grid[i].length
1 <= m, n <= 300
grid[i][j] is '0' or '1'.
 */
public class NumberOfIslands {
    public int numIslands(char[][] grid) {
        if (grid.length == 0) { return 0; }
        Set<String> visitedSet = new HashSet<>();
        int result = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                char value = grid[i][j];
                String position = String.format("%d-%d", i, j);
                if (value == '1' && !visitedSet.contains(position)) {
                    bfs(i, j, visitedSet, grid);
                    result += 1;
                }
            }
        }

        return result;
    }

    private void bfs(int row, int column, Set<String> visitedSet, char[][] grid) {
        List<int[]> searchList = new ArrayList<>();
        int[] start = {row, column};
        searchList.add(start);
        visitedSet.add(String.format("%d-%d", start[0], start[1]));

        while (searchList.size() > 0) {
            int[] position = searchList.get(0);
            searchList.remove(0);
            int[] top = {position[0] - 1, position[1]};
            int[] left = {position[0], position[1] - 1};
            int[] down = {position[0] + 1, position[1]};
            int[] right = {position[0], position[1] + 1};
            int[][] nextList = {top, left, down, right};
            for (int[] nextPosition: nextList) {
                String nextPositionStr = String.format("%d-%d", nextPosition[0], nextPosition[1]);
                if (
                        nextPosition[0] >= 0 &&
                                nextPosition[0] < grid.length &&
                                nextPosition[1] >= 0 &&
                                nextPosition[1] < grid[0].length &&
                                grid[nextPosition[0]][nextPosition[1]] == '1' &&
                                !visitedSet.contains(nextPositionStr)
                ) {
                    visitedSet.add(nextPositionStr);
                    searchList.add(nextPosition);
                }
            }
        }
    }
}
