package com.seanlindev.algorithms;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/*
Design a stack-like data structure to push elements to the stack and pop the most frequent element from the stack.

Implement the FreqStack class:

FreqStack() constructs an empty frequency stack.
void push(int val) pushes an integer val onto the top of the stack.
int pop() removes and returns the most frequent element in the stack.
If there is a tie for the most frequent element, the element closest to the stack's top is removed and returned.


Example 1:

Input
["FreqStack", "push", "push", "push", "push", "push", "push", "pop", "pop", "pop", "pop"]
[[], [5], [7], [5], [7], [4], [5], [], [], [], []]
Output
[null, null, null, null, null, null, null, 5, 7, 5, 4]

Explanation
FreqStack freqStack = new FreqStack();
freqStack.push(5); // The stack is [5]
freqStack.push(7); // The stack is [5,7]
freqStack.push(5); // The stack is [5,7,5]
freqStack.push(7); // The stack is [5,7,5,7]
freqStack.push(4); // The stack is [5,7,5,7,4]
freqStack.push(5); // The stack is [5,7,5,7,4,5]
freqStack.pop();   // return 5, as 5 is the most frequent. The stack becomes [5,7,5,7,4].
freqStack.pop();   // return 7, as 5 and 7 is the most frequent, but 7 is closest to the top. The stack becomes [5,7,5,4].
freqStack.pop();   // return 5, as 5 is the most frequent. The stack becomes [5,7,4].
freqStack.pop();   // return 4, as 4, 5 and 7 is the most frequent, but 4 is closest to the top. The stack becomes [5,7].


Constraints:

0 <= val <= 109
At most 2 * 104 calls will be made to push and pop.
It is guaranteed that there will be at least one element in the stack before calling pop.
 */
public class MaximumFrequencyStack {
    Map<Integer, Integer> numCountMap;
    Map<Integer, Stack<Integer>> countStackMap;
    int maxCount = 0;
    public MaximumFrequencyStack() {
        this.numCountMap = new HashMap<>();
        this.countStackMap = new HashMap<>();
    }

    public void push(int val) {
        numCountMap.put(val, numCountMap.getOrDefault(val, 0) + 1); // numCountMap = { 4: 2, 0: 1, 9: 1, 3: 1, 2: 1 }
        int count = numCountMap.get(val); //count = 1
        countStackMap.putIfAbsent(count, new Stack<>()); //countStackMap = {1: [4,0,9,3,2], 2:[]}
        countStackMap.get(count).push(val);
        maxCount = Math.max(maxCount, count); //maxCount = 2
    }

    public int pop() {
        Stack<Integer> stack = countStackMap.get(maxCount);
        int result = stack.pop();
        numCountMap.put(result, numCountMap.get(result) - 1);
        if (stack.size() == 0) {
            maxCount -= 1; //maxCount=1
        }
        return result;
    }
}
