package com.seanlindev.algorithms;

public class StrangeCounter {
    public static long strangeCounter(long t) {
        // 3 * (2 ^ n - 1) / (2 - 1) = t
        double num = (Math.log(((double)(t) / 3.0) + 1.0) / Math.log(2));
        long cycleCount = (long)num;
        if (num - cycleCount > 0) {
            cycleCount++;
        }

        long endTime = (long)(3 * (Math.pow(2, cycleCount) - 1));
        return 1 + (endTime - t);
    }

}
