package com.seanlindev.algorithms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class FindKClosestElements {
    class Node {
        int diff;
        int value;

        Node(int diff, int value) {
            this.diff = diff;
            this.value = value;
        }
    }

    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int index = binarySearch(arr, x);
        int leftIndex = Math.max(0, index - k);
        int rightIndex = Math.min(arr.length - 1, index + k);
        PriorityQueue<Node> minHeap = new PriorityQueue<Node>((a, b) -> {
            if (a.diff == b.diff) {
                return Integer.compare(a.value, b.value);
            }

            return Integer.compare(a.diff, b.diff);
        });

        for (int i = leftIndex; i <= rightIndex; i++) {
            minHeap.add(new Node(Math.abs(x - arr[i]), arr[i]));
        }

        List<Integer> result = new ArrayList<Integer>();
        while (result.size() < k) {
            Node node = minHeap.poll();
            result.add(node.value);
        }

        Collections.sort(result);
        return result;
    }

    int binarySearch(int[] arr, int x) {
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            int num = arr[mid];
            if (num > x) {
                right = mid - 1;
            } else if (num < x) {
                left = mid + 1;
            } else {
                return mid;
            }
        }

        return left;
    }
}
