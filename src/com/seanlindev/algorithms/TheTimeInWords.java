package com.seanlindev.algorithms;

public class TheTimeInWords {
    static final String[] oneDigit = new String[] {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
    static final String[] twoDigits = new String[] {"", "ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"};
    static final String twenty = "twenty";

    public static String timeInWords(int h, int m) {
        // Write your code here
        if (m == 0) {
            return numWord(h) + " o' clock";
        } else if (m == 15) {
            return "quarter past " + numWord(h);
        } else if (m == 30) {
            return "half past " + numWord(h);
        } else if (m > 0 && m < 30) {
            return numWord(m) + (m == 1 ? " minute " : " minutes ")  + "past " + numWord(h);
        } else if (m == 45) {
            return "quarter to " + numWord(h + 1);
        } else {
            return numWord(60 - m) + " minutes to " + numWord(h + 1);
        }

    }

    private static String numWord(int num) {
        if (num < 10) {
            return oneDigit[num];
        } else if (num >= 10 && num < 20) {
            return twoDigits[num - 9];
        } else if (num == 20) {
            return twenty;
        } else {
            return twenty + " " + oneDigit[num - 20];
        }
    }

}
