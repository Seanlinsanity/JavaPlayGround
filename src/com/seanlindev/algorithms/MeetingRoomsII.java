package com.seanlindev.algorithms;

import java.util.Arrays;
import java.util.List;

/*
iven an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), find the minimum number of conference rooms required.)

(0,8),(8,10) is not conflict at 8

Example
Example1

Input: intervals = [(0,30),(5,10),(15,20)]
Output: 2
Explanation:
We need two meeting rooms
room1: (0,30)
room2: (5,10),(15,20)
Example2

Input: intervals = [(2,7)]
Output: 1
Explanation:
Only need one meeting room
 */
public class MeetingRoomsII {
    public int minMeetingRooms(List<MeetingRooms.Interval> intervals) {
        // Write your code here
        if (intervals.size() <= 1) { return intervals.size(); }

        int[] startIntervals = new int[intervals.size()];
        for (int i = 0; i < intervals.size(); i++) {
            startIntervals[i] = intervals.get(i).start;
        }
        Arrays.sort(startIntervals); //[0,5,15]

        int[] endIntervals = new int[intervals.size()];
        for (int i = 0; i < intervals.size(); i++) {
            endIntervals[i] = intervals.get(i).end;
        }
        Arrays.sort(endIntervals); //[10,20,30]

        int startIndex = 0;
        int endIndex = 0;
        int count = 0;
        int result = 0;
        while (startIndex < startIntervals.length) {
            if (startIntervals[startIndex] < endIntervals[endIndex]) {
                count += 1;
                startIndex += 1;
            } else {
                count -= 1;
                endIndex += 1;
            }
            result = Math.max(count, result);
        }

        return result;
    }
}
