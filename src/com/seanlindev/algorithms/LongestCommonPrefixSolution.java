package com.seanlindev.algorithms;

public class LongestCommonPrefixSolution {
    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) {
            return "";
        }

        if (strs.length == 1) {
            return strs[0];
        }

        String firstElement = strs[0];
        char[] ch = firstElement.toCharArray();
        int i = firstElement.length();
        while (i>=1) {
            String currentPrefix = firstElement.substring(0, i);
            Boolean isMatch = false;
            for (int j=1; j< strs.length; j++) {
                String element = strs[j];
                if (strs[j].startsWith(currentPrefix) == false) {
                    break;
                }

                if (j == strs.length - 1) {
                    isMatch = true;
                }
            }

            if (isMatch) {
                return currentPrefix;
            }

            i-=1;
        }

        return "";
    }
}
