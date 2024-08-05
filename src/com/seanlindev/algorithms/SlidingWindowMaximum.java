package com.seanlindev.algorithms;

import java.util.Deque;
import java.util.LinkedList;

/*
You are given an array of integers nums, there is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position.

Return the max sliding window.



Example 1:

Input: nums = [1,3,-1,-3,5,3,6,7], k = 3
Output: [3,3,5,5,6,7]
Explanation:
Window position                Max
---------------               -----
[1  3  -1] -3  5  3  6  7       3
 1 [3  -1  -3] 5  3  6  7       3
 1  3 [-1  -3  5] 3  6  7       5
 1  3  -1 [-3  5  3] 6  7       5
 1  3  -1  -3 [5  3  6] 7       6
 1  3  -1  -3  5 [3  6  7]      7
Example 2:

Input: nums = [1], k = 1
Output: [1]


Constraints:

1 <= nums.length <= 105
-104 <= nums[i] <= 104
1 <= k <= nums.length
 */

public class SlidingWindowMaximum {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int[] ans = new int[nums.length - k + 1];
        int r = 0;
        Deque<Integer> deque = new LinkedList<>();
        while (r < nums.length) {
            while (deque.size() > 0 && nums[deque.peekLast()] < nums[r]) {
                deque.removeLast();
            }
            deque.addLast(r);

            if (deque.peekFirst() < r - k + 1) {
                deque.removeFirst();
            }

            if (r >= k - 1) {
                ans[r - k + 1] = nums[deque.peekFirst()];
            }

            r += 1;
        }

        return ans;
    }
}
