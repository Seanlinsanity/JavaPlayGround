package com.seanlindev.algorithms;

import java.util.Arrays;

/*
The frequency of an element is the number of times it occurs in an array.

You are given an integer array nums and an integer k. In one operation, you can choose an index of nums and increment the element at that index by 1.

Return the maximum possible frequency of an element after performing at most k operations.



Example 1:

Input: nums = [1,2,4], k = 5
Output: 3
Explanation: Increment the first element three times and the second element two times to make nums = [4,4,4].
4 has a frequency of 3.
Example 2:

Input: nums = [1,4,8,13], k = 5
Output: 2
Explanation: There are multiple optimal solutions:
- Increment the first element three times to make nums = [4,4,8,13]. 4 has a frequency of 2.
- Increment the second element four times to make nums = [1,8,8,13]. 8 has a frequency of 2.
- Increment the third element five times to make nums = [1,4,13,13]. 13 has a frequency of 2.
Example 3:

Input: nums = [3,9,6], k = 2
Output: 1


Constraints:

1 <= nums.length <= 105
1 <= nums[i] <= 105
1 <= k <= 105
 */
public class FrequencyOfMostFrequentElement {
    public int maxFrequency(int[] nums, int k) {
        if (nums.length == 1) { return 1; }
        Arrays.sort(nums);

        long result = 0;
        long total = 0;
        int left = 0; //l=0
        int right = 0;
        while (right < nums.length) { //r=0 -> 1 -> 2 -> 3
            total += nums[right]; //total=1 -> 2 -> 3 -> 7
            while ((nums[right] * (right - left + 1L)) - total > k) { // 4 * 1 - 4 > 1
                total -= nums[left]; //total=6 -> 5 -> 4
                left += 1; //l=1 -> 2 -> 3
            }

            result = Math.max(result, right - left + 1L); // result=2
            right += 1;
        }

        return (int) result;

        // int result = 0;
        // int remain = k; //2
        // int right = nums.length - 1; //r=2
        // int left = right; //l=2
        // while (right >= 0) {
        //     int rightNum = nums[right]; //9
        //     while (left >= 0) {
        //         int diff = rightNum - nums[left]; //diff=3
        //         if (remain - diff >= 0) {
        //             result = Math.max(result, right - left + 1); //result=1
        //             remain -= diff;//remain=2
        //             left -= 1;//l=1
        //         } else {
        //             break;
        //         }
        //     }

        //     if (left < 0) {
        //         break;
        //     }

        //     remain += (nums[right] - nums[right - 1]) * (right - left - 1); //(9-6)*(2-1) //remain
        //     right -= 1;
        // }

        // return result;
    }
}
