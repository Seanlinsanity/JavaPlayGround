package com.seanlindev.algorithms;

/*
A trie (pronounced as "try") or prefix tree is a tree data structure used to efficiently store and retrieve keys in a dataset of strings. There are various applications of this data structure, such as autocomplete and spellchecker.

Implement the Trie class:

Trie() Initializes the trie object.
void insert(String word) Inserts the string word into the trie.
boolean search(String word) Returns true if the string word is in the trie (i.e., was inserted before), and false otherwise.
boolean startsWith(String prefix) Returns true if there is a previously inserted string word that has the prefix prefix, and false otherwise.


Example 1:

Input
["Trie", "insert", "search", "search", "startsWith", "insert", "search"]
[[], ["apple"], ["apple"], ["app"], ["app"], ["app"], ["app"]]
Output
[null, null, true, false, true, null, true]

Explanation
Trie trie = new Trie();
trie.insert("apple");
trie.search("apple");   // return True
trie.search("app");     // return False
trie.startsWith("app"); // return True
trie.insert("app");
trie.search("app");     // return True


Constraints:

1 <= word.length, prefix.length <= 2000
word and prefix consist only of lowercase English letters.
At most 3 * 104 calls in total will be made to insert, search, and startsWith.

 */
public class ImplementTrie {
    public class TrieNode {
        public char val;
        public TrieNode[] children;
        public boolean isEndOfWord = false;

        TrieNode() {
            val = 0;
            children = new TrieNode[26];
        }

        TrieNode(char _val) {
            val = _val;
            children = new TrieNode[26];
        }
    }


    public class Trie {

        private TrieNode root;

        public Trie() {
            root = new TrieNode();
        }

        public void insert(String word) {
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
            TrieNode current = root;
            for (char character: word.toCharArray()) {
                if (current.children[character - 'a'] == null) {
                    return false;
                }
                current = current.children[character - 'a'];
            }

            return current.isEndOfWord;
        }

        public boolean startsWith(String prefix) {
            TrieNode current = root;
            for (char character: prefix.toCharArray()) {
                if (current.children[character - 'a'] == null) {
                    return false;
                }
                current = current.children[character - 'a'];
            }

            return true;
        }
    }
}
