package com.seanlindev.algorithms;

/*
Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it can trap after raining.



Example 1:


Input: height = [0,1,0,2,1,0,1,3,2,1,2,1]
Output: 6
Explanation: The above elevation map (black section) is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section) are being trapped.
Example 2:

Input: height = [4,2,0,3,2,5]
Output: 9


Constraints:

n == height.length
1 <= n <= 2 * 104
0 <= height[i] <= 105
 */

public class TrappingRainWater {
    public int trap(int[] height) {
        //edge case
        if (height.length == 1) { return 0; }

        //build left side max
        int[] leftMax = new int[height.length];
        int max1 = 0;
        for (int i = 1; i < height.length; i++) {
            max1 = Math.max(max1, height[i - 1]);
            leftMax[i] = max1;
        }

        //build right side max
        int[] rightMax = new int[height.length];
        int max2 = 0;
        for (int i = height.length - 2; i >= 0; i--){
            max2 = Math.max(max2, height[i + 1]);
            rightMax[i] = max2;
        }

        //count trap water
        int result = 0;
        for (int i = 0; i < height.length; i++) {
            int leftRightMin = Math.min(leftMax[i], rightMax[i]);
            if (leftRightMin > height[i]) {
                result += (leftRightMin - height[i]);
            }
        }

        return result;
    }
}
