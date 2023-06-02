package com.seanlindev.algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSum {
     public List<List<Integer>> threeSum2(int[] nums) {
         Arrays.sort(nums);
         ArrayList<List<Integer>> sol = new ArrayList<List<Integer>>();

         for (int i = 0; i < nums.length - 2; i++) {
             if (i == 0 || (i > 0 && nums[i] != nums[i - 1])) {
                 int target = 0 - nums[i];
                 int left = i + 1;
                 int right = nums.length - 1;

                 while (left < right) {
                     if (nums[left] + nums[right] == target) {
                         ArrayList<Integer> miniSol = new ArrayList<>();
                         miniSol.add(nums[i]);
                         miniSol.add(nums[left]);
                         miniSol.add(nums[right]);
                         sol.add(miniSol);
                         while (left < right && nums[left] == nums[left + 1]) {
                             left++;
                         }
                         while (left < right && nums[right] == nums[right - 1]) {
                             right--;
                         }
                         left++;
                         right--;
                     } else if (nums[left] + nums[right] > target) {
                         right--;
                     } else {
                         left++;
                     }
                 }
             }
         }

         return sol;
     }
     
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);//[-1,-1,0, 1]
        List<List<Integer>> result = new ArrayList<>();
        int prevNum = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length - 2; i++) {
            // i = 0
            if (nums[i] == prevNum) { continue; } // -1
            List<List<Integer>> twoSumResult = twoSum(Arrays.copyOfRange(nums, i + 1, nums.length), 0 - nums[i]);
            for (List<Integer> pair: twoSumResult) {
                result.add(
                        new ArrayList<>(
                                List.of(nums[i], pair.get(0), pair.get(1))
                        )
                );
            }
            prevNum = nums[i];
        }
        return result;
    }

    //[-1, 0, 1], 1
    public List<List<Integer>> twoSum(int[] nums, int target) {
        int leftIndex = 0;
        int rightIndex = nums.length - 1;
        int prevLeft = Integer.MIN_VALUE;
        int prevRight = Integer.MIN_VALUE;
        List<List<Integer>> result = new ArrayList<>();
        while (leftIndex < rightIndex) {
            int leftNum = nums[leftIndex];
            int rightNum = nums[rightIndex];
            if (prevLeft == leftNum) {
                leftIndex += 1;
                continue;
            }

            if (prevRight == rightNum) {
                rightIndex -= 1;
                continue;
            }

            if (leftNum + rightNum == target) {
                result.add(List.of(leftNum, rightNum));
                leftIndex += 1;
                rightIndex -= 1;
                prevLeft = leftNum;
                prevRight = rightNum;
            } else if (leftNum + rightNum < target) {
                leftIndex += 1;
                prevLeft = leftNum;
            } else {
                rightIndex -= 1;
                prevRight = rightNum;
            }
        }
        return result;
    }
}
