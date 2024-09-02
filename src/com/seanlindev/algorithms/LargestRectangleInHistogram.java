package com.seanlindev.algorithms;

import java.util.Stack;

/*
Given an array of integers heights representing the histogram's bar height where the width of each bar is 1, return the area of the largest rectangle in the histogram.



Example 1:


Input: heights = [2,1,5,6,2,3]
Output: 10
Explanation: The above is a histogram where width of each bar is 1.
The largest rectangle is shown in the red area, which has an area = 10 units.
Example 2:


Input: heights = [2,4]
Output: 4


Constraints:

1 <= heights.length <= 105
0 <= heights[i] <= 104
 */
public class LargestRectangleInHistogram {
    public int largestRectangleArea(int[] heights) {
        // edgse case
        if (heights.length == 1) { return heights[0]; }

        int result = 0;
        Stack<int[]> stack = new Stack<>();
        for (int i = 0; i < heights.length; i++) {
            int index = i;
            while (stack.size() > 0 && stack.peek()[0] > heights[i]) {
                int[] node = stack.pop();
                result = Math.max(result, node[0] * (i - node[1]));
                index = node[1];
            }

            stack.add(new int[]{ heights[i], index });
        }

        while (stack.size() > 0) {
            int[] node = stack.pop();
            result = Math.max(result, node[0] * (heights.length - node[1]));
        }

        return result;
    }

    public int largestRectangleArea2(int[] heights) {
        // edgse case
        if (heights.length == 1) { return heights[0]; }

        // get rightExtendedLength
        int[] rightExtendedLength = new int[heights.length];
        Stack<int[]> stack = new Stack<>();
        for (int i = 0; i < heights.length; i++) {
            while (stack.size() > 0 && stack.peek()[0] > heights[i]) {
                int[] node = stack.pop();
                rightExtendedLength[node[1]] = i - node[1];
            }

            stack.add(new int[]{ heights[i], i });
        }

        while (stack.size() > 0) {
            int[] node = stack.pop();
            rightExtendedLength[node[1]] = heights.length - node[1];
        }

        // get leftExtendedLength
        int[] leftExtendedLength = new int[heights.length];
        stack = new Stack<>();
        for (int i = heights.length - 1; i >= 0; i--) {
            while (stack.size() > 0 && stack.peek()[0] > heights[i]) {
                int[] node = stack.pop();
                leftExtendedLength[node[1]] = node[1] - i;
            }

            stack.add(new int[]{ heights[i], i });
        }

        while (stack.size() > 0) {
            int[] node = stack.pop();
            leftExtendedLength[node[1]] = node[1] + 1;
        }

        // find maximum rectangle
        int result = 0;
        for (int i = 0; i < heights.length; i++) {
            int leftLength = leftExtendedLength[i];
            int rightLength = rightExtendedLength[i];
            result = Math.max(result, heights[i] * (rightLength + leftLength - 1));
        }

        return result;
    }
}
