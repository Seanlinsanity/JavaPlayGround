package com.seanlindev.algorithms;

import java.util.HashSet;
import java.util.Set;

/*
Given an unsorted array of integers nums, return the length of the longest consecutive elements sequence.

You must write an algorithm that runs in O(n) time.



Example 1:

Input: nums = [100,4,200,1,3,2]
Output: 4
Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.
Example 2:

Input: nums = [0,3,7,2,5,8,4,6,0,1]
Output: 9


Constraints:

0 <= nums.length <= 105
-109 <= nums[i] <= 109
 */
public class LongestConsecutiveSequence {
    public int longestConsecutive(int[] nums) {
        if (nums.length <= 1) { return nums.length; }

        Set<Integer> numSet = new HashSet<>();
        for (int num: nums) {
            numSet.add(num);
        }

        int result = 0;
        Set<Integer> visitedNumSet = new HashSet<>();
        for (Integer num: numSet) {
            if (visitedNumSet.contains(num)) {
                continue;
            }

            int count = 1;
            int smallerNum = num - 1;
            while (numSet.contains(smallerNum)) {
                visitedNumSet.add(smallerNum);
                count += 1;
                smallerNum -= 1;
            }

            int largerNum = num + 1;
            while (numSet.contains(largerNum)) {
                visitedNumSet.add(largerNum);
                count += 1;
                largerNum += 1;
            }

            result = Math.max(count, result);
        }

        return result;
    }
}
