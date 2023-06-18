package com.seanlindev.algorithms;


/*
Given an m x n matrix board containing 'X' and 'O', capture all regions that are 4-directionally surrounded by 'X'.

A region is captured by flipping all 'O's into 'X's in that surrounded region.



Example 1:


Input: board = [["X","X","X","X"],["X","O","O","X"],["X","X","O","X"],["X","O","X","X"]]
Output: [["X","X","X","X"],["X","X","X","X"],["X","X","X","X"],["X","O","X","X"]]
Explanation: Notice that an 'O' should not be flipped if:
- It is on the border, or
- It is adjacent to an 'O' that should not be flipped.
The bottom 'O' is on the border, so it is not flipped.
The other three 'O' form a surrounded region, so they are flipped.
Example 2:

Input: board = [["X"]]
Output: [["X"]]


Constraints:

m == board.length
n == board[i].length
1 <= m, n <= 200
board[i][j] is 'X' or 'O'.
 */
public class SurroundedRegions {
     public void solve(char[][] board) {
         int nRows = board.length;
         int nCols = board[0].length;

         // 1a) Capture unsurrounded regions - top and bottom row (O -> T)
         for (int i = 0; i < nCols; i++) {
             if (board[0][i] == 'O') dfs(board, 0, i);
             if (board[nRows - 1][i] == 'O') dfs(board, nRows - 1, i);
         }

         // 1b) Capture unsurrounded regions - Left and right columns (O -> T)
         for (int i = 0; i < nRows; i++) {
             if (board[i][0] == 'O') dfs(board, i, 0);
             if (board[i][nCols - 1] == 'O') dfs(board, i, nCols - 1);
         }

         for (int r = 0; r < nRows; r++) {
             for (int c = 0; c < nCols; c++) {
                 if (board[r][c] == 'O') board[r][c] = 'X'; // 2) Capture surrounded regions (O -> X)
                 if (board[r][c] == 'T') board[r][c] = 'O'; // 3) Uncapture unsurrounded regions (T- O)
             }
         }
     }

     private void dfs(char[][] board, int r, int c) {
         int nRows = board.length;
         int nCols = board[0].length;
         if (
             r < 0 || c < 0 || r >= nRows || c >= nCols || board[r][c] != 'O'
         ) return;

         board[r][c] = 'T';
         dfs(board, r + 1, c);
         dfs(board, r - 1, c);
         dfs(board, r, c + 1);
         dfs(board, r, c - 1);
     }
}
