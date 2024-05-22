package com.seanlindev.algorithms;

import java.util.Arrays;

/*
There are several cards arranged in a row, and each card has an associated number of points. The points are given in the integer array cardPoints.

In one step, you can take one card from the beginning or from the end of the row. You have to take exactly k cards.

Your score is the sum of the points of the cards you have taken.

Given the integer array cardPoints and the integer k, return the maximum score you can obtain.



Example 1:

Input: cardPoints = [1,2,3,4,5,6,1], k = 3
Output: 12
Explanation: After the first step, your score will always be 1. However, choosing the rightmost card first will maximize your total score. The optimal strategy is to take the three cards on the right, giving a final score of 1 + 6 + 5 = 12.
Example 2:

Input: cardPoints = [2,2,2], k = 2
Output: 4
Explanation: Regardless of which two cards you take, your score will always be 4.
Example 3:

Input: cardPoints = [9,7,7,9,7,7,9], k = 7
Output: 55
Explanation: You have to take all the cards. Your score is the sum of points of all cards.


Constraints:

1 <= cardPoints.length <= 105
1 <= cardPoints[i] <= 104
1 <= k <= cardPoints.length
 */
public class MaximumPointsCards {
    public int maxScore(int[] cardPoints, int k) {
        // int totalSum = 0;
        // for (int point : cardPoints) {
        //     totalSum += point; // Calculate total sum of all points
        // }
        // int n = cardPoints.length;
        // int windowSize = n - k; // Calculate the size of the window (cards not to be selected)
        // int currentSum = 0;
        // for (int i = 0; i < windowSize; i++) {
        //     currentSum += cardPoints[i]; // Initial sum of the first window
        // }
        // int minSubArraySum = currentSum; // Initialize minimum sum with the first window's sum
        // for (int i = windowSize; i < n; i++) {
        //     currentSum += cardPoints[i] - cardPoints[i - windowSize]; // Slide the window and update sum
        //     minSubArraySum = Math.min(minSubArraySum, currentSum); // Update minimum sum found
        // }
        // return totalSum - minSubArraySum;

        int length = cardPoints.length - k;
        int current = 0; //0
        for (int i = 0; i < length; i++) {
            current += cardPoints[i]; //6
        }
        int result = current; //6
        int right = length; //3
        while (right < cardPoints.length) {
            current -= cardPoints[right - length]; //11
            current += cardPoints[right]; //12
            result = Math.min(result, current); //6
            right += 1; //6
        }

        int totalSum = Arrays.stream(cardPoints).sum();
        return totalSum - result;
    }
}
