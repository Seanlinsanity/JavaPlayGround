package com.seanlindev.algorithms;

/*
You are given an integer array nums. In one move, you can pick an index i where 0 <= i < nums.length and increment nums[i] by 1.

Return the minimum number of moves to make every value in nums unique.

The test cases are generated so that the answer fits in a 32-bit integer.



Example 1:

Input: nums = [1,2,2]
Output: 1
Explanation: After 1 move, the array could be [1, 2, 3].
Example 2:

Input: nums = [3,2,1,2,1,7]
Output: 6
Explanation: After 6 moves, the array could be [3, 4, 1, 2, 5, 7].
It can be shown that it is impossible for the array to have all unique values with 5 or less moves.


Constraints:

1 <= nums.length <= 105
0 <= nums[i] <= 105

 */
public class MinimumIncrementMakeArrayUnique {
    public int minIncrementForUnique(int[] nums) {
        int n = nums.length;
        int max = 0;
        int minMoves = 0;

        // Find the maximum value in the array
        for (int value : nums) {
            max = Math.max(max, value);
        }

        // Create a frequency array to count occurrences
        int[] frequency = new int[n + max];

        // Populate the frequency array
        for (int value : nums) {
            frequency[value]++;
        }

        // Adjust frequencies to make all values unique
        for (int i = 0; i < frequency.length; i++) {
            if (frequency[i] <= 1) continue; // Skip if frequency is 1 or less

            int excess = frequency[i] - 1; // Calculate excess duplicates
            frequency[i + 1] += excess;    // Move excess to the next value
            minMoves += excess;           // Count moves required
        }

        return minMoves; // Return total moves



        // Map<Integer, Integer> numCountMap = new HashMap<>();
        // for (int num: nums) {
        //     numCountMap.put(num, numCountMap.getOrDefault(num, 0) + 1);
        // }

        // if (numCountMap.keySet().size() == nums.length) { return 0; }

        // List<Integer> sortedNums = new ArrayList<>(numCountMap.keySet());
        // Collections.sort(sortedNums);
        // int totalMoves = 0;
        // int increasedNum = -1;
        // for (int num: sortedNums) {
        //     if (numCountMap.get(num) == 1) { continue; }

        //     if (increasedNum == -1) {
        //         increasedNum = num + 1;
        //     } else {
        //         increasedNum = Math.max(increasedNum, num + 1);
        //     }

        //     while (numCountMap.get(num) > 1) {
        //         if (numCountMap.get(increasedNum) == null) {
        //             numCountMap.put(increasedNum, 1);
        //             numCountMap.put(num, numCountMap.get(num) - 1);
        //             totalMoves += (increasedNum - num);
        //         }
        //         increasedNum += 1;
        //     }
        // }

        // return totalMoves;
    }
}
