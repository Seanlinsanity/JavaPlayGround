package com.seanlindev.algorithms;

import java.util.HashMap;
import java.util.Map;

/*
Design and implement a data structure for a Least Frequently Used (LFU) cache.

Implement the LFUCache class:

LFUCache(int capacity) Initializes the object with the capacity of the data structure.
int get(int key) Gets the value of the key if the key exists in the cache. Otherwise, returns -1.
void put(int key, int value) Update the value of the key if present, or inserts the key if not already present. When the cache reaches its capacity, it should invalidate and remove the least frequently used key before inserting a new item. For this problem, when there is a tie (i.e., two or more keys with the same frequency), the least recently used key would be invalidated.
To determine the least frequently used key, a use counter is maintained for each key in the cache. The key with the smallest use counter is the least frequently used key.

When a key is first inserted into the cache, its use counter is set to 1 (due to the put operation). The use counter for a key in the cache is incremented either a get or put operation is called on it.

The functions get and put must each run in O(1) average time complexity.



Example 1:

Input
["LFUCache", "put", "put", "get", "put", "get", "get", "put", "get", "get", "get"]
[[2], [1, 1], [2, 2], [1], [3, 3], [2], [3], [4, 4], [1], [3], [4]]
Output
[null, null, null, 1, null, -1, 3, null, -1, 3, 4]

Explanation
// cnt(x) = the use counter for key x
// cache=[] will show the last used order for tiebreakers (leftmost element is  most recent)
LFUCache lfu = new LFUCache(2);
lfu.put(1, 1);   // cache=[1,_], cnt(1)=1
lfu.put(2, 2);   // cache=[2,1], cnt(2)=1, cnt(1)=1
lfu.get(1);      // return 1
                 // cache=[1,2], cnt(2)=1, cnt(1)=2
lfu.put(3, 3);   // 2 is the LFU key because cnt(2)=1 is the smallest, invalidate 2.
                 // cache=[3,1], cnt(3)=1, cnt(1)=2
lfu.get(2);      // return -1 (not found)
lfu.get(3);      // return 3
                 // cache=[3,1], cnt(3)=2, cnt(1)=2
lfu.put(4, 4);   // Both 1 and 3 have the same cnt, but 1 is LRU, invalidate 1.
                 // cache=[4,3], cnt(4)=1, cnt(3)=2
lfu.get(1);      // return -1 (not found)
lfu.get(3);      // return 3
                 // cache=[3,4], cnt(4)=1, cnt(3)=3
lfu.get(4);      // return 4
                 // cache=[4,3], cnt(4)=2, cnt(3)=3


Constraints:

1 <= capacity <= 104
0 <= key <= 105
0 <= value <= 109
At most 2 * 105 calls will be made to get and put.
 */
public class LFUCache {
    class DoubleLinkedList {
        ListNode head;
        ListNode tail;

        DoubleLinkedList() {
            this.head = new ListNode(-1, 0, 0);
            this.tail = new ListNode(-1, 0, 0);
            this.head.next = this.tail;
            this.tail.prev = this.head;
        }

        void remove(ListNode node) {
            ListNode prev = node.prev;
            ListNode next = node.next;
            prev.next = next;
            next.prev = prev;
        }

        void add(ListNode node) {
            ListNode last = tail.prev;
            last.next = node;
            node.prev = last;
            node.next = tail;
            tail.prev = node;
        }

        ListNode removeFirst() { //[H<->(2,2,1)<->T]
            ListNode first = head.next; //(2,2,1)
            ListNode prev = first.prev; //H
            ListNode next = first.next; //T
            prev.next = next; // H <-> T
            next.prev = prev; // H <-> T
            return first;
        }

        boolean isEmpty() {
            return head.next == tail;
        }
    }

    class ListNode {
        int key;
        int val;
        int count;
        ListNode next;
        ListNode prev;

        ListNode(int key, int val, int count) {
            this.key = key;
            this.val = val;
            this.count = count;
        }
    }

    Map<Integer, ListNode> keyNodeMap;
    Map<Integer, DoubleLinkedList> countLinkedListMap;
    int capacity;
    int size; //size=0
    int minFreq;

    public LFUCache(int capacity) {
        this.capacity = capacity; //capacity=2
        this.keyNodeMap = new HashMap<>(); // keyNodeMap={}
        this.countLinkedListMap = new HashMap<>(); // countLinkedListMap={}
        this.minFreq = Integer.MAX_VALUE; //minFreq=max
    }

    public int get(int key) {
        ListNode node = keyNodeMap.get(key); //keyNodeMap={1:(1,1,2),3:(3,3,1)}
        if (node == null) { return -1; }

        int count = node.count;
        node.count = count + 1;
        DoubleLinkedList linkedList = countLinkedListMap.get(count); //countLinkedListMap={1:[H<->(3,3,1)<->T], 2:[H<->(1,2)<->T}
        linkedList.remove(node); //countLinkedListMap={1:[H<->T], 2:[H<->(1,1,2)<->T}
        if (linkedList.isEmpty() && count == minFreq) {
            minFreq += 1;
        }
        countLinkedListMap.putIfAbsent(count + 1, new DoubleLinkedList());
        countLinkedListMap.get(count + 1).add(node); //countLinkedListMap={1:[H<->T], 2:[H<->(1,1,2)<->(3,3,2)<->T}
        return node.val;
    }

    public void put(int key, int value) {
        ListNode node = keyNodeMap.get(key);
        if (node == null) {
            if (size == capacity) {
                ListNode remove = countLinkedListMap.get(minFreq).removeFirst(); //countLinkedListMap={1:[H<->T], 2:[H<->(1,2)<->T]}
                keyNodeMap.remove(remove.key); //keyNodeMap={1:(1,1,2)}
            } else {
                size += 1; //size=1 -> 2
            }
            countLinkedListMap.putIfAbsent(1, new DoubleLinkedList()); //countLinkedListMap={1:[H<->T]}
            node = new ListNode(key, value, 1);
            countLinkedListMap.get(1).add(node); //countLinkedListMap={1:[H<->(3,3,1)<->T], 2:[H<->(1,2)<->T}
            keyNodeMap.put(key, node); //keyNodeMap={1:(1,1,2),3:(3,3,1)}
            minFreq = 1;
        } else {
            int count = node.count;
            node.val = value;
            node.count = count + 1;
            DoubleLinkedList linkedList = countLinkedListMap.get(count);
            linkedList.remove(node);
            if (linkedList.isEmpty() && count == minFreq) {
                minFreq = count + 1;
            }
            countLinkedListMap.putIfAbsent(count + 1, new DoubleLinkedList());
            countLinkedListMap.get(count + 1).add(node);
        }
    }
}
