package com.seanlindev.algorithms;

import java.util.Collections;
import java.util.List;

public class MeetingRooms {

    class Interval {
        int start, end;
        Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
    public boolean canAttendMeetings(List<Interval> intervals) {

        // Write your code here
        Collections.sort(intervals, (a, b) -> Integer.compare(a.start, b.start));
        int prevEnd = 0;
        for (Interval interval: intervals) {
            if (interval.start < prevEnd) {
                return false;
            }
            prevEnd = interval.end;
        }

        return true;
    }
}
