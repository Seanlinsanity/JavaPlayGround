package com.seanlindev.algorithms;

/*
Given an m x n grid of characters board and a string word, return true if word exists in the grid.

The word can be constructed from letters of sequentially adjacent cells, where adjacent cells are horizontally or vertically neighboring. The same letter cell may not be used more than once.



Example 1:


Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
Output: true
Example 2:


Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "SEE"
Output: true
Example 3:


Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCB"
Output: false


Constraints:

m == board.length
n = board[i].length
1 <= m, n <= 6
1 <= word.length <= 15
board and word consists of only lowercase and uppercase English letters.
 */
public class WordSearch {
    public boolean exist(char[][] board, String word) {
        int rows = board.length;
        int columns = board[0].length;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                boolean[][] visited = new boolean[rows][columns];
                boolean result = dfs(i, j, board, word, visited, 0);
                if (result) { return true; }
            }
        }

        return false;
    }

    public boolean dfs(
            int row,
            int column,
            char[][] board,
            String word,
            boolean[][] visited,
            int depth
    ) {
        int rows = board.length;
        int columns = board[0].length;
        if (row < 0 || row >= rows || column < 0 || column >= columns) { return false; }
        if (visited[row][column]) { return false; }

        char current = board[row][column];
        if (current != word.charAt(depth)) { return false; }
        if (depth == word.length() - 1) { return true; }
        visited[row][column] = true;
        boolean result = (
                dfs(row, column - 1, board, word, visited, depth + 1) ||
                dfs(row, column + 1, board, word, visited, depth + 1) ||
                dfs(row - 1, column, board, word, visited, depth + 1) ||
                dfs(row + 1, column, board, word, visited, depth + 1)
        );
        visited[row][column] = false;
        return result;
    }
}
