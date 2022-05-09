package com.seanlindev.algorithms;

import java.util.List;

public class ConnectedCellsInAGrid {
    public static int connectedCell(List<List<Integer>> matrix) {
        int result = 0;
        boolean[][] selectedCells = new boolean[matrix.size()][matrix.get(0).size()];

        for (int row = 0; row < matrix.size(); row++) {
            for (int column = 0; column < matrix.get(row).size(); column++) {
                if (matrix.get(row).get(column) == 1) {
                    //find connected filled cells
                    if (selectedCells[row][column] == false) {
                        selectedCells[row][column] = true;
                        int count = 1 + findConnectedFilledCells(matrix, row, column, selectedCells);
                        result = Math.max(result, count);
                    }
                }
            }
        }

        return result;
    }

    public static int findConnectedFilledCells(List<List<Integer>> matrix, int row, int column, boolean[][] selectedCells) {
        int count = 0;
        //top
        if (row > 0) {
            if (matrix.get(row - 1).get(column) == 1) {
                if (selectedCells[row - 1][column] == false) {
                    selectedCells[row - 1][column] = true;
                    count++;
                    count += findConnectedFilledCells(matrix, row - 1, column, selectedCells);
                }
            }
        }

        //down
        if (row < matrix.size() - 1) {
            if (matrix.get(row + 1).get(column) == 1) {
                if (selectedCells[row + 1][column] == false) {
                    selectedCells[row + 1][column] = true;
                    count++;
                    count += findConnectedFilledCells(matrix, row + 1, column, selectedCells);
                }
            }
        }

        //left
        if (column > 0) {
            if (matrix.get(row).get(column - 1) == 1) {
                if (selectedCells[row][column - 1] == false) {
                    selectedCells[row][column - 1] = true;
                    count++;
                    count += findConnectedFilledCells(matrix, row, column - 1, selectedCells);
                }
            }
        }

        //right
        if (column < matrix.get(row).size() - 1) {
            if (matrix.get(row).get(column + 1) == 1) {
                if (selectedCells[row][column + 1] == false) {
                    selectedCells[row][column + 1] = true;
                    count++;
                    count += findConnectedFilledCells(matrix, row, column + 1, selectedCells);
                }
            }
        }

        //top-left
        if (row > 0 && column > 0) {
            if (matrix.get(row - 1).get(column - 1) == 1) {
                if (selectedCells[row - 1][column - 1] == false) {
                    selectedCells[row - 1][column - 1] = true;
                    count++;
                    count += findConnectedFilledCells(matrix, row - 1, column - 1, selectedCells);
                }
            }
        }

        //top-right
        if (row > 0 && column < matrix.get(row).size() - 1) {
            if (matrix.get(row - 1).get(column + 1) == 1) {
                if (selectedCells[row - 1][column + 1] == false) {
                    selectedCells[row - 1][column + 1] = true;
                    count++;
                    count += findConnectedFilledCells(matrix, row - 1, column + 1, selectedCells);
                }
            }
        }

        //down-left
        if (row < matrix.size() - 1 && column > 0) {
            if (matrix.get(row + 1).get(column - 1) == 1) {
                if (selectedCells[row + 1][column - 1] == false) {
                    selectedCells[row + 1][column - 1] = true;
                    count++;
                    count += findConnectedFilledCells(matrix, row + 1, column - 1, selectedCells);
                }
            }
        }

        //down-right
        if (row < matrix.size() - 1 && column < matrix.get(row).size() - 1) {
            if (matrix.get(row + 1).get(column + 1) == 1) {
                if (selectedCells[row + 1][column + 1] == false) {
                    selectedCells[row + 1][column + 1] = true;
                    count++;
                    count += findConnectedFilledCells(matrix, row + 1, column + 1, selectedCells);
                }
            }
        }

        return count;
    }
}
