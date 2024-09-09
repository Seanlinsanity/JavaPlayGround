package com.seanlindev.algorithms;

import java.util.PriorityQueue;

/*
You are given a sorted integer array arr containing 1 and prime numbers, where all the integers of arr are unique. You are also given an integer k.

For every i and j where 0 <= i < j < arr.length, we consider the fraction arr[i] / arr[j].

Return the kth smallest fraction considered. Return your answer as an array of integers of size 2, where answer[0] == arr[i] and answer[1] == arr[j].



Example 1:

Input: arr = [1,2,3,5], k = 3
Output: [2,5]
Explanation: The fractions to be considered in sorted order are:
1/5, 1/3, 2/5, 1/2, 3/5, and 2/3.
The third fraction is 2/5.
Example 2:

Input: arr = [1,7], k = 1
Output: [1,7]


Constraints:

2 <= arr.length <= 1000
1 <= arr[i] <= 3 * 104
arr[0] == 1
arr[i] is a prime number for i > 0.
All the numbers of arr are unique and sorted in strictly increasing order.
1 <= k <= arr.length * (arr.length - 1) / 2


Follow up: Can you solve the problem with better than O(n2) complexity?
 */
public class KthSmallestPrimeFraction {
    class Node {
        double val;
        int[] pair;

        Node(double val, int[] pair) {
            this.val = val;
            this.pair = pair;
        }
    }

    // [1,2,3,5]
    public int[] kthSmallestPrimeFraction(int[] arr, int k) {
        if (k == 1) {
            return new int[]{ arr[0], arr[arr.length - 1] };
        }

        PriorityQueue<Node> minHeap = new PriorityQueue<>((a, b) -> Double.compare(a.val, b.val));
        for (int i = 0; i < arr.length - 1; i++) { //i=0->1->2
            double fraction = (double)arr[i] / (double)arr[arr.length - 1];
            minHeap.add(
                    new Node(fraction, new int[] {i, arr.length - 1}) //minHeap=[1/5,1/3,1/2,2/5,2/3,3/5]
            );
        }

        int[] result = new int[2];
        for (int i = 0; i < k; i++) {
            Node node = minHeap.poll();
            int[] pair = node.pair;
            if (pair[0] < pair[1] - 1) {
                double fraction = (double)arr[pair[0]] / (double)arr[pair[1] - 1];
                minHeap.add(
                        new Node(fraction, new int[] {pair[0], pair[1] - 1}) //minHeap=[1/5,1/3,1/2,2/5,2/3,3/5]
                );
            }
            result[0] = arr[node.pair[0]];
            result[1] = arr[node.pair[1]];
        }

        return result;
    }
}
