package com.seanlindev.algorithms;

public class ReverseOnlyLettersSolution {
    final int lowercaseAlphabetMinValue = 97;
    final int lowercaseAlphabetMaxValue = 122;
    final int upperAlphabetMinValue = 65;
    final int upperAlphabetMaxValue = 90;

    public String reverseOnlyLetters(String s) {
        if (s.length() <= 1) {
            return s;
        }

        final char[] characters = s.toCharArray();
        char[] result = s.toCharArray();

        final int length = s.length();
        int lastIndex =  length - 1;
        for (int i = 0; i < length; i++) {
            if (isAlphabet(characters[i])) {
                while(lastIndex >= 0) {
                    if (isAlphabet(characters[lastIndex])) {
                        result[lastIndex] = characters[i];
                        lastIndex -= 1;
                        break;
                    }
                    lastIndex -= 1;
                }
            }
        }
        return String.valueOf(result);
    }

    private boolean isAlphabet(char character) {
        final int value = (int)character;
        if ((value >= lowercaseAlphabetMinValue && value <= lowercaseAlphabetMaxValue) || (value >= upperAlphabetMinValue && value <= upperAlphabetMaxValue)) {
            return true;
        }
        return false;
    }
}
