package com.seanlindev.algorithms;

/*
A permutation of an array of integers is an arrangement of its members into a sequence or linear order.

For example, for arr = [1,2,3], the following are all the permutations of arr: [1,2,3], [1,3,2], [2, 1, 3], [2, 3, 1], [3,1,2], [3,2,1].
The next permutation of an array of integers is the next lexicographically greater permutation of its integer. More formally, if all the permutations of the array are sorted in one container according to their lexicographical order, then the next permutation of that array is the permutation that follows it in the sorted container. If such arrangement is not possible, the array must be rearranged as the lowest possible order (i.e., sorted in ascending order).

For example, the next permutation of arr = [1,2,3] is [1,3,2].
Similarly, the next permutation of arr = [2,3,1] is [3,1,2].
While the next permutation of arr = [3,2,1] is [1,2,3] because [3,2,1] does not have a lexicographical larger rearrangement.
Given an array of integers nums, find the next permutation of nums.

The replacement must be in place and use only constant extra memory.



Example 1:

Input: nums = [1,2,3]
Output: [1,3,2]
Example 2:

Input: nums = [3,2,1]
Output: [1,2,3]
Example 3:

Input: nums = [1,1,5]
Output: [1,5,1]


Constraints:

1 <= nums.length <= 100
0 <= nums[i] <= 100
 */
public class NextPermutation {
    public void nextPermutation(int[] nums) {
        int n = nums.length, pivot = -1;

        // Step 1: Find the pivot
        for (int i = n - 2; i >= 0; i--) {
            if (nums[i] < nums[i + 1]) {
                pivot = i;
                break;
            }
        }

        if (pivot != -1) {
            // Step 2: Find the successor
            for (int j = n - 1; j > pivot; j--) {
                if (nums[j] > nums[pivot]) {
                    // Step 3: Swap pivot and successor
                    swap(nums, pivot, j);
                    break;
                }
            }
        }

        // Step 4: Reverse the sequence after pivot position
        reverse(nums, pivot + 1);

        // int decreasingIndex = 0;
        // for (int i = 0; i < nums.length - 1; i++) {
        //     if (nums[i] < nums[i + 1]) {
        //         decreasingIndex = i + 1;
        //     }
        // }

        // if (decreasingIndex == 0) {
        //     Arrays.sort(nums);
        //     return;
        // }

        // int swapIndex = decreasingIndex;
        // for (int i = decreasingIndex + 1; i < nums.length; i++) {
        //     if (nums[i] > nums[decreasingIndex - 1] && nums[i] <= nums[swapIndex]) {
        //         swapIndex = i;
        //     }
        // }

        // int temp = nums[decreasingIndex - 1];
        // nums[decreasingIndex - 1] = nums[swapIndex];
        // nums[swapIndex] = temp;

        // int[] copy = Arrays.copyOfRange(nums, decreasingIndex, nums.length);
        // Arrays.sort(copy);
        // for (int i = 0; i < copy.length; i++) {
        //     nums[decreasingIndex + i] = copy[i];
        // }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    private void reverse(int[] nums, int start) {
        int end = nums.length - 1;
        while (start < end) {
            swap(nums, start, end);
            start++;
            end--;
        }
    }
}
