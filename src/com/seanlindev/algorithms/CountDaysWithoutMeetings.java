package com.seanlindev.algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
You are given a positive integer days representing the total number of days an employee is available for work (starting from day 1). You are also given a 2D array meetings of size n where, meetings[i] = [start_i, end_i] represents the starting and ending days of meeting i (inclusive).

Return the count of days when the employee is available for work but no meetings are scheduled.

Note: The meetings may overlap.



Example 1:

Input: days = 10, meetings = [[5,7],[1,3],[9,10]]

Output: 2

Explanation:

There is no meeting scheduled on the 4th and 8th days.

Example 2:

Input: days = 5, meetings = [[2,4],[1,3]]

Output: 1

Explanation:

There is no meeting scheduled on the 5th day.

Example 3:

Input: days = 6, meetings = [[1,6]]

Output: 0

Explanation:

Meetings are scheduled for all working days.



Constraints:

1 <= days <= 109
1 <= meetings.length <= 105
meetings[i].length == 2
1 <= meetings[i][0] <= meetings[i][1] <= days
 */
public class CountDaysWithoutMeetings {
    public int countDays(int days, int[][] meetings) {
        Arrays.sort(meetings, (a, b) -> Integer.compare(a[0], b[0]));
        List<int[]> merged = new ArrayList<>();
        for (int[] meeting: meetings) {
            int start = meeting[0], end = meeting[1];
            if (merged.size() == 0) {
                merged.add(meeting);
            } else {
                int[] last = merged.get(merged.size() - 1);
                if (start < last[1]) {
                    last[0] = Math.min(start, last[0]);
                    last[1] = Math.max(end, last[1]);
                } else {
                    merged.add(meeting);
                }
            }
        }

        int count = 0;
        int[] last = merged.get(merged.size() - 1);
        int[] first = merged.get(0);
        count += (days - last[1]) + (first[0] - 1);

        for (int i = 1; i < merged.size(); i++) {
            count += Math.max(merged.get(i)[0] - merged.get(i - 1)[1] - 1, 0);
        }

        return count;
    }
}
