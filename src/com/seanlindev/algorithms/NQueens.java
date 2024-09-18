package com.seanlindev.algorithms;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
The n-queens puzzle is the problem of placing n queens on an n x n chessboard such that no two queens attack each other.

Given an integer n, return all distinct solutions to the n-queens puzzle. You may return the answer in any order.

Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a queen and an empty space, respectively.



Example 1:


Input: n = 4
Output: [[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
Explanation: There exist two distinct solutions to the 4-queens puzzle as shown above
Example 2:

Input: n = 1
Output: [["Q"]]


Constraints:

1 <= n <= 9
 */
public class NQueens {
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<>();
        boolean[] visitedColumn = new boolean[n];
        Set<Integer> postiveDiagSet = new HashSet<>();
        Set<Integer> negativeDiagSet = new HashSet<>();
        solveNQueensTracking(0, n, new ArrayList<>(), visitedColumn, postiveDiagSet, negativeDiagSet, result);
        return result;
    }

    void solveNQueensTracking(
            int row,
            int n,
            List<String> board,
            boolean[] visitedColumn,
            Set<Integer> postiveDiagSet,
            Set<Integer> negativeDiagSet,
            List<List<String>> result
    ) {
        if (row == n) {
            result.add(new ArrayList<>(board));
            return;
        }

        for (int column = 0; column < n; column++) {
            if (isValidBoard(column, row, visitedColumn, postiveDiagSet, negativeDiagSet)) {
                String newRow = generateRowString(column, n);
                board.add(newRow);
                visitedColumn[column] = true;
                postiveDiagSet.add(column - row);
                negativeDiagSet.add(column + row);
                solveNQueensTracking(row + 1, n, board, visitedColumn, postiveDiagSet, negativeDiagSet, result);
                visitedColumn[column] = false;
                postiveDiagSet.remove(column - row);
                negativeDiagSet.remove(column + row);
                board.remove(board.size() - 1);
            }
        }
    }

    String generateRowString(int column, int n) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if (i == column) {
                builder.append('Q');
            } else {
                builder.append('.');
            }
        }

        return builder.toString();
    }

    boolean isValidBoard(int column, int row, boolean[] visitedColumn, Set<Integer> postiveDiagSet, Set<Integer> negativeDiagSet) {
        // check vertical
        if (visitedColumn[column]) { return false; }

        // check diag
        int postiveDiag = column - row;
        if (postiveDiagSet.contains(postiveDiag)) {
            return false;
        }

        int negativeDiag = column + row;
        if (negativeDiagSet.contains(negativeDiag)) {
            return false;
        }

        return true;
    }
}
