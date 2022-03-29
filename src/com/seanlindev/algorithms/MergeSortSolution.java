package com.seanlindev.algorithms;

import java.util.Arrays;

public class MergeSortSolution {
    public int[] mergeSorting(int[] numbers) {
        if (numbers.length < 2) {
            return numbers;
        }

        final int[] leftNumbers = Arrays.copyOfRange(numbers, 0, numbers.length/2);
        final int[] rightNumbers = Arrays.copyOfRange(numbers, numbers.length/2, numbers.length);

        return merge(mergeSorting(leftNumbers), mergeSorting(rightNumbers));
    }

    private int[] merge(int[] leftNumbers, int[] rightNumbers) {
        int[] result = new int[leftNumbers.length + rightNumbers.length];
        int currentIndex = 0;
        while (leftNumbers.length > 0 && rightNumbers.length > 0) {
            if (leftNumbers[0] < rightNumbers[0]) {
                result[currentIndex] = leftNumbers[0];
                int[] temp = new int[leftNumbers.length - 1];
                System.arraycopy(leftNumbers,  1, temp, 0, leftNumbers.length - 1);
                leftNumbers = temp;
            } else {
                result[currentIndex] = rightNumbers[0];
                int[] temp = new int[rightNumbers.length - 1];
                System.arraycopy(rightNumbers,  1, temp, 0, rightNumbers.length - 1);
                rightNumbers = temp;
            }
            currentIndex += 1;
        }

        if (leftNumbers.length > 0) {
            System.arraycopy(leftNumbers,  0, result, currentIndex, leftNumbers.length);
        } else if (rightNumbers.length > 0) {
            System.arraycopy(rightNumbers,  0, result, currentIndex, rightNumbers.length);
        }

        return result;
    }
}