package com.seanlindev.algorithms;

import java.util.PriorityQueue;

public class FurthestBuildingYouCanReach {
    public int furthestBuilding(int[] heights, int bricks, int ladders) {
        if (heights.length == 1) { return 0; }

        PriorityQueue<Integer> ladderAllocations = new PriorityQueue<>();

        int index = 0;
        while (index < heights.length - 1) {
            int diff = heights[index + 1] - heights[index];
            // Use resources if next building is higher
            if (diff > 0) {
                ladderAllocations.add(diff);
                // Use ladders for the largest differences
                if (ladderAllocations.size() > ladders) {
                    bricks -= ladderAllocations.poll();
                }
                // Return current position if out of bricks
                if (bricks < 0) {
                    return index;
                }
            }

            index += 1;
        }
        // Return last building if enough resources
        return heights.length - 1;
    }
}
