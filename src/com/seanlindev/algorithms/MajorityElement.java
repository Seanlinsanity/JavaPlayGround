package com.seanlindev.algorithms;

/*
Given an array A of N elements. Find the majority element in the array. A majority element in an array A of size N is an element that appears strictly more than N/2 times in the array.


Example 1:

Input:
N = 3
A[] = {1,2,3}
Output:
-1
Explanation:
Since, each element in
{1,2,3} appears only once so there
is no majority element.
Example 2:

Input:
N = 5
A[] = {3,1,3,3,2}
Output:
3
Explanation:
Since, 3 is present more
than N/2 times, so it is
the majority element.

Your Task:
The task is to complete the function majorityElement() which returns the majority element in the array. If no majority exists, return -1.


Expected Time Complexity: O(N).
Expected Auxiliary Space: O(1).


Constraints:
1 ≤ N ≤ 107
0 ≤ Ai ≤ 106
 */
public class MajorityElement {
    static int majorityElement(int[] a, int size)
    {
        // your code here
        int count = 0;
        int result = 0;
        for (int num: a) {
            if (count == 0) {
                result = num;
                count = 1;
            } else if (result != num) {
                count -= 1;
            } else {
                count += 1;
            }
        }

        int elementCount = 0;
        for (int num: a) {
            if (num == result) {
                elementCount += 1;
            }
        }
        return elementCount > (size / 2) ? result : -1;
    }
}
