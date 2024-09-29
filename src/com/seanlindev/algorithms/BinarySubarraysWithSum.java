package com.seanlindev.algorithms;

import java.util.LinkedList;
import java.util.Queue;

/*
Given a binary array nums and an integer goal, return the number of non-empty subarrays with a sum goal.

A subarray is a contiguous part of the array.



Example 1:

Input: nums = [1,0,1,0,1], goal = 2
Output: 4
Explanation: The 4 subarrays are bolded and underlined below:
[1,0,1,0,1]
[1,0,1,0,1]
[1,0,1,0,1]
[1,0,1,0,1]
Example 2:

Input: nums = [0,0,0,0,0], goal = 0
Output: 15


Constraints:

1 <= nums.length <= 3 * 104
nums[i] is either 0 or 1.
0 <= goal <= nums.length
 */
public class BinarySubarraysWithSum {
    public int numSubarraysWithSum(int[] nums, int goal) {
        int left = 0;
        int right = 0;
        int result = 0;
        Queue<Integer> oneIndexQueue = new LinkedList<>();
        int currentSum = 0;
        while (right < nums.length) {
            int num = nums[right];
            currentSum += num;
            if (num == 1) {
                oneIndexQueue.add(right);
            }

            while (currentSum > goal) {
                int leftNum = nums[left];
                currentSum -= leftNum;
                if (leftNum == 1) {
                    oneIndexQueue.remove();
                }
                left += 1;
            }

            if (currentSum == goal) {
                if (oneIndexQueue.size() == 0) {
                    result += (right - left + 1);
                } else {
                    int firstOneIndex = oneIndexQueue.peek();
                    result += (firstOneIndex - left + 1);
                }
            }

            right += 1;
        }

        return result;
    }
}
