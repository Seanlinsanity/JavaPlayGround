package com.seanlindev.algorithms;

import java.util.HashMap;
import java.util.Map;

/*
You are given a stream of points on the X-Y plane. Design an algorithm that:

Adds new points from the stream into a data structure. Duplicate points are allowed and should be treated as different points.
Given a query point, counts the number of ways to choose three points from the data structure such that the three points and the query point form an axis-aligned square with positive area.
An axis-aligned square is a square whose edges are all the same length and are either parallel or perpendicular to the x-axis and y-axis.

Implement the DetectSquares class:

DetectSquares() Initializes the object with an empty data structure.
void add(int[] point) Adds a new point point = [x, y] to the data structure.
int count(int[] point) Counts the number of ways to form axis-aligned squares with point point = [x, y] as described above.


Example 1:


Input
["DetectSquares", "add", "add", "add", "count", "count", "add", "count"]
[[], [[3, 10]], [[11, 2]], [[3, 2]], [[11, 10]], [[14, 8]], [[11, 2]], [[11, 10]]]
Output
[null, null, null, null, 1, 0, null, 2]

Explanation
DetectSquares detectSquares = new DetectSquares();
detectSquares.add([3, 10]);
detectSquares.add([11, 2]);
detectSquares.add([3, 2]);
detectSquares.count([11, 10]); // return 1. You can choose:
                               //   - The first, second, and third points
detectSquares.count([14, 8]);  // return 0. The query point cannot form a square with any points in the data structure.
detectSquares.add([11, 2]);    // Adding duplicate points is allowed.
detectSquares.count([11, 10]); // return 2. You can choose:
                               //   - The first, second, and third points
                               //   - The first, third, and fourth points


Constraints:

point.length == 2
0 <= x, y <= 1000
At most 3000 calls in total will be made to add and count.
 */

public class DetectSquares {
    Map<Integer, Map<Integer, Integer>> points;
    // int[][] pointCount;
    // Set<String> pointSet;
    public DetectSquares() {
        // this.pointCount = new int[1001][1001];
        // this.pointSet = new HashSet<>();
        this.points = new HashMap<>();
    }

    public void add(int[] point) {
        int x = point[0];
        int y = point[1];
        if(points.containsKey(x)) {
            Map<Integer, Integer> temp = points.get(x);
            temp.put(y, temp.getOrDefault(y, 0)+1);
        } else {
            points.put(x, new HashMap<>());
            points.get(x).put(y, 1);
        }
        // int x = point[0], y = point[1];
        // pointCount[x][y] += 1;
        // pointSet.add(String.valueOf(x) + "-" + String.valueOf(y));
    }

    public int count(int[] point) {
        int res = 0;
        int x1 = point[0];
        int y1 = point[1];

        if(!points.containsKey(x1)) {
            return 0;
        } else {
            for(int y2: points.get(x1).keySet()) {
                if(y1 == y2) {
                    continue;
                }
                int count = points.get(x1).get(y2);
                int width = Math.abs(y2 - y1);
                // x1 - width
                if(points.containsKey(x1 - width)) {
                    Map<Integer, Integer> temp = points.get(x1 - width);
                    res += count * temp.getOrDefault(y1, 0) * temp.getOrDefault(y2, 0);
                }
                // x1 + width
                if(points.containsKey(x1 + width)) {
                    Map<Integer, Integer> temp = points.get(x1 + width);
                    res += count * temp.getOrDefault(y1, 0) * temp.getOrDefault(y2, 0);
                }
            }
        }
        return res;

        // int count = 0;
        // int x1 = point[0], y1 = point[1];
        // for (String pointKey: pointSet) {
        //     String[] position = pointKey.split("-");
        //     int x2 = Integer.valueOf(position[0]), y2 = Integer.valueOf(position[1]);
        //     if (x1 == x2 || y1 == y2) { continue; }
        //     if (Math.abs(x1 - x2) != Math.abs(y1 - y2)) { continue; }
        //     count += (pointCount[x2][y2] * pointCount[x1][y2] * pointCount[x2][y1]);
        // }
        // return count;
    }
}
