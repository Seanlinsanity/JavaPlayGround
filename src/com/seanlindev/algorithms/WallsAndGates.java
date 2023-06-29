package com.seanlindev.algorithms;

import java.util.LinkedList;
import java.util.Queue;

/*
You are given a m x n 2D grid initialized with these three possible values.

-1 - A wall or an obstacle.
0 - A gate.
INF - Infinity means an empty room. We use the value 2^31 - 1 = 2147483647 to represent INF as you may assume that the distance to a gate is less than 2147483647.
Fill each empty room with the distance to its nearest gate. If it is impossible to reach a Gate, that room should remain filled with INF

Example
Example1

Input:
[[2147483647,-1,0,2147483647],[2147483647,2147483647,2147483647,-1],[2147483647,-1,2147483647,-1],[0,-1,2147483647,2147483647]]
Output:
[[3,-1,0,1],[2,2,1,-1],[1,-1,2,-1],[0,-1,3,4]]

Explanation:
the 2D grid is:
INF  -1  0  INF
INF INF INF  -1
INF  -1 INF  -1
  0  -1 INF INF
the answer is:
  3  -1   0   1
  2   2   1  -1
  1  -1   2  -1
  0  -1   3   4
Example2

Input:
[[0,-1],[2147483647,2147483647]]
Output:
[[0,-1],[1,2]]
 */
public class WallsAndGates {
    public void wallsAndGates(int[][] rooms) {
        int rows = rooms.length;
        int columns = rooms[0].length;
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (rooms[i][j] == 0) {
                    queue.add(new int[]{i, j});
                }
            }
        }

        bfs(queue, rooms);
    }

    public void bfs(Queue<int[]> queue, int[][] rooms) {
        int rows = rooms.length;
        int columns = rooms[0].length;

        boolean[][] visited = new boolean[rows][columns];
        int count = -1;
        while (queue.size() > 0) {
            int size = queue.size();
            if (count == -1) {
                count = 0;
            } else {
                count += 1;
            }

            for (int i = 0; i < size; i++) {
                int[] position = queue.remove();
                int r = position[0];
                int c = position[1];

                if (r < 0 || r >= rows || c < 0 || c >= columns) {
                    continue;
                }

                if (visited[r][c]) { continue; }

                visited[r][c] = true;

                if (rooms[r][c] == -1) {
                    continue;
                }

                if (rooms[r][c] == 2147483647) {
                    rooms[r][c] = count;
                }

                int[][] nextList = {
                    {r - 1, c},
                    {r + 1, c},
                    {r, c - 1},
                    {r, c + 1}
                };

                for (int[] next: nextList) {
                    queue.add(next);
                }
            }
        }
    }
}
