package com.seanlindev.algorithms;

import java.util.HashSet;
import java.util.Set;

/*
Given a string path, where path[i] = 'N', 'S', 'E' or 'W', each representing moving one unit north, south, east, or west, respectively. You start at the origin (0, 0) on a 2D plane and walk on the path specified by path.

Return true if the path crosses itself at any point, that is, if at any time you are on a location you have previously visited. Return false otherwise.



Example 1:


Input: path = "NES"
Output: false
Explanation: Notice that the path doesn't cross any point more than once.
Example 2:


Input: path = "NESWW"
Output: true
Explanation: Notice that the path visits the origin twice.


Constraints:

1 <= path.length <= 104
path[i] is either 'N', 'S', 'E', or 'W'.
 */
public class PathCrossing {
    public boolean isPathCrossing(String path) {
        Set<String> visited = new HashSet<>();
        visited.add("0,0");
        int[] position = new int[]{ 0, 0 };
        for (char direction: path.toCharArray()) {
            if (direction == 'N') {
                position[1] += 1;
            } else if (direction == 'S') {
                position[1] -= 1;
            } else if (direction == 'E') {
                position[0] += 1;
            } else {
                position[0] -= 1;
            }

            String visit = position[0] + "," + position[1];
            if (visited.contains(visit)) {
                return true;
            }

            visited.add(visit);
        }

        return false;
    }
}
