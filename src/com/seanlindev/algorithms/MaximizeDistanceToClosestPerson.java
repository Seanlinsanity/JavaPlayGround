package com.seanlindev.algorithms;

/*
You are given an array representing a row of seats where seats[i] = 1 represents a person sitting in the ith seat, and seats[i] = 0 represents that the ith seat is empty (0-indexed).

There is at least one empty seat, and at least one person sitting.

Alex wants to sit in the seat such that the distance between him and the closest person to him is maximized.

Return that maximum distance to the closest person.



Example 1:


Input: seats = [1,0,0,0,1,0,1]
Output: 2
Explanation:
If Alex sits in the second open seat (i.e. seats[2]), then the closest person has distance 2.
If Alex sits in any other open seat, the closest person has distance 1.
Thus, the maximum distance to the closest person is 2.
Example 2:

Input: seats = [1,0,0,0]
Output: 3
Explanation:
If Alex sits in the last seat (i.e. seats[3]), the closest person is 3 seats away.
This is the maximum distance possible, so the answer is 3.
Example 3:

Input: seats = [0,1]
Output: 1


Constraints:

2 <= seats.length <= 2 * 104
seats[i] is 0 or 1.
At least one seat is empty.
At least one seat is occupied.
 */
public class MaximizeDistanceToClosestPerson {
    public int maxDistToClosest(int[] seats) {
        int maxDistance = 0; // To keep track of the maximum distance
        int lastOccupied = -1; // To keep track of the last occupied seat
        int n = seats.length; // Total number of seats

        // Iterate through the array to find the maximum gap
        for (int i = 0; i < n; i++) {
            if (seats[i] == 1) {
                // For the first occupied seat, distance is i itself
                // For others, calculate the distance from the last occupied seat
                maxDistance = lastOccupied == -1 ? i : Math.max(maxDistance, (i - lastOccupied) / 2);
                lastOccupied = i; // Update the last occupied seat index
            }
        }
        // Check the distance from the last occupied seat to the end of the row
        maxDistance = Math.max(maxDistance, n - 1 - lastOccupied);

        return maxDistance;
    }
}
