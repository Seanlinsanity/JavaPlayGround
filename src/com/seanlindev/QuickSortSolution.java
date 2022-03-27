package com.seanlindev;

import java.util.Arrays;

public class QuickSortSolution {
    public int[] quickSorting(int[] numbers) {

        if (numbers.length <= 1) {
            return numbers;
        }

        final int pivotIndex = numbers.length - 1;
        int leftIndex = 0;
        int rightIndex = numbers.length - 2;

        while (leftIndex <= rightIndex) {
            if (numbers[leftIndex] > numbers[pivotIndex] && numbers[rightIndex] < numbers[pivotIndex]) {
                int temp = numbers[rightIndex];
                numbers[rightIndex] = numbers[leftIndex];
                numbers[leftIndex] = temp;
            } else if (numbers[leftIndex] < numbers[pivotIndex]) {
                leftIndex += 1;
            } else if (numbers[rightIndex] > numbers[pivotIndex]) {
                rightIndex -= 1;
            }
        }

        int pivotNumber = numbers[pivotIndex];
        numbers[pivotIndex] = numbers[leftIndex];
        numbers[leftIndex] = pivotNumber;

        System.out.println("left Numbers: " + Arrays.toString(Arrays.copyOfRange(numbers, 0, leftIndex)));
        int[] leftNumbers = quickSorting(Arrays.copyOfRange(numbers, 0, leftIndex));
        System.out.println("right Numbers: " + Arrays.toString(Arrays.copyOfRange(numbers, leftIndex + 1, numbers.length)));
        int[] rightNumbers = quickSorting(Arrays.copyOfRange(numbers, leftIndex + 1, numbers.length));

        int[] leftResults = new int[leftNumbers.length + 1];
        System.arraycopy(leftNumbers, 0, leftResults, 0, leftNumbers.length);
        leftResults[leftNumbers.length] = pivotNumber;

        int[] results = new int[numbers.length];
        System.arraycopy(leftResults, 0, results, 0, leftResults.length);
        System.arraycopy(rightNumbers, 0, results, leftResults.length, rightNumbers.length);

        return results;
    }
}
