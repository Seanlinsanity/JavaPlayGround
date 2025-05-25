package com.seanlindev.algorithms;

import java.util.ArrayList;
import java.util.List;

/*
Design a hit counter which counts the number of hits received in the past 5 minutes (i.e., the past 300 seconds).

Your system should accept a timestamp parameter (in seconds granularity), and you may assume that calls are being made to the system in chronological order (i.e., timestamp is monotonically increasing). Several hits may arrive roughly at the same time.

Implement the HitCounter class:

HitCounter() Initializes the object of the hit counter system.
void hit(int timestamp) Records a hit that happened at timestamp (in seconds). Several hits may happen at the same timestamp.
int getHits(int timestamp) Returns the number of hits in the past 5 minutes from timestamp (i.e., the past 300 seconds).


Example 1:

Input
["HitCounter", "hit", "hit", "hit", "getHits", "hit", "getHits", "getHits"]
[[], [1], [2], [3], [4], [300], [300], [301]]
Output
[null, null, null, null, 3, null, 4, 3]

Explanation
HitCounter hitCounter = new HitCounter();
hitCounter.hit(1);       // hit at timestamp 1.
hitCounter.hit(2);       // hit at timestamp 2.
hitCounter.hit(3);       // hit at timestamp 3.
hitCounter.getHits(4);   // get hits at timestamp 4, return 3.
hitCounter.hit(300);     // hit at timestamp 300.
hitCounter.getHits(300); // get hits at timestamp 300, return 4.
hitCounter.getHits(301); // get hits at timestamp 301, return 3.


Constraints:

1 <= timestamp <= 2 * 109
All the calls are being made to the system in chronological order (i.e., timestamp is monotonically increasing).
At most 300 calls will be made to hit and getHits.
 */
public class DesignHitCounter {
    List<Integer> nums;
    public DesignHitCounter() {
        this.nums = new ArrayList<>();
    }

    public void hit(int timestamp) {
        nums.add(timestamp);
    }

    public int getHits(int timestamp) {
        if (nums.size() == 0) { return 0; }
        int from = timestamp - 299;
        if (nums.get(0) >= from) { return nums.size(); }
        if (nums.get(nums.size() - 1) < from) {
            nums = new ArrayList<>();
            return 0;
        }

        int l = 0;
        int r = nums.size() - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (nums.get(mid) >= from) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }

        return (nums.size() - l);
    }
}
