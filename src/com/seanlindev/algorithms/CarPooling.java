package com.seanlindev.algorithms;

import java.util.Arrays;
import java.util.PriorityQueue;

/*
There is a car with capacity empty seats. The vehicle only drives east (i.e., it cannot turn around and drive west).

You are given the integer capacity and an array trips where trips[i] = [numPassengersi, fromi, toi] indicates that the ith trip has numPassengersi passengers and the locations to pick them up and drop them off are fromi and toi respectively. The locations are given as the number of kilometers due east from the car's initial location.

Return true if it is possible to pick up and drop off all passengers for all the given trips, or false otherwise.



Example 1:

Input: trips = [[2,1,5],[3,3,7]], capacity = 4
Output: false
Example 2:

Input: trips = [[2,1,5],[3,3,7]], capacity = 5
Output: true


Constraints:

1 <= trips.length <= 1000
trips[i].length == 3
1 <= numPassengersi <= 100
0 <= fromi < toi <= 1000
1 <= capacity <= 105
 */
public class CarPooling {
    public boolean carPooling(int[][] trips, int capacity) {
        Arrays.sort(trips, (a, b) -> Integer.compare(a[1], b[1]));

        int count = 0;
        int index = 0;
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
        while (index < trips.length) {
            int[] trip = trips[index];
            while (minHeap.size() > 0 && minHeap.peek()[0] <= trip[1]) {
                int[] node = minHeap.poll();
                count -= node[1];
            }
            count += trip[0];
            if (count > capacity) { return false; }
            minHeap.add(new int[]{ trip[2], trip[0] });
            index += 1;
        }

        return true;

        // int[][] pickup = new int[trips.length][2];
        // int[][] drop = new int[trips.length][2];
        // for (int i = 0; i < trips.length; i++) {
        //     int[] trip = trips[i];
        //     pickup[i] = new int[]{ trip[1], trip[0] };
        //     drop[i] = new int[]{ trip[2], trip[0] };
        // }

        // Arrays.sort(pickup, (a, b) -> Integer.compare(a[0], b[0]));
        // Arrays.sort(drop, (a, b) -> Integer.compare(a[0], b[0]));

        // int count = 0;
        // int pickupIndex = 0;
        // int dropIndex = 0;
        // while (pickupIndex < pickup.length) {
        //     if (pickup[pickupIndex][0] < drop[dropIndex][0]) {
        //         count += pickup[pickupIndex][1];
        //         pickupIndex += 1;
        //     } else if (pickup[pickupIndex][0] > drop[dropIndex][0]) {
        //         count -= drop[dropIndex][1];
        //         dropIndex += 1;
        //     } else {
        //         count -= drop[dropIndex][1];
        //         count += pickup[pickupIndex][1];
        //         pickupIndex += 1;
        //         dropIndex += 1;
        //     }

        //     if (count > capacity) { return false; }
        // }
        // return true;
    }
}
