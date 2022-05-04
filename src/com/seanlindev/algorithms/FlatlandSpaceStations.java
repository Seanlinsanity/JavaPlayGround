package com.seanlindev.algorithms;

import java.util.Arrays;

public class FlatlandSpaceStations {
    static int flatlandSpaceStations(int n, int[] c) {
        Arrays.sort(c);
        int maxDistance = c[0];
        for(int i = 1; i < c.length; i++){
            int distance = (c[i] - c[i-1]) / 2;
            maxDistance = Math.max(maxDistance, distance);
        }

        int lastGap = (n-1) - c[c.length - 1];
        return Math.max(maxDistance, lastGap);
    }
}
