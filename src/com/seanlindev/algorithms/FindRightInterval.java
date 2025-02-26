package com.seanlindev.algorithms;

import java.util.Arrays;

/*
You are given an array of intervals, where intervals[i] = [starti, endi] and each starti is unique.

The right interval for an interval i is an interval j such that startj >= endi and startj is minimized. Note that i may equal j.

Return an array of right interval indices for each interval i. If no right interval exists for interval i, then put -1 at index i.



Example 1:

Input: intervals = [[1,2]]
Output: [-1]
Explanation: There is only one interval in the collection, so it outputs -1.
Example 2:

Input: intervals = [[3,4],[2,3],[1,2]]
Output: [-1,0,1]
Explanation: There is no right interval for [3,4].
The right interval for [2,3] is [3,4] since start0 = 3 is the smallest start that is >= end1 = 3.
The right interval for [1,2] is [2,3] since start1 = 2 is the smallest start that is >= end2 = 2.
Example 3:

Input: intervals = [[1,4],[2,3],[3,4]]
Output: [-1,2,-1]
Explanation: There is no right interval for [1,4] and [3,4].
The right interval for [2,3] is [3,4] since start2 = 3 is the smallest start that is >= end1 = 3.


Constraints:

1 <= intervals.length <= 2 * 104
intervals[i].length == 2
-106 <= starti <= endi <= 106
The start point of each interval is unique.
 */
public class FindRightInterval {
    public int[] findRightInterval(int[][] intervals) {
        if (intervals.length == 1) { return new int[]{ (intervals[0][0] == intervals[0][1]) ? 0 : -1 }; }

        int[][] sortedIntervals = new int[intervals.length][3];
        for (int i = 0; i < intervals.length; i++) {
            sortedIntervals[i] = new int[] { intervals[i][0], intervals[i][1], i };
        }
        Arrays.sort(sortedIntervals, (a, b) -> Integer.compare(a[0], b[0]));

        int[] result = new int[intervals.length];
        for (int i = 0; i < sortedIntervals.length; i++) {
            int left = i;
            int right = sortedIntervals.length - 1;
            int rightIdx = Integer.MAX_VALUE;
            while (left <= right) {
                int mid = (left + right) / 2;
                if (sortedIntervals[mid][0] >= sortedIntervals[i][1]) {
                    rightIdx = Math.min(rightIdx, mid);
                    right = mid - 1;
                } else if (sortedIntervals[mid][0] < sortedIntervals[i][1]) {
                    left = mid + 1;
                }
            }

            result[sortedIntervals[i][2]] = rightIdx == Integer.MAX_VALUE ? -1 : sortedIntervals[rightIdx][2];
        }

        return result;
    }
}
