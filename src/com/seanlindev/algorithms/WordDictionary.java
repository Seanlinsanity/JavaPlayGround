package com.seanlindev.algorithms;

/*
Design a data structure that supports adding new words and finding if a string matches any previously added string.

Implement the WordDictionary class:

WordDictionary() Initializes the object.
void addWord(word) Adds word to the data structure, it can be matched later.
bool search(word) Returns true if there is any string in the data structure that matches word or false otherwise. word may contain dots '.' where dots can be matched with any letter.


Example:

Input
["WordDictionary","addWord","addWord","addWord","search","search","search","search"]
[[],["bad"],["dad"],["mad"],["pad"],["bad"],[".ad"],["b.."]]
Output
[null,null,null,null,false,true,true,true]

Explanation
WordDictionary wordDictionary = new WordDictionary();
wordDictionary.addWord("bad");
wordDictionary.addWord("dad");
wordDictionary.addWord("mad");
wordDictionary.search("pad"); // return False
wordDictionary.search("bad"); // return True
wordDictionary.search(".ad"); // return True
wordDictionary.search("b.."); // return True


Constraints:

1 <= word.length <= 25
word in addWord consists of lowercase English letters.
word in search consist of '.' or lowercase English letters.
There will be at most 2 dots in word for search queries.
At most 104 calls will be made to addWord and search.
 */
public class WordDictionary {
    public class TrieNode {
        public char val;
        public TrieNode[] children;
        public boolean isEndOfWord;

        public TrieNode(char val) {
            this.val = val;
            this.children = new TrieNode[26];
            this.isEndOfWord = false;
        }
    }

    TrieNode root;

    public WordDictionary() {
        char val = 0;
        this.root = new TrieNode(val);
    }

    public void addWord(String word) {
        TrieNode current = root;
        for (char character: word.toCharArray()) {
            if (current.children[character - 'a'] == null) {
                current.children[character - 'a'] = new TrieNode(character);
            }
            current = current.children[character - 'a'];
        }

        current.isEndOfWord = true;
    }

    public boolean search(String word) {
        return searchWithAnyMatch(word, 0, root);
    }

    boolean searchWithAnyMatch(String str, int depth, TrieNode node) {
        char character = str.charAt(depth);

        if (character != '.') {
            TrieNode current = node.children[character - 'a'];
            if (current == null) {
                return false;
            }

            if (depth == str.length() - 1) {
                return current.isEndOfWord;
            }

            return searchWithAnyMatch(str, depth + 1, current);
        } else {
            for (TrieNode child: node.children) {
                if (depth == str.length() - 1) {
                    if (child != null && child.isEndOfWord) {
                        return true;
                    }
                } else {
                    if (child != null && searchWithAnyMatch(str, depth + 1, child)) {
                        return true;
                    }
                }
            }

            return false;
        }
    }
}
