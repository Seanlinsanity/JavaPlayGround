package com.seanlindev.algorithms;

import java.util.PriorityQueue;

/*
There is a dungeon with n x m rooms arranged as a grid.

You are given a 2D array moveTime of size n x m, where moveTime[i][j] represents the minimum time in seconds after which the room opens and can be moved to. You start from the room (0, 0) at time t = 0 and can move to an adjacent room. Moving between adjacent rooms takes exactly one second.

Return the minimum time to reach the room (n - 1, m - 1).

Two rooms are adjacent if they share a common wall, either horizontally or vertically.



Example 1:

Input: moveTime = [[0,4],[4,4]]

Output: 6

Explanation:

The minimum time required is 6 seconds.

At time t == 4, move from room (0, 0) to room (1, 0) in one second.
At time t == 5, move from room (1, 0) to room (1, 1) in one second.
Example 2:

Input: moveTime = [[0,0,0],[0,0,0]]

Output: 3

Explanation:

The minimum time required is 3 seconds.

At time t == 0, move from room (0, 0) to room (1, 0) in one second.
At time t == 1, move from room (1, 0) to room (1, 1) in one second.
At time t == 2, move from room (1, 1) to room (1, 2) in one second.
Example 3:

Input: moveTime = [[0,1],[1,2]]

Output: 3



Constraints:

2 <= n == moveTime.length <= 50
2 <= m == moveTime[i].length <= 50
0 <= moveTime[i][j] <= 109
 */
public class MinimumTimeToReachLastRoom {
    public int minTimeToReach(int[][] moveTime) {
        int rows = moveTime.length; //2
        int cols = moveTime[0].length; //2
        int[][] directions = new int[][]{
                { 1, 0 },
                { -1, 0 },
                { 0, 1 },
                { 0, -1 }
        };
        boolean[][] visited = new boolean[rows][cols];
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
        minHeap.add(new int[]{ 0, 0, 0 }); //minHeap={[0,0,0]}
        while (minHeap.size() > 0) { //minHeap={[0,0,0]}
            int[] poll = minHeap.poll();
            int time = poll[0];
            int r = poll[1];
            int c = poll[2];
            if (r == rows - 1 && c == cols - 1) { return time; }

            if (visited[r][c]) { continue; }

            visited[r][c] = true;
            for (int[] direct: directions) {
                int nR = r + direct[0];
                int nC = c + direct[1];
                if (nR < 0 || nR >= rows || nC < 0 || nC >= cols) { continue; }
                if (visited[nR][nC]) { continue; }
                if (time >= moveTime[nR][nC]) {
                    minHeap.add(new int[]{ time + 1, nR, nC });
                } else {
                    minHeap.add(new int[]{  moveTime[nR][nC] + 1, nR, nC });
                }
            }
        }

        return 0;
    }
}
