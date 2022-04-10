package com.seanlindev.algorithms;

import java.util.Arrays;

public class MinimumCostBuyingCandiesWithDiscount {
    public int minimumCost(int[] cost) {
        if (cost.length <= 2) {
            int sum = 0;
            for (int price: cost) {
                sum += price;
            }
            return sum;
        }

        Arrays.sort(cost);
        int sum = 0;
        int buyCount = 0;
        for (int i=cost.length - 1; i >= 0; i--) {
            if (buyCount == 2) {
                buyCount = 0;
                continue;
            }

            sum += cost[i];
            buyCount += 1;
        }

        return sum;
    }
}
