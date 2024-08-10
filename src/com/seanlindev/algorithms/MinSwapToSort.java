package com.seanlindev.algorithms;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
Given an array of n distinct elements. Find the minimum number of swaps required to sort the array in strictly increasing order.


Example 1:

Input:
nums = {2, 8, 5, 4}
Output:
1
Explanation:
swap 8 with 4.
Example 2:

Input:
nums = {10, 19, 6, 3, 5}
Output:
2
Explanation:
swap 10 with 3 and swap 19 with 5.

Your Task:
You do not need to read input or print anything. Your task is to complete the function minSwaps() which takes the nums as input parameter and returns an integer denoting the minimum number of swaps required to sort the array.
If the array is already sorted, return 0.


Expected Time Complexity: O(nlogn)
Expected Auxiliary Space: O(n)


Constraints:
1 ≤ n ≤ 105
1 ≤ numsi ≤ 106
 */
public class MinSwapToSort {
    public int minSwaps(int[] nums)
    {
        int ans = 0;
        // Hashmap which stores the
        // indexes of the input array
        Map<Integer, Integer> h
                = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++) {
            h.put(nums[i], i);
        }

        int[] temp = Arrays.copyOfRange(nums, 0, nums.length);
        Arrays.sort(temp);
        for (int i = 0; i < nums.length; i++) {

            // This is checking whether
            // the current element is
            // at the right place or not
            if (nums[i] != temp[i]) {
                ans++;
                int init = nums[i];

                // If not, swap this element
                // with the index of the
                // element which should come here
                swap(nums, i, h.get(temp[i]));

                // Update the indexes in
                // the hashmap accordingly
                h.put(init, h.get(temp[i]));
                h.put(temp[i], i);
            }
        }
        return ans;
    }

    void swap(int[] arr, int index1, int index2) {
        int temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;
    }
}
