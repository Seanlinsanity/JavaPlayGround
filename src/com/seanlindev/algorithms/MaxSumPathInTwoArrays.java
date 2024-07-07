package com.seanlindev.algorithms;

/*
Given two sorted arrays A and B of size M and N respectively. Each array may have some elements in common with the other array. Find the maximum sum of a path from the beginning of any array to the end of any of the two arrays. We can switch from one array to another array only at the common elements.Both the arrays are sorted.
Note: Only one repeated value is considered in the valid path sum.


Example 1:

Input:
M = 5, N = 4
A[] = {2,3,7,10,12}
B[] = {1,5,7,8}
Output: 35
Explanation: The path will be 1+5+7+10+12
= 35.

Example 2:

Input:
M = 3, N = 3
A[] = {1,2,3}
B[] = {3,4,5}
Output: 15
Explanation: The path will be 1+2+3+4+5=15.

Your Task:
You don't need to read input or print anything. Complete the function max_path_sum() which takes the two arrays A and B along with their sizes M and N as input parameters. It returns the maximum path sum.


Expected Time Complexity: O(M + N)
Expected Auxiliary Space: O(1)


Constraints:
1 <= M,N <= 104
1 <= A[i], B[i] <= 104
 */
public class MaxSumPathInTwoArrays {
    int maxPathSum(int ar1[], int ar2[])
    {
        int m = ar1.length, n = ar2.length;
        // initialize indexes for ar1[] and ar2[]
        int i = 0, j = 0;

        // Initialize result and current sum through ar1[]
        // and ar2[].
        int result = 0, sum1 = 0, sum2 = 0;

        // Below 3 loops are similar to merge in merge sort
        while (i < m && j < n) {
            // Add elements of ar1[] to sum1
            if (ar1[i] < ar2[j])
                sum1 += ar1[i++];

                // Add elements of ar2[] to sum2
            else if (ar1[i] > ar2[j])
                sum2 += ar2[j++];

                // we reached a common point
            else {
                // Take the maximum of two sums and add to
                // result
                // Also add the common element of array,
                // once
                result += Math.max(sum1, sum2) + ar1[i];

                // Update sum1 and sum2 for elements after
                // this intersection point
                sum1 = 0;
                sum2 = 0;

                // update i and j to move to next element of
                // each array
                i++;
                j++;
            }
        }

        // Add remaining elements of ar1[]
        while (i < m)
            sum1 += ar1[i++];

        // Add remaining elements of ar2[]
        while (j < n)
            sum2 += ar2[j++];

        // Add maximum of two sums of remaining elements
        result += Math.max(sum1, sum2);
        return result;
    }
}
