package com.seanlindev.algorithms;

/*
You are given an integer array height of length n. There are n vertical lines drawn such that the two endpoints of the ith line are (i, 0) and (i, height[i]).

Find two lines that together with the x-axis form a container, such that the container contains the most water.

Return the maximum amount of water a container can store.

Notice that you may not slant the container.



Example 1:


Input: height = [1,8,6,2,5,4,8,3,7]
Output: 49
Explanation: The above vertical lines are represented by array [1,8,6,2,5,4,8,3,7]. In this case, the max area of water (blue section) the container can contain is 49.
Example 2:

Input: height = [1,1]
Output: 1


Constraints:

n == height.length
2 <= n <= 105
0 <= height[i] <= 104
 */
public class ContainerWithMostWater {

    public int maxArea(int[] height) {
        int left = 0; //0 -> 1
        int right = height.length - 1;  //8 -> 6
        int result = 0;
        while (left < right) {
            int area = Math.min(height[left], height[right]) * (right - left); //49
            result = Math.max(area, result); //49
            if (height[left] < height[right]) {  // 8, 7
                int currentHeight = height[left]; //1
                left += 1; //1
                while (height[left] <= currentHeight && left < right) {
                    left += 1;
                }
            } else {
                int currentHeight = height[right]; //7
                right -= 1; //7 -> 6
                while (height[right] <= currentHeight && left < right) { //3 -> 8
                    right -= 1;
                }
            }
        }
        return result;
    }

    public int maxArea2(int[] height) {
        int left = 0; //0 -> 1
        int right = height.length - 1;  //8 -> 6
        int result = 0;
        while (left < right) {
            int area = Math.min(height[left], height[right]) * (right - left); //49
            result = Math.max(area, result); //49
            if (height[left] < height[right]) {  // 8, 7
                left += 1; //1
            } else {
                right -= 1; //7 -> 6
            }
        }
        return result;
    }
}
