package com.seanlindev.algorithms;

public class JumpingOnTheCloudsRevisited {
    static int jumpingOnClouds(int[] c, int k) {
        int score = 100;
        int position = 0;

        while(true) {
            score -= 1;
            position = (position + k) % (c.length);
            if (c[position] == 1) {
                score -= 2;
            }

            if (position == 0) {
                break;
            }
        }

        return score;

    }
}
