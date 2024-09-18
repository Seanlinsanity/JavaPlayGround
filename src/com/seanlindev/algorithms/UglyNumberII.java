package com.seanlindev.algorithms;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/*
An ugly number is a positive integer whose prime factors are limited to 2, 3, and 5.

Given an integer n, return the nth ugly number.



Example 1:

Input: n = 10
Output: 12
Explanation: [1, 2, 3, 4, 5, 6, 8, 9, 10, 12] is the sequence of the first 10 ugly numbers.
Example 2:

Input: n = 1
Output: 1
Explanation: 1 has no prime factors, therefore all of its prime factors are limited to 2, 3, and 5.


Constraints:


 */
public class UglyNumberII {
    public int nthUglyNumber(int n) {
        int[] primeFactors = new int[]{2, 3, 5};
        Set<Long> numSet = new HashSet<>();
        PriorityQueue<Long> minHeap = new PriorityQueue<>();
        minHeap.add(1L);
        numSet.add(1L);
        for (int i = 0; i < n; i++) {
            long pollVal = minHeap.poll();
            if (i == n - 1) {
                return (int)pollVal;
            }

            for (int prime: primeFactors) {
                long newVal = pollVal * (long)prime;
                if (numSet.contains(newVal)) {
                    continue;
                }

                minHeap.add(newVal);
                numSet.add(newVal);
            }
        }

        return 0;
    }
}
