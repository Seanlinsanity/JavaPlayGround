package com.seanlindev.algorithms;

import java.util.HashSet;
import java.util.Set;

/*
Determine if a 9 x 9 Sudoku board is valid. Only the filled cells need to be validated according to the following rules:

Each row must contain the digits 1-9 without repetition.
Each column must contain the digits 1-9 without repetition.
Each of the nine 3 x 3 sub-boxes of the grid must contain the digits 1-9 without repetition.
Note:

A Sudoku board (partially filled) could be valid but is not necessarily solvable.
Only the filled cells need to be validated according to the mentioned rules.


Example 1:


Input: board =
[["5","3",".",".","7",".",".",".","."]
,["6",".",".","1","9","5",".",".","."]
,[".","9","8",".",".",".",".","6","."]
,["8",".",".",".","6",".",".",".","3"]
,["4",".",".","8",".","3",".",".","1"]
,["7",".",".",".","2",".",".",".","6"]
,[".","6",".",".",".",".","2","8","."]
,[".",".",".","4","1","9",".",".","5"]
,[".",".",".",".","8",".",".","7","9"]]
Output: true
Example 2:

Input: board =
[["8","3",".",".","7",".",".",".","."]
,["6",".",".","1","9","5",".",".","."]
,[".","9","8",".",".",".",".","6","."]
,["8",".",".",".","6",".",".",".","3"]
,["4",".",".","8",".","3",".",".","1"]
,["7",".",".",".","2",".",".",".","6"]
,[".","6",".",".",".",".","2","8","."]
,[".",".",".","4","1","9",".",".","5"]
,[".",".",".",".","8",".",".","7","9"]]
Output: false
Explanation: Same as Example 1, except with the 5 in the top left corner being modified to 8. Since there are two 8's in the top left 3x3 sub-box, it is invalid.


Constraints:

board.length == 9
board[i].length == 9
board[i][j] is a digit 1-9 or '.'.
 */
public class ValidSudoku {
    public boolean isValidSudoku(char[][] board) {
        int rows = board.length;
        int columns = board[0].length;


        for (int i = 0; i < rows; i++) {
            Set<Character> rowSet = new HashSet<>();
            Set<Character> columnSet = new HashSet<>();
            for (int j = 0; j < columns; j++) {
                char rowCharacter = board[i][j];
                if (rowCharacter != '.') {
                    if (rowSet.contains(rowCharacter)) {
                        return false;
                    }
                    rowSet.add(rowCharacter);
                }

                char columnCharacter = board[j][i];
                if (columnCharacter != '.') {
                    if (columnSet.contains(columnCharacter)) {
                        return false;
                    }
                    columnSet.add(columnCharacter);
                }
            }
        }

        int[][] startPoints = {
                {0,0},{0,3},{0,6},
                {3,0},{3,3},{3,6},
                {6,0},{6,3},{6,6}
        };

        for (int[] point: startPoints) {
            Set<Character> numSet = new HashSet<>();
            for (int i = point[0]; i < point[0] + 3; i++){
                for (int j = point[1]; j < point[1] + 3; j++) {
                    char character = board[i][j];
                    if (character != '.') {
                        if (numSet.contains(character)) {
                            return false;
                        }
                        numSet.add(character);
                    }
                }
            }
        }

        return true;
    }
}
