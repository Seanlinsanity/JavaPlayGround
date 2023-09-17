package com.seanlindev.algorithms;

/*
You are given a 2D matrix containing different characters, you need to find if there exists any cycle consisting of the same character in the matrix.

A cycle is a path in the matrix that starts and ends at the same cell and has four or more cells. From a given cell, you can move to one of the cells adjacent to it - in one of the four directions (up, down, left, or right), if it has the same character value of the current cell.

Write a function to find if the matrix has a cycle.

input: [["a", "a", "a", "a"], ["b", "a", "c", "a"], ["b", "a", "c", "a"], ["b", "a", "a", "a"]]
output: true

input: [["a", "a", "a", "a"], ["a", "b", "b", "a"], ["a", "b", "a", "a"], ["a", "a", "a", "c"]]
output: true

input: [["a", "b", "e", "b"], ["b", "b", "b", "b"], ["b", "c", "c", "d"], ["c", "c", "d", "d"]]
output: false
 */
public class FindCycleInMatrix {
    public static boolean hasCycle(char[][] matrix) {
        int rows = matrix.length;
        int columns = matrix[0].length;
        boolean[][] visited = new boolean[rows][columns];
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                if (visited[row][column]) { continue; }

                if (containsCycleDFS(matrix, visited, matrix[row][column], row, column, -1, -1)) { return true; }
            }
        }
        return false;
    }

    private static boolean containsCycleDFS(char[][] matrix, boolean[][] visited,
                                            char startChar, int x, int y, int prevX, int prevY) {
        if (x < 0 || x >= matrix.length || y < 0 || y >= matrix[0].length)
            return false;
        if (matrix[x][y] != startChar)
            return false;

        if (visited[x][y])
            return true; // found a cycle, as we are visiting an already visited valid cell

        visited[x][y] = true; // mark the cell visited

        if (x + 1 != prevX && containsCycleDFS(matrix, visited, startChar, x + 1, y, x, y)) // down
            return true;
        if (x - 1 != prevX && containsCycleDFS(matrix, visited, startChar, x - 1, y, x, y)) // up
            return true;
        if (y + 1 != prevY && containsCycleDFS(matrix, visited, startChar, x, y + 1, x, y)) // right
            return true;
        if (y - 1 != prevY && containsCycleDFS(matrix, visited, startChar, x, y - 1, x, y)) // left
            return true;

        return false;
    }
}
