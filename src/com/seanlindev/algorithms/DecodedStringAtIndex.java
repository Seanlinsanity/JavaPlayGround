package com.seanlindev.algorithms;

public class DecodedStringAtIndex {
    public String decodeAtIndex(String s, int k) {
        char[] ar= s.toCharArray();

        //calculate total size
        long size=0;


        for(int i=0;i<ar.length;i++)
        {
            char ch= ar[i];
            if(ch>='0' && ch<='9')
                size=size * (ch-'0');
            else
                size++;
        }

        // find and return the kth element
        for(int i=ar.length-1;i>=0;i--)
        {
            k %= size;

            char ch=ar[i];
            if(k==0 && !(ch>='0' && ch<='9'))
                return String.valueOf(ch);

            if(ch>='0' && ch<='9')
                size=size / (ch-'0');
            else
                size--;
        }

        return "";
    }
}
