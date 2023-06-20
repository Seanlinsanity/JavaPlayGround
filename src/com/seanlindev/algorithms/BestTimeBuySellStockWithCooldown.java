package com.seanlindev.algorithms;

import java.util.HashMap;
import java.util.Map;

public class BestTimeBuySellStockWithCooldown {
    public int maxProfit(int[] prices) {
        Map<String, Integer> cache = new HashMap<>();
        return dfs(prices, cache, 0, true);
    }

    public int dfs(
            int[] prices,
            Map<String, Integer> cache,
            int index,
            boolean buying
    ) {
        if (index >= prices.length) {
            return 0;
        }
        String key = index + "-" + buying;

        if (cache.containsKey(key)) {
            return cache.get(key);
        }

        int buyOrsell = 0;
        if (buying) {
            buyOrsell = dfs(prices, cache, index + 1, !buying) - prices[index];
        } else {
            buyOrsell = dfs(prices, cache, index + 2, !buying) + prices[index];
        }
        int cooldown = dfs(prices, cache, index + 1, buying);

        cache.put(key, Math.max(buyOrsell, cooldown));
        return cache.get(key);
    }
}
