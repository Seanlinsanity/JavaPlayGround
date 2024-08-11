package com.seanlindev.algorithms;

import java.util.PriorityQueue;

/*
Given an n x n matrix where each of the rows and columns is sorted in ascending order, return the kth smallest element in the matrix.

Note that it is the kth smallest element in the sorted order, not the kth distinct element.

You must find a solution with a memory complexity better than O(n2).



Example 1:

Input: matrix = [[1,5,9],[10,11,13],[12,13,15]], k = 8
Output: 13
Explanation: The elements in the matrix are [1,5,9,10,11,12,13,13,15], and the 8th smallest number is 13
Example 2:

Input: matrix = [[-5]], k = 1
Output: -5


Constraints:

n == matrix.length == matrix[i].length
1 <= n <= 300
-109 <= matrix[i][j] <= 109
All the rows and columns of matrix are guaranteed to be sorted in non-decreasing order.
1 <= k <= n2


Follow up:

Could you solve the problem with a constant memory (i.e., O(1) memory complexity)?
Could you solve the problem in O(n) time complexity? The solution may be too advanced for an interview but you may find reading this paper fun.
 */
public class KthSmallestElementInSortedMatrix {
    public int kthSmallest(int[][] matrix, int k) {
        // [value, row, column]
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));

        int n = matrix.length;
        for (int r = 0; r < n; r++) {
            minHeap.add(new int[]{ matrix[r][0], r, 0 });
        }

        int count = 0;
        int result = Integer.MIN_VALUE;
        while (minHeap.size() > 0) {
            int[] pollNode = minHeap.poll();
            result = pollNode[0];
            count += 1;
            if (count == k) { return result; }

            int row = pollNode[1];
            int column = pollNode[2];
            if (column < n - 1) {
                minHeap.add(new int[]{ matrix[row][column + 1], row, column + 1 });
            }
        }

        return result;

        // for (int r = 0; r < n; r++) {
        //     for (int c = 0; c < n; c++) {
        //         if (maxHeap.size() < k) {
        //             maxHeap.add(matrix[r][c]);
        //         } else if (maxHeap.peek() > matrix[r][c]) {
        //             maxHeap.poll();
        //             maxHeap.add(matrix[r][c]);
        //         }
        //     }
        // }

        // return maxHeap.peek();
    }
}
