package com.seanlindev;

import com.seanlindev.algorithms.MergeSortSolution;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        MergeSortSolution mergeSortSolution = new MergeSortSolution();
        int[] numbers= {30, 10, 50, 40, 70, 90, 80, 9, 4, 1, 6, 7, 3, 8, 2, 5};
        System.out.println(Arrays.toString(mergeSortSolution.mergeSorting(numbers)));
    }
}
