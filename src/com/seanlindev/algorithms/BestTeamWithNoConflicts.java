package com.seanlindev.algorithms;

import java.util.Arrays;

/*
You are the manager of a basketball team. For the upcoming tournament, you want to choose the team with the highest overall score. The score of the team is the sum of scores of all the players in the team.

However, the basketball team is not allowed to have conflicts. A conflict exists if a younger player has a strictly higher score than an older player. A conflict does not occur between players of the same age.

Given two lists, scores and ages, where each scores[i] and ages[i] represents the score and age of the ith player, respectively, return the highest overall score of all possible basketball teams.



Example 1:

Input: scores = [1,3,5,10,15], ages = [1,2,3,4,5]
Output: 34
Explanation: You can choose all the players.
Example 2:

Input: scores = [4,5,6,5], ages = [2,1,2,1]
Output: 16
Explanation: It is best to choose the last 3 players. Notice that you are allowed to choose multiple people of the same age.
Example 3:

Input: scores = [1,2,3,5], ages = [8,9,10,1]
Output: 6
Explanation: It is best to choose the first 3 players.


Constraints:

1 <= scores.length, ages.length <= 1000
scores.length == ages.length
1 <= scores[i] <= 106
1 <= ages[i] <= 1000
 */
public class BestTeamWithNoConflicts {
    public int bestTeamScore(int[] scores, int[] ages) {
        int[][] players = new int[scores.length][2];
        for (int i = 0; i < scores.length; i++) {
            players[i] = new int[]{ scores[i], ages[i] };
        }

        Arrays.sort(players, (a, b) -> {
            if (a[0] == b[0]) {
                return Integer.compare(a[1], b[1]);
            }
            return Integer.compare(a[0], b[0]);
        });

        int maxScore = 0;
        int[] dp = new int[players.length];
        for (int i = 0; i < players.length; i++) {
            int age = players[i][1];
            dp[i] = players[i][0];
            for (int j = 0; j < i; j++) {
                if (players[j][0] == players[i][0] || players[j][1] <= age) {
                    dp[i] = Math.max(dp[i], dp[j] + players[i][0]);
                }
            }
            maxScore = Math.max(maxScore, dp[i]);
        }

        return maxScore;

//        // [age, score]
//        int[][] players = new int[ages.length][2];
//        for (int i = 0; i < ages.length; i++) {
//            players[i] = new int[] { ages[i], scores[i] };
//        }
//
//        Arrays.sort(players, (a, b) -> {
//            if (a[0] == b[0]) {
//                return Integer.compare(a[1], b[1]);
//            }
//            return Integer.compare(a[0], b[0]);
//        });
//        Integer[][] dp = new Integer[ages.length][ages.length + 1];
//        return bestTeamScoreRecursion(players, 0, ages.length, dp);
    }

    int bestTeamScoreRecursion(int[][] players, int index, int maxScoreIndex, Integer[][] dp) {
        if (index == players.length) { return 0; }
        if (dp[index][maxScoreIndex] != null) { return dp[index][maxScoreIndex]; }

        int[] player = players[index];
        int[] maxScorePlayer = maxScoreIndex == players.length ? new int[] {-1, -1} : players[maxScoreIndex];
        int result = 0;
        if (player[0] == maxScorePlayer[0] || player[1] >= maxScorePlayer[1]) {
            result = player[1] + bestTeamScoreRecursion(players, index + 1, player[1] > maxScorePlayer[1] ? index : maxScoreIndex, dp);
        }

        result = Math.max(result, bestTeamScoreRecursion(players, index + 1, maxScoreIndex, dp));
        dp[index][maxScoreIndex] = result;
        return result;
    }
}
