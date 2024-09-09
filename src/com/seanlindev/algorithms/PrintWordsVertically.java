package com.seanlindev.algorithms;

import java.util.ArrayList;
import java.util.List;

/*
Given a string s. Return all the words vertically in the same order in which they appear in s.
Words are returned as a list of strings, complete with spaces when is necessary. (Trailing spaces are not allowed).
Each word would be put on only one column and that in one column there will be only one word.



Example 1:

Input: s = "HOW ARE YOU"
Output: ["HAY","ORO","WEU"]
Explanation: Each word is printed vertically.
 "HAY"
 "ORO"
 "WEU"
Example 2:

Input: s = "TO BE OR NOT TO BE"
Output: ["TBONTB","OEROOE","   T"]
Explanation: Trailing spaces is not allowed.
"TBONTB"
"OEROOE"
"   T"
Example 3:

Input: s = "CONTEST IS COMING"
Output: ["CIC","OSO","N M","T I","E N","S G","T"]


Constraints:

1 <= s.length <= 200
s contains only upper case English letters.
It's guaranteed that there is only one space between 2 words.
 */
public class PrintWordsVertically {
    public List<String> printVertically(String s) {
        String[] words = s.split(" ");

        // edge case
        if (words.length == 1) { return List.of(words); }

        List<String> result = new ArrayList<>();
        int index = 0;
        while (true) {
            StringBuilder builder = new StringBuilder();
            StringBuilder subBuilder = new StringBuilder();
            for (String word: words) {
                String str = " ";
                if (index < word.length()) {
                    str = String.valueOf(word.charAt(index));
                }
                subBuilder.append(str);
                if (!str.equals(" ")) {
                    builder.append(subBuilder.toString());
                    subBuilder = new StringBuilder();
                }
            }

            if (builder.toString().isEmpty()) {
                break;
            }

            result.add(builder.toString());
            index += 1;
        }

        return result;
    }
}
