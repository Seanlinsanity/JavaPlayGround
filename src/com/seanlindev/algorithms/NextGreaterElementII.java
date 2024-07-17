package com.seanlindev.algorithms;

import java.util.Arrays;
import java.util.Stack;

/*
Given a circular integer array nums (i.e., the next element of nums[nums.length - 1] is nums[0]), return the next greater number for every element in nums.

The next greater number of a number x is the first greater number to its traversing-order next in the array, which means you could search circularly to find its next greater number. If it doesn't exist, return -1 for this number.



Example 1:

Input: nums = [1,2,1]
Output: [2,-1,2]
Explanation: The first 1's next greater number is 2;
The number 2 can't find next greater number.
The second 1's next greater number needs to search circularly, which is also 2.
Example 2:

Input: nums = [1,2,3,4,3]
Output: [2,3,4,-1,4]
 */
public class NextGreaterElementII {
    public int[] nextGreaterElements(int[] nums) {
        // int n = nums.length;
        // int[] result = new int[n]; // Result array
        // java.util.Stack<Integer> stack = new java.util.Stack<>(); // Stack to keep track of indices
        // java.util.Arrays.fill(result, -1); // Initialize result array with -1

        // for (int i = 0; i < n * 2; i++) {
        //     int num = nums[i % n]; // Current number (use modulo for circular array)
        //     // Find next greater element
        //     while (!stack.isEmpty() && nums[stack.peek()] < num) {
        //         result[stack.pop()] = num; // Update result for the index popped from the stack
        //     }
        //     if (i < n) stack.push(i); // Push current index to stack
        // }

        // return result; // Return the result array

        // edge case
        if (nums.length == 1) { return new int[]{ -1 }; }

        int[] result = new int[nums.length];
        Arrays.fill(result, -1);
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < 2 * nums.length; i++) {
            int index = i % nums.length;
            int num = nums[index];
            while (stack.size() > 0 && nums[stack.peek()] < num) {
                int numIndex = stack.pop();
                if (result[numIndex] == -1) {
                    result[numIndex] = num;
                }
            }
            stack.push(index);
        }

        return result;
    }
}
