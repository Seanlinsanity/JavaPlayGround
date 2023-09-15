package com.seanlindev.algorithms;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/*
Given an array of integers arr and an integer k. Find the least number of unique integers after removing exactly k elements.



Example 1:

Input: arr = [5,5,4], k = 1
Output: 1
Explanation: Remove the single 4, only 5 is left.
Example 2:
Input: arr = [4,3,1,1,3,3,2], k = 3
Output: 2
Explanation: Remove 4, 2 and either one of the two 1s or three 3s. 1 and 3 will be left.


Constraints:

1 <= arr.length <= 10^5
1 <= arr[i] <= 10^9
0 <= k <= arr.length
 */
public class LeastNumOfUniqueIntegersAfterKRemovals {
    public int findLeastNumOfUniqueInts(int[] arr, int k) {
        // edge case
        if (k == arr.length) { return 0; }
        Map<Integer, Integer> numCountMap = new HashMap<>();
        for (int num: arr) {
            numCountMap.putIfAbsent(num, 0);
            numCountMap.put(num, numCountMap.get(num) + 1);
        }

        PriorityQueue<Integer> minHeap = new PriorityQueue<>(numCountMap.values());
        int remove = 0;
        while (remove < k) {
            Integer count = minHeap.poll();
            remove += count;
        }

        return remove == k ? minHeap.size() : minHeap.size() + 1;
    }
}
