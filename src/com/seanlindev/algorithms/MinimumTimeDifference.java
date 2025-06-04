package com.seanlindev.algorithms;

import java.util.List;

/*
Given a list of 24-hour clock time points in "HH:MM" format, return the minimum minutes difference between any two time-points in the list.


Example 1:

Input: timePoints = ["23:59","00:00"]
Output: 1
Example 2:

Input: timePoints = ["00:00","23:59","00:00"]
Output: 0


Constraints:

2 <= timePoints.length <= 2 * 104
timePoints[i] is in the format "HH:MM".
 */
public class MinimumTimeDifference {
    public int findMinDifference(List<String> timePoints) {
        boolean[] timeVisited = new boolean[24 * 60];
        for (String timePoint: timePoints) {
            String[] split = timePoint.split(":");
            int time = Integer.valueOf(split[0]) * 60 + Integer.valueOf(split[1]);
            if (timeVisited[time]) {
                return 0;
            }
            timeVisited[time] = true;
        }

        int result = Integer.MAX_VALUE;
        int prev = -1;
        int first = -1, last = -1;
        for (int i = 0; i < timeVisited.length; i++) {
            if (timeVisited[i] && prev > -1) {
                result = Math.min(result, i - prev);
            }

            if (timeVisited[i]) {
                prev = i;
                if (first == -1) {
                    first = i;
                }
                last = i;
            }
        }
        return Math.min(result, first + 24 * 60 - last);
    }
}
