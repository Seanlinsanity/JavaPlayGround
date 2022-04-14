package com.seanlindev.algorithms;

public class AtLeastLengthKPlacesAway {
    public boolean kLengthApart(int[] nums, int k) {
        int lastOneIndex = -1;
        for (int i=0; i < nums.length; i++) {
            if (nums[i] == 1) {
                if (lastOneIndex != -1 && i - lastOneIndex <= k) {
                    return false;
                } else {
                    lastOneIndex = i;
                }
            }
        }

        return true;
    }
}
