package com.seanlindev.algorithms;

import java.util.ArrayList;
import java.util.List;

/*
Implement a SnapshotArray that supports the following interface:

SnapshotArray(int length) initializes an array-like data structure with the given length. Initially, each element equals 0.
void set(index, val) sets the element at the given index to be equal to val.
int snap() takes a snapshot of the array and returns the snap_id: the total number of times we called snap() minus 1.
int get(index, snap_id) returns the value at the given index, at the time we took the snapshot with the given snap_id


Example 1:

Input: ["SnapshotArray","set","snap","set","get"]
[[3],[0,5],[],[0,6],[0,0]]
Output: [null,null,0,null,5]
Explanation:
SnapshotArray snapshotArr = new SnapshotArray(3); // set the length to be 3
snapshotArr.set(0,5);  // Set array[0] = 5
snapshotArr.snap();  // Take a snapshot, return snap_id = 0
snapshotArr.set(0,6);
snapshotArr.get(0,0);  // Get the value of array[0] with snap_id = 0, return 5


Constraints:

1 <= length <= 5 * 104
0 <= index < length
0 <= val <= 109
0 <= snap_id < (the total number of times we call snap())
At most 5 * 104 calls will be made to set, snap, and get.
 */
public class SnapshotArray {
    List<List<int[]>> arrList;
    int snapshotId;
    public SnapshotArray(int length) {
        this.arrList = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            List<int[]> list = new ArrayList<>();
            // { num, snapshotId }
            list.add(new int[] { 0, 0 });
            this.arrList.add(list); //arrList=[[(0,0)],[(0,0)],[(0,0)],[(0,0)]]
        }
    }

    public void set(int index, int val) { //arrList=[[(0,0),(15,1)],[(0,0)],[(0,0)],[(0,0),(18,1)]]
        List<int[]> numHistory = arrList.get(index); //arrList=[[(0,0),(15,1)],[(0,0)],[(0,0)],[(0,0)]]
        if (numHistory.get(numHistory.size() - 1)[1] == snapshotId) {
            numHistory.get(numHistory.size() - 1)[0] = val; //arrList=[[(0,0),(15,1)],[(0,0)],[(0,0)],[(0,0),(18,1)]]
        } else {
            numHistory.add(new int[]{ val, snapshotId }); //arrList=[[(0,0),(15,1),(2,2)],[(0,0),(1,2)],[(0,0)],[(0,0),(18,1)]]
        }
    }

    public int snap() {
        snapshotId += 1; //snapshotId=1 -> 2 -> 3 -> 4 -> 5
        return snapshotId - 1;
    }

    public int get(int index, int snap_id) { //arrList=[[(0,0),(15,1),(2,2)],[(0,0),(1,2)],[(0,0)],[(0,0),(18,1)]]
        List<int[]> numHistory = arrList.get(index);
        // Binary Search
        int l = 0;
        int r = numHistory.size() - 1;
        while (l <= r) {
            int mid = (r - l) / 2 + l;
            int id = numHistory.get(mid)[1];
            if (id == snap_id) {
                return numHistory.get(mid)[0];
            } else if (id > snap_id) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }

        return numHistory.get(r)[0];
    }
}
