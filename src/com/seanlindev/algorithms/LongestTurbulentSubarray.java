package com.seanlindev.algorithms;

/*
Given an integer array arr, return the length of a maximum size turbulent subarray of arr.

A subarray is turbulent if the comparison sign flips between each adjacent pair of elements in the subarray.

More formally, a subarray [arr[i], arr[i + 1], ..., arr[j]] of arr is said to be turbulent if and only if:

For i <= k < j:
arr[k] > arr[k + 1] when k is odd, and
arr[k] < arr[k + 1] when k is even.
Or, for i <= k < j:
arr[k] > arr[k + 1] when k is even, and
arr[k] < arr[k + 1] when k is odd.


Example 1:

Input: arr = [9,4,2,10,7,8,8,1,9]
Output: 5
Explanation: arr[1] > arr[2] < arr[3] > arr[4] < arr[5]
Example 2:

Input: arr = [4,8,12,16]
Output: 2
Example 3:

Input: arr = [100]
Output: 1


Constraints:

1 <= arr.length <= 4 * 104
0 <= arr[i] <= 109
 */
public class LongestTurbulentSubarray {
    // [0,1,1,0,1,0,1,1,0,0]
    public int maxTurbulenceSize(int[] arr) {
        // edge case
        if (arr.length == 1) { return 1; }

        int result = 1;
        int left = 0;
        int right = 0;
        while (right < arr.length) { //l=0, r=0->1->2
            if (left == right) {
                right += 1; //r=1
            } else if (arr[right] == arr[right - 1]) {
                left = right;
            } else if (right - left == 1) {
                result = Math.max(result, 2);
                right += 1;
            } else {
                boolean increase = (arr[right] > arr[right - 1]); //r=2,true
                while (right < arr.length && isTurbulent(arr, right, increase)) {
                    result = Math.max(result, right - left + 1);
                    right += 1;
                    increase = !increase;
                }

                left = right - 1;
            }
        }
        return result;
    }
    // r=2,true
    boolean isTurbulent(int[] arr, int right, boolean increase) {
        if (increase) {
            return (arr[right] > arr[right - 1]) && (arr[right - 1] < arr[right - 2]); //12>8 && 8<4
        } else {
            return (arr[right] < arr[right - 1]) && (arr[right - 1] > arr[right - 2]);
        }
    }
}
