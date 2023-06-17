package com.seanlindev.algorithms;

import java.util.HashMap;
import java.util.Map;

/*
You are given an integer array nums and an integer target.

You want to build an expression out of nums by adding one of the symbols '+' and '-' before each integer in nums and then concatenate all the integers.

For example, if nums = [2, 1], you can add a '+' before 2 and a '-' before 1 and concatenate them to build the expression "+2-1".
Return the number of different expressions that you can build, which evaluates to target.



Example 1:

Input: nums = [1,1,1,1,1], target = 3
Output: 5
Explanation: There are 5 ways to assign symbols to make the sum of nums be target 3.
-1 + 1 + 1 + 1 + 1 = 3
+1 - 1 + 1 + 1 + 1 = 3
+1 + 1 - 1 + 1 + 1 = 3
+1 + 1 + 1 - 1 + 1 = 3
+1 + 1 + 1 + 1 - 1 = 3
Example 2:

Input: nums = [1], target = 1
Output: 1


Constraints:

1 <= nums.length <= 20
0 <= nums[i] <= 1000
0 <= sum(nums[i]) <= 1000
-1000 <= target <= 1000
 */
public class TargetSum {
    public int findTargetSumWays(int[] nums, int target) {
        Map<String, Integer> dp = new HashMap<>();
        return tracking(nums, 0, target, dp);
    }

    public int tracking(int[] nums, int index, int target, Map<String, Integer> dp) {
        if (index == nums.length - 1) {
            if (nums[index] == target || nums[index] == target * (-1)) {
                return target == 0 ? 2 : 1;
            }

            return 0;
        }

        String key = index + "-" + target;
        if (dp.get(key) != null) {
            return dp.get(key);
        }

        int currentNum = nums[index];
        int result = tracking(nums, index + 1, target - currentNum, dp) +
                tracking(nums, index + 1, target + currentNum, dp);

        dp.put(key, result);
        return result;
    }
}
