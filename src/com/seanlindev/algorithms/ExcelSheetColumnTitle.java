package com.seanlindev.algorithms;

import java.util.ArrayList;
import java.util.List;

/*
Given an integer columnNumber, return its corresponding column title as it appears in an Excel sheet.

For example:

A -> 1
B -> 2
C -> 3
...
Z -> 26
AA -> 27
AB -> 28
...


Example 1:

Input: columnNumber = 1
Output: "A"
Example 2:

Input: columnNumber = 28
Output: "AB"
Example 3:

Input: columnNumber = 701
Output: "ZY"


Constraints:

1 <= columnNumber <= 231 - 1
 */
public class ExcelSheetColumnTitle {
    public String convertToTitle(int columnNumber) {
        List<Character> list = new ArrayList<>();
        while (columnNumber > 0) {
            int r = (columnNumber - 1) % 26;
            list.add((char)('A' + r));
            columnNumber = (columnNumber - 1) / 26;
        }

        StringBuilder builder = new StringBuilder();
        for (int i = list.size() - 1; i >= 0; i--) {
            builder.append(list.get(i));
        }

        return builder.toString();
    }
}
