package com.seanlindev.algorithms;

public class ExpressiveWords {
    public int expressiveWords(String s, String[] words) {
        int result = 0;

        for (String word: words) {
            if (isExpressive(s, word)) {
                result += 1;
            }
        }

        return result;
    }

    private boolean isExpressive(String expressiveWord, String word) {
        int expressStartIndex = 0;
        int wordStartIndex = 0;

        char[] expressChars = expressiveWord.toCharArray();
        char[] wordChars = word.toCharArray();

        while ((expressStartIndex < expressiveWord.length()) && (wordStartIndex < word.length())) {
            if (expressChars[expressStartIndex] != wordChars[wordStartIndex]) {
                return false;
            }

            int expressBeginIndex = expressStartIndex;
            char character = expressChars[expressStartIndex];
            while((expressStartIndex < expressiveWord.length()) && (expressChars[expressStartIndex] == character)) {
                expressStartIndex ++;
            }
            int expressCount = expressStartIndex - expressBeginIndex;

            int wordBeginIndex = wordStartIndex;
            while((wordStartIndex < word.length()) && (wordChars[wordStartIndex] == character)) {
                wordStartIndex ++;
            }
            int countWord = wordStartIndex - wordBeginIndex;

            if ((expressCount == countWord) || ((expressCount > countWord) && (expressCount >= 3))) {
                continue;
            } else {
                return false;
            }
        }

        if (expressStartIndex != expressiveWord.length() || wordStartIndex != word.length()) {
            return false;
        }

        return true;

    }
}
