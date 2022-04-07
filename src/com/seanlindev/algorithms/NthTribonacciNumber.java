package com.seanlindev.algorithms;

public class NthTribonacciNumber {
    int[] nums;

    public int tribonacci(int n) {
        if (n == 0) { return 0; }

        if (n <= 2) { return 1; }

        if (nums == null) {
            nums = new int[n + 1];
            nums[0] = 0;
            nums[1] = 1;
            nums[2] = 2;
        } else if (nums[n] > 0) {
            return nums[n];
        }

        int num = tribonacci(n - 3) + tribonacci(n - 2) + tribonacci(n - 1);
        nums[n] = num;
        return num;
    }

}
