package com.seanlindev.algorithms;

/*
Given an integer array arr, partition the array into (contiguous) subarrays of length at most k. After partitioning, each subarray has their values changed to become the maximum value of that subarray.

Return the largest sum of the given array after partitioning. Test cases are generated so that the answer fits in a 32-bit integer.



Example 1:

Input: arr = [1,15,7,9,2,5,10], k = 3
Output: 84
Explanation: arr becomes [15,15,15,9,10,10,10]
Example 2:

Input: arr = [1,4,1,5,7,3,6,1,9,9,3], k = 4
Output: 83
Example 3:

Input: arr = [1], k = 1
Output: 1


Constraints:

1 <= arr.length <= 500
0 <= arr[i] <= 109
1 <= k <= arr.length


arr = [1,15,7,9,2,5,10], k = 3
i=0 i=1     i=2             i=3
            [1,15,7]        [1,15,7],[9]
    [1,15]
                            [1,15],[7,9]
            [1,15],[7]
                            [1,15],[7],[9]
1
                            [1],[15,7,9]
            [1],[15,7]
                            [1],[15,7],[9]
    [1],[15]
                            [1],[15],[7,9]
            [1],[15],[7]
                            [1],[15],[7],[9]
 */
public class PartitionArrayMaximumSum {
    // arr=[1,15,7,9,2,5,10]
    public int maxSumAfterPartitioning(int[] arr, int k) {
        if (arr.length == 1) { return arr[0]; }

        int[] dp = new int[arr.length]; // dp=[0,0,0,0,0,0,0]

        for (int i = 0; i < arr.length; i++) { //i=0
            int maxElement = arr[i]; //maxElement=1
            // Check partitions of length 1 to k
            for (int j = i; j >= i - k + 1; j--) { //j=0
                if (j < 0) { break; }

                maxElement = Math.max(maxElement, arr[j]); // Update the maximum element
                int subMaxSum = j - 1 >= 0 ? dp[j - 1] : 0;
                dp[i] = Math.max(dp[i], subMaxSum + maxElement * (i - j + 1)); // Update the dp array with the maximum sum
            }
        }
        return dp[arr.length - 1]; // Return the maximum sum after partitioning

        // Integer[][] dp = new Integer[arr.length][k + 1];
        // return maxSumAfterPartitioningRecursion(arr, k, 1, 1, arr[0], dp);
    }

    int maxSumAfterPartitioningRecursion(int[] arr, int k, int index, int count, int max, Integer[][] dp) {
        if (index == arr.length) {
            return count > 0 ? max * count : 0;
        }

        if (dp[index][count] != null) { return dp[index][count]; }

        int num = arr[index];
        int result = 0;
        if (count < k) {
            // include
            int val1 = maxSumAfterPartitioningRecursion(arr, k, index + 1, count + 1, Math.max(num, max), dp);
            // exclude
            int val2 = max * count + maxSumAfterPartitioningRecursion(arr, k, index + 1, 1, num, dp);
            result = Math.max(val1, val2);
        } else {
            // exclude
            result = max * count + maxSumAfterPartitioningRecursion(arr, k, index + 1, 1, num, dp);
        }

        dp[index][count] = result;
        return result;
    }
}
