package com.seanlindev.algorithms;

/*
You are given two integer arrays nums1 and nums2. We write the integers of nums1 and nums2 (in the order they are given) on two separate horizontal lines.

We may draw connecting lines: a straight line connecting two numbers nums1[i] and nums2[j] such that:

nums1[i] == nums2[j], and
the line we draw does not intersect any other connecting (non-horizontal) line.
Note that a connecting line cannot intersect even at the endpoints (i.e., each number can only belong to one connecting line).

Return the maximum number of connecting lines we can draw in this way.



Example 1:


Input: nums1 = [1,4,2], nums2 = [1,2,4]
Output: 2
Explanation: We can draw 2 uncrossed lines as in the diagram.
We cannot draw 3 uncrossed lines, because the line from nums1[1] = 4 to nums2[2] = 4 will intersect the line from nums1[2]=2 to nums2[1]=2.
Example 2:

Input: nums1 = [2,5,1,2,5], nums2 = [10,5,2,1,5,2]
Output: 3
Example 3:

Input: nums1 = [1,3,7,1,7,5], nums2 = [1,9,2,5,1]
Output: 2


Constraints:

1 <= nums1.length, nums2.length <= 500
1 <= nums1[i], nums2[j] <= 2000
 */
public class UncrossedLines {
    public int maxUncrossedLines(int[] nums1, int[] nums2) {
        int[][] dp = new int[nums1.length + 1][nums2.length + 1];
        for (int r = nums1.length - 1; r >= 0; r--) {
            for (int c = nums2.length - 1; c >= 0; c--) {
                int num1 = nums1[r];
                int num2 = nums2[c];
                int result = 0;
                if (num1 == num2) {
                    result = 1 + dp[r + 1][c + 1];
                } else {
                    result = Math.max(dp[r + 1][c], dp[r][c + 1]);
                }
                dp[r][c] = result;
            }
        }

        return dp[0][0];

        // Integer[][] dp = new Integer[nums1.length][nums2.length];
        // return maxUncrossedLinesRecursion(nums1, nums2, 0, 0, dp);
    }

    int maxUncrossedLinesRecursion(int[] nums1, int[] nums2, int index1, int index2, Integer[][] dp) {
        if (index1 == nums1.length || index2 == nums2.length) { return 0; }
        if (dp[index1][index2] != null) { return dp[index1][index2]; }

        int num1 = nums1[index1];
        int num2 = nums2[index2];
        int result = 0;
        if (num1 == num2) {
            result = Math.max(result, 1 + maxUncrossedLinesRecursion(nums1, nums2, index1 + 1, index2 + 1, dp));
        } else {
            result = Math.max(
                    maxUncrossedLinesRecursion(nums1, nums2, index1 + 1, index2, dp),
                    maxUncrossedLinesRecursion(nums1, nums2, index1, index2 + 1, dp)
            );
        }

        dp[index1][index2] = result;
        return result;
    }
}
