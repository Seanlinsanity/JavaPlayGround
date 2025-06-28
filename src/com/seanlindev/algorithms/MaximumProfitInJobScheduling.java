package com.seanlindev.algorithms;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
We have n jobs, where every job is scheduled to be done from startTime[i] to endTime[i], obtaining a profit of profit[i].

You're given the startTime, endTime and profit arrays, return the maximum profit you can take such that there are no two jobs in the subset with overlapping time range.

If you choose a job that ends at time X you will be able to start another job that starts at time X.



Example 1:



Input: startTime = [1,2,3,3], endTime = [3,4,5,6], profit = [50,10,40,70]
Output: 120
Explanation: The subset chosen is the first and fourth job.
Time range [1-3]+[3-6] , we get profit of 120 = 50 + 70.
Example 2:



Input: startTime = [1,2,3,4,6], endTime = [3,5,10,6,9], profit = [20,20,100,70,60]
Output: 150
Explanation: The subset chosen is the first, fourth and fifth job.
Profit obtained 150 = 20 + 70 + 60.
Example 3:



Input: startTime = [1,1,1], endTime = [2,3,4], profit = [5,6,4]
Output: 6


Constraints:

1 <= startTime.length == endTime.length == profit.length <= 5 * 104
1 <= startTime[i] < endTime[i] <= 109
1 <= profit[i] <= 104

 */
public class MaximumProfitInJobScheduling {
    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        int[][] jobs = new int[startTime.length][3];
        // O(N)
        for (int i = 0; i < startTime.length; i++) {
            jobs[i] = new int[]{ startTime[i], endTime[i], profit[i] };
        }

        // O(NlogN)
        Arrays.sort(jobs, (a, b) -> Integer.compare(a[0], b[0]));
        Map<Integer, Integer> cache = new HashMap<>();
        int[] dp = new int[startTime.length];
        // O(NlogN)
        for (int i = startTime.length - 1; i >= 0; i--) {
            int maxVal = jobs[i][2];
            int nextIndex = Math.max(i + 1, findNextIndex(jobs[i][1], jobs, cache));
            if (nextIndex < startTime.length) {
                // pick current
                maxVal = Math.max(maxVal, jobs[i][2] + dp[nextIndex]);
            }

            if (i + 1 < startTime.length) {
                // skip current
                maxVal = Math.max(maxVal, dp[i + 1]);
            }

            dp[i] = maxVal;
        }

        return dp[0];

        // Integer[][] dp = new Integer[startTime.length + 1][startTime.length + 1];
        // return jobSchedulingRecursion(-1, 0, jobs, dp);
    }

    int findNextIndex(int target, int[][] jobs, Map<Integer, Integer> cache) {
        if (cache.get(target) != null) { return cache.get(target); }
        int left = 0;
        int right = jobs.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (jobs[mid][0] >= target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        cache.put(target, left);
        return left;
    }

    int jobSchedulingRecursion(int idx1, int idx2, int[][] jobs, Integer[][] dp) {
        if (idx2 == jobs.length) { return 0; }
        if (dp[idx1 + 1][idx2] != null) { return dp[idx1 + 1][idx2]; }

        int result = 0;
        if (idx1 == -1 || jobs[idx2][0] >= jobs[idx1][1]) {
            result = Math.max(result,jobs[idx2][2] + jobSchedulingRecursion(idx2, idx2 + 1, jobs, dp));
        }

        result = Math.max(result, jobSchedulingRecursion(idx1, idx2 + 1, jobs, dp));
        dp[idx1 + 1][idx2] = result;
        return result;
    }
}
