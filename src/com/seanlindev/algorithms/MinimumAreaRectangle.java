package com.seanlindev.algorithms;

import java.util.*;

/*
You are given an array of points in the X-Y plane points where points[i] = [xi, yi].

Return the minimum area of a rectangle formed from these points, with sides parallel to the X and Y axes. If there is not any such rectangle, return 0.



Example 1:


Input: points = [[1,1],[1,3],[3,1],[3,3],[2,2]]
Output: 4
Example 2:


Input: points = [[1,1],[1,3],[3,1],[3,3],[4,1],[4,3]]
Output: 2


Constraints:

1 <= points.length <= 500
points[i].length == 2
0 <= xi, yi <= 4 * 104
All the given points are unique.
 */
public class MinimumAreaRectangle {
    public int minAreaRect(int[][] points) {
        if (points.length < 4) { return 0; }

        Set<String> pointSet = new HashSet<>();
        for (int[] point: points) {
            pointSet.add(Arrays.toString(point));
        }

        int area = Integer.MAX_VALUE;
        List<List<int[]>> pairs = new ArrayList<>();
        for (int i = 0; i < points.length - 1; i++) {
            int[] point1 = points[i];
            for (int j = i + 1; j < points.length; j++) {
                int[] point2 = points[j];
                if (point1[0] != point2[0] && point1[1] != point2[1]) {
                    String key1 = Arrays.toString(new int[]{point2[0], point1[1]});
                    String key2 = Arrays.toString(new int[]{point1[0], point2[1]});
                    if (pointSet.contains(key1) && pointSet.contains(key2)) {
                        area = Math.min(area, Math.abs((point1[0] - point2[0]) * (point1[1] - point2[1])));
                    }
                }
            }
        }

        return area == Integer.MAX_VALUE ? 0 : area;

    }
}
