package com.seanlindev.algorithms;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/*
In a warehouse, there is a row of barcodes, where the ith barcode is barcodes[i].

Rearrange the barcodes so that no two adjacent barcodes are equal. You may return any answer, and it is guaranteed an answer exists.



Example 1:

Input: barcodes = [1,1,1,2,2,2]
Output: [2,1,2,1,2,1]
Example 2:

Input: barcodes = [1,1,1,1,2,2,3,3]
Output: [1,3,1,3,1,2,1,2]


Constraints:

1 <= barcodes.length <= 10000
1 <= barcodes[i] <= 10000
 */
public class DistantBarcodes {
    public int[] rearrangeBarcodes(int[] barcodes) {
        Map<Integer, Integer> numCountMap = new HashMap<>();
        for (int num: barcodes) {
            numCountMap.put(num, numCountMap.getOrDefault(num, 0) + 1);
        }

        PriorityQueue<int[]> maxHeap = new PriorityQueue<>((a, b) -> Integer.compare(b[1], a[1]));
        for (int key: numCountMap.keySet()) {
            maxHeap.add(new int[]{ key, numCountMap.get(key) });
        }

        int[] result = new int[barcodes.length];
        int index = 0;
        int[] prev = null;
        while (maxHeap.size() > 0 || prev != null) {
            int[] node = maxHeap.poll();
            result[index] = node[0];
            index += 1;
            if (prev != null) {
                maxHeap.add(prev);
            }

            if (node[1] - 1 > 0) {
                prev = new int[]{ node[0], node[1] - 1 };
            } else {
                prev = null;
            }
        }

        return result;
    }
}
