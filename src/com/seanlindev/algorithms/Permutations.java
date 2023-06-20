package com.seanlindev.algorithms;

import java.util.ArrayList;
import java.util.List;

public class Permutations {
     public List<List<Integer>> permute2(int[] nums) {
         List<List<Integer>> ans = new ArrayList<>();
         function(ans, nums, 0);
         return ans;
     }

     public void function(List<List<Integer>> ans, int[] arr, int start) {
         if (start == arr.length) {
             List<Integer> list = new ArrayList();
             for (int i = 0; i < arr.length; i++) list.add(arr[i]);
             ans.add(list);
             return;
         }

         for (int i = start; i < arr.length; i++) {
             swap(arr, start, i);
             function(ans, arr, start + 1);
             swap(arr, start, i);
         }
     }

     public void swap(int[] arr, int a, int b) {
         int temp = arr[a];
         arr[a] = arr[b];
         arr[b] = temp;
     }

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> numList = new ArrayList<>();
        for (int num: nums) {
            numList.add(num);
        }

        if (numList.size() == 1) {
            result.add(numList);
            return result;
        }

        List<Integer> permutation = new ArrayList<>();
        backTracking(numList, permutation, result);
        return result;
    }

    public void backTracking(List<Integer> numList, List<Integer> permutation, List<List<Integer>> result) {
        if (numList.size() == 1) {
            permutation.add(numList.get(0)); //[1,2,3]
            result.add(new ArrayList<>(permutation));
            permutation.remove(permutation.size() - 1);
            return;
        }

        for (int i = 0; i < numList.size(); i++) {
            //i = 1
            Integer num = numList.get(i);
            permutation.add(num); //[1,3]
            backTracking(copyListAndRemove(numList, num), permutation, result);
            permutation.remove(permutation.size() - 1);
        }
    }

    public List<Integer> copyListAndRemove(List<Integer> list, Integer remove) {
        List<Integer> newList = new ArrayList<>(list);
        newList.remove(remove);
        return newList;
    }
}
