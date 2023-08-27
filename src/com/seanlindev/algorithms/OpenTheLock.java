package com.seanlindev.algorithms;

import java.util.*;

public class OpenTheLock {
    public int openLock(String[] deadends, String target) {
        Set<String> visited = new HashSet<>();
        for (String deadend: deadends) {
            visited.add(deadend);
        }

        //BFS
        Queue<String> lockQueue = new LinkedList<>();
        lockQueue.add("0000");
        int count = 0;
        while (!lockQueue.isEmpty()) {
            int size = lockQueue.size();
            for (int i = 0; i < size; i++) {
                String lock = lockQueue.remove();
                if (lock.equals(target)) {
                    return count;
                }

                if (visited.contains(lock)) {
                    continue;
                }

                visited.add(lock);
                List<String> children = getChildren(lock);
                for (String child: children) {
                    if (!visited.contains(child)) {
                        lockQueue.add(child);
                    }
                }
            }

            count += 1;
        }

        return -1;
    }

    List<String> getChildren(String lock) {
        List<String> result = new ArrayList<>();
        char[] chars = lock.toCharArray();
        for (int i = 0; i < 4; i++) {
            char numChar = chars[i];
            Integer num = Integer.valueOf(String.valueOf(numChar));
            Integer increase = (num + 1) % 10;
            chars[i] = String.valueOf(increase).charAt(0);
            result.add(new String(chars));
            Integer decrease = (num - 1 + 10) % 10;
            chars[i] = String.valueOf(decrease).charAt(0);
            result.add(new String(chars));
            chars[i] = numChar;
        }
        return result;
    }
}
