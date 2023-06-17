package com.seanlindev.algorithms;

import java.util.ArrayList;
import java.util.List;

/*
Design an algorithm to encode a list of strings to a string. The encoded string is then sent over the network and is decoded back to the original list of strings.

Please implement encode and decode

Example
Example1

Input: ["lint","code","love","you"]
Output: ["lint","code","love","you"]
Explanation:
One possible encode method is: "lint:;code:;love:;you"
Example2

Input: ["we", "say", ":", "yes"]
Output: ["we", "say", ":", "yes"]
Explanation:
One possible encode method is: "we:;say:;:::;yes"
 */
public class EncodeAndDecodeStrings {
    private char seperator = '-';

    //["lint","code","love","you"]
    public String encode(List<String> strs) {
        // write your code here
        StringBuilder strBuilder = new StringBuilder();
        for (String str: strs) {
            //"4-lint4-code4-love3-you"
            strBuilder.append(str.length())
                    .append(seperator)
                    .append(str);
        }

        return strBuilder.toString();
    }

    /*
     * @param str: A string
     * @return: decodes a single string to a list of strings
     */

    //"4-lint4-code4-love3-you"
    public List<String> decode(String str) {
        // write your code here
        List<String> result = new ArrayList<>();
        int startIndex = 0;
        int endIndex = 0;
        while (endIndex < str.length()) {
            //start = 0
            //end = 0 -> 1
            if (str.charAt(endIndex) != seperator) {
                endIndex += 1;
                continue;
            }

            int length = Integer.valueOf(str.substring(startIndex, endIndex)); //4
            String word = str.substring(endIndex + 1, endIndex + 1 + length); //(2,6)
            result.add(word);
            startIndex = endIndex + 1 + length; //6
            endIndex = startIndex; //6
        }
        return result;
    }
}
