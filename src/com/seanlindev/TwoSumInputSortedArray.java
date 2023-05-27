package com.seanlindev.algorithms;

public class TwoSumInputSortedArray {
    public int[] twoSum(int[] numbers, int target) {
        int leftIndex = 0;
        int rightIndex = numbers.length - 1;

        while (rightIndex > leftIndex) {
            int leftIndexNumber = numbers[leftIndex];
            int rightIndexNumber = numbers[rightIndex];
            int sum = leftIndexNumber + rightIndexNumber;
            if (sum == target) {
                return new int[] {leftIndex + 1, rightIndex + 1};
            } else if (sum > target) {
                rightIndex -= 1;
            } else {
                leftIndex += 1;
            }
        }

        return null;

    }
}
