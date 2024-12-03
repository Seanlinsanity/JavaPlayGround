package com.seanlindev.algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
You are given an array of strings products and a string searchWord.

Design a system that suggests at most three product names from products after each character of searchWord is typed. Suggested products should have common prefix with searchWord. If there are more than three products with a common prefix return the three lexicographically minimums products.

Return a list of lists of the suggested products after each character of searchWord is typed.



Example 1:

Input: products = ["mobile","mouse","moneypot","monitor","mousepad"], searchWord = "mouse"
Output: [["mobile","moneypot","monitor"],["mobile","moneypot","monitor"],["mouse","mousepad"],["mouse","mousepad"],["mouse","mousepad"]]
Explanation: products sorted lexicographically = ["mobile","moneypot","monitor","mouse","mousepad"].
After typing m and mo all products match and we show user ["mobile","moneypot","monitor"].
After typing mou, mous and mouse the system suggests ["mouse","mousepad"].
Example 2:

Input: products = ["havana"], searchWord = "havana"
Output: [["havana"],["havana"],["havana"],["havana"],["havana"],["havana"]]
Explanation: The only word "havana" will be always suggested while typing the search word.


Constraints:

1 <= products.length <= 1000
1 <= products[i].length <= 3000
1 <= sum(products[i].length) <= 2 * 104
All the strings of products are unique.
products[i] consists of lowercase English letters.
1 <= searchWord.length <= 1000
searchWord consists of lowercase English letters.
 */
public class SearchSuggestionsSystem {
    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        Arrays.sort(products, (a, b) -> a.compareTo(b));

        List<List<String>> result = new ArrayList<>();
        int left = 0;
        int right = products.length - 1;
        for (int i = 0; i < searchWord.length(); i++) {
            String searchStr = searchWord.substring(0, i + 1);
            int found = products.length;
            while (left <= right) {
                int mid = (left + right) / 2;
                String current = products[mid];

                if (isPrefix(current, searchStr)) {
                    found = Math.min(found, mid);
                    right = mid - 1;
                } else if (current.compareTo(searchStr) > 0) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }

            List<String> searchResult = new ArrayList<>();
            for (int j = found; j < products.length; j++) {
                if (!isPrefix(products[j], searchStr) || searchResult.size() == 3) {
                    break;
                }

                searchResult.add(products[j]);
            }
            result.add(searchResult);
            left = found;
            right = products.length - 1;
        }

        return result;
    }

    boolean isPrefix(String str, String prefix) {
        if (str.length() < prefix.length()) { return false; }

        for (int i = 0; i < prefix.length(); i++) {
            if (str.charAt(i) != prefix.charAt(i)) {
                return false;
            }
        }

        return true;
    }
}
