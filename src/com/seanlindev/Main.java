package com.seanlindev;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        QuickSortSolution quickSortSolution = new QuickSortSolution();
        int[] numbers= {10, 30, 40, 50, 70, 90, 80, 9, 4, 1, 6, 7, 3, 8, 2, 5};
        System.out.println(Arrays.toString(quickSortSolution.quickSorting(numbers)));
    }
}
