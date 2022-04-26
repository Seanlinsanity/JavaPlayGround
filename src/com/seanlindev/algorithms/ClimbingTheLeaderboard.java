package com.seanlindev.algorithms;

import java.util.ArrayList;
import java.util.List;

/*
ranked: 100 90 90 80 75 60
player: 50 65 77 90 102
*/

public class ClimbingTheLeaderboard {
    public static List<Integer> climbingLeaderboard(List<Integer> ranked, List<Integer> player) {
        int results[] = new int[player.size()];
        Integer rank = 1;
        Integer rankScore = ranked.get(0);
        int rankPosition = 0;

        for (int a = player.size() - 1; a >= 0; a--) {
            if (player.get(a) < rankScore) {
                while (rankPosition < ranked.size()) {
                    if (ranked.get(rankPosition) < rankScore) {
                        rank++;
                        rankScore = ranked.get(rankPosition);
                    }
                    if(player.get(a) >= ranked.get(rankPosition)) break;
                    rankPosition++;
                }
            }

            results[a] = rankPosition == ranked.size() ? rank + 1 : rank;
        }

        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int num: results) {
            list.add(Integer.valueOf(num));
        }
        return list;
    }
}
