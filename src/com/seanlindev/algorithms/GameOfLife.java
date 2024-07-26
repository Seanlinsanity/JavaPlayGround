package com.seanlindev.algorithms;

/*
According to Wikipedia's article: "The Game of Life, also known simply as Life, is a cellular automaton devised by the British mathematician John Horton Conway in 1970."

The board is made up of an m x n grid of cells, where each cell has an initial state: live (represented by a 1) or dead (represented by a 0). Each cell interacts with its eight neighbors (horizontal, vertical, diagonal) using the following four rules (taken from the above Wikipedia article):

Any live cell with fewer than two live neighbors dies as if caused by under-population.
Any live cell with two or three live neighbors lives on to the next generation.
Any live cell with more than three live neighbors dies, as if by over-population.
Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
The next state is created by applying the above rules simultaneously to every cell in the current state, where births and deaths occur simultaneously. Given the current state of the m x n grid board, return the next state.



Example 1:


Input: board = [[0,1,0],[0,0,1],[1,1,1],[0,0,0]]
Output: [[0,0,0],[1,0,1],[0,1,1],[0,1,0]]
Example 2:


Input: board = [[1,1],[1,0]]
Output: [[1,1],[1,1]]


Constraints:

m == board.length
n == board[i].length
1 <= m, n <= 25
board[i][j] is 0 or 1.


Follow up:

Could you solve it in-place? Remember that the board needs to be updated simultaneously: You cannot update some cells first and then use their updated values to update other cells.
In this question, we represent the board using a 2D array. In principle, the board is infinite, which would cause problems when the active area encroaches upon the border of the array (i.e., live cells reach the border). How would you address these problems?
 */
public class GameOfLife {
    public void gameOfLife(int[][] board) {
        int rows = board.length;
        int columns = board[0].length;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                int[][] neighbors = {
                        {r - 1, c},
                        {r + 1, c},
                        {r, c - 1},
                        {r, c + 1},
                        {r - 1, c - 1},
                        {r - 1, c + 1},
                        {r + 1, c - 1},
                        {r + 1, c + 1}
                };
                int live = 0;
                for (int[] neighbor: neighbors) {
                    int neighborRow = neighbor[0];
                    int neighborColumn = neighbor[1];
                    if (neighborRow < 0 || neighborRow >= rows || neighborColumn < 0 || neighborColumn >= columns) {
                        continue;
                    }

                    if (board[neighborRow][neighborColumn] == 1 || board[neighborRow][neighborColumn] == 3) {
                        live += 1;
                    }
                }

                if (board[r][c] == 0 && live == 3) {
                    board[r][c] = 2;
                } else if (board[r][c] == 1) {
                    if (live < 2 || live > 3) {
                        board[r][c] = 3;
                    }
                }
            }
        }

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                if (board[r][c] == 2) {
                    board[r][c] = 1;
                } else if (board[r][c] == 3) {
                    board[r][c] = 0;
                }
            }
        }

    }
}
