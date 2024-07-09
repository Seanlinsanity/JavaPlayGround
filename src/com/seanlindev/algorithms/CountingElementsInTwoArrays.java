package com.seanlindev.algorithms;

import java.util.ArrayList;
import java.util.Arrays;

/*
Given two unsorted arrays arr1[] and arr2[]. They may contain duplicates. For each element in arr1[] count elements less than or equal to it in array arr2[].

Example 1:

Input:
m = 6, n = 6
arr1[] = {1,2,3,4,7,9}
arr2[] = {0,1,2,1,1,4}
Output: 4 5 5 6 6 6
Explanation: Number of elements less than
or equal to 1, 2, 3, 4, 7, and 9 in the
second array are respectively 4,5,5,6,6,6
Example 2:

Input:
m = 5, n = 7
arr1[] = {4,8,7,5,1}
arr2[] = {4,48,3,0,1,1,5}
Output: 5 6 6 6 3
Explanation: Number of elements less than
or equal to 4, 8, 7, 5, and 1 in the
second array are respectively 5,6,6,6,3
Your Task :
Complete the function countEleLessThanOrEqual() that takes two array arr1[], arr2[],  m, and n as input and returns an array containing the required results(the count of elements less than or equal to it in arr2 for each element in arr1 where ith output represents the count for ith element in arr1.)

Expected Time Complexity: O(M logN + N logN).
Expected Auxiliary Space: O(m).

Constraints:
1<=m,n<=10^5
0<=arr1[i],arr2[j]<=10^5
 */
public class CountingElementsInTwoArrays {
    public static ArrayList<Integer> countEleLessThanOrEqual(int[] arr1, int[] arr2, int m, int n)
    {
        // add your code here
        Arrays.sort(arr2);
        ArrayList<Integer> result = new ArrayList<>();
        for (int num: arr1) {
            int left = 0;
            int right = arr2.length - 1;
            int findIndex = -1;
            while (left <= right) {
                int mid = (left + right) / 2;
                if (arr2[mid] == num) {
                    findIndex = mid;
                    left = mid + 1;
                } else if (arr2[mid] < num) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }

            if (findIndex == -1) {
                findIndex = right;
            }

            result.add(findIndex < 0 ? 0 : findIndex + 1);
        }

        return result;
    }
}
