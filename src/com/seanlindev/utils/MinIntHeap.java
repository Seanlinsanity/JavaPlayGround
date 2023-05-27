package com.seanlindev.utils;

public class MinIntHeap {
    private int capacity = 10;
    private int size = 0;
    int[] items = new int[capacity];

    private int getLeftChildIndex(int parentIndex) { return parentIndex * 2 + 1; }
    private int getRightChildIndex(int parentIndex) { return parentIndex * 2 + 2; }
    private int getParentIndex(int childIndex) { return (childIndex - 1) / 2; }
    private boolean hasLeftChild(int index) { return (index * 2 + 1) < size; }
    private boolean hasRightChild(int index) { return (index * 2 + 2) < size; }
    private boolean hasParent(int index) { return index > 0; }
    private int leftChild(int index) { return items[getLeftChildIndex(index)]; }
    private int rightChild(int index) { return items[getRightChildIndex(index)]; }
    private int parent(int index) { return items[getParentIndex(index)]; }

    private void swap(int firstIndex, int secondIndex) {
        int temp = items[firstIndex];
        items[firstIndex] = items[secondIndex];
        items[secondIndex] = temp;
    }

    private void ensureExtraCapacity() {
        if (size == capacity) {
            int[] newItems = new int[capacity * 2];
            System.arraycopy(items, 0, newItems, 0, capacity);
            items = newItems;
            capacity = 2 * capacity;
        }
    }

    private int poll() {
        if (size == 0) { throw new RuntimeException("empty heap"); }
        int item = items[0];
        int lastItem = items[size - 1];
        items[0] = lastItem;
        size -= 1;
        // heapifyDown();
        return item;
    }

    private void add(int value) {
        ensureExtraCapacity();
        items[size] = value;
        size += 1;
        heapifyUp();
    }

    private void heapifyUp() {
        int currentIndex = size - 1;
        while (hasParent(currentIndex)) {
            if (items[currentIndex] > parent(currentIndex)) {
                return;
            }
            int parentIndex = getParentIndex(currentIndex);
            swap(currentIndex, parentIndex);
            currentIndex = parentIndex;
        }
    }

    private void heapifyDown() {
        int currentIndex = 0;
        while (hasLeftChild(currentIndex)) {
            int left = leftChild(currentIndex);
            int right = rightChild(currentIndex);
            int smallerIndex = (left < right) ? getLeftChildIndex(currentIndex) : getRightChildIndex(currentIndex);
            if (items[currentIndex] < items[smallerIndex]) {
                return;
            }

            swap(currentIndex, smallerIndex);
            currentIndex = smallerIndex;
        }
    }
}
