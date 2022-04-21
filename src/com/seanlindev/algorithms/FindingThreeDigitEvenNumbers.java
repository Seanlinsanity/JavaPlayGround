package com.seanlindev.algorithms;

import java.util.ArrayList;

public class FindingThreeDigitEvenNumbers {
    public int[] findEvenNumbers(int[] digits) {
        int[] count = new int[10];
        ArrayList<Integer> res = new ArrayList<Integer>();
        for(int d: digits) {
            count[d]++;
        }

        for(int i=1; i<=9; i++){
            if(count[i]==0)
                continue;
            count[i]--;
            for(int j=0; j<=9; j++){
                if(count[j]==0)
                    continue;
                count[j]--;
                for(int k=0; k<=9; k+=2){
                    if(count[k]==0)
                        continue;
                    res.add(i*100+j*10+k);
                }
                count[j]++;
            }
            count[i]++;
        }

        return res.stream().mapToInt(i->i).toArray();
    }
}
