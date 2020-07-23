package com.interview.graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Date 03/04/2016
 * @author Tushar Roy
 *
 * Search for dictionary words in the board.
 * Idea is to use trie for the dictionary which keeps search
 * very efficient.
 *
 * https://leetcode.com/problems/word-search-ii/
 */
public class Boggle {

    public List<String> findWords(char[][] board, String[] words) {
        Trie t = new Trie();
        for (String word : words) {
            t.insert(word);
        }
        StringBuffer buff = new StringBuffer();
        Set<String> result = new HashSet<>();
        Set<Integer> visited = new HashSet<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                findWordsUtil(board, t, i, j, buff, visited, result, board[0].length);
            }
        }
        return new ArrayList<>(result);
    }

    private void findWordsUtil(char[][] board, Trie t , int i, int j, StringBuffer buff, Set<Integer> visited, Set<String> result, int col ) {
        if (i < 0 || j < 0 || i >= board.length || j >= board[i].length) {
            return;
        }

        int val = i*col + j;

        if (visited.contains(val)) {
            return;
        }

        buff.append(board[i][j]);
        String str = buff.toString();
        if(!t.startsWith(str)) {
            buff.deleteCharAt(buff.length() - 1);
            return;
        }
        visited.add(val);

        if(t.search(str)) {
            result.add(buff.toString());
        }

        findWordsUtil(board, t, i + 1, j, buff, visited, result, col);
        findWordsUtil(board, t, i, j + 1, buff, visited, result, col);
        findWordsUtil(board, t, i, j - 1, buff, visited, result, col);
        findWordsUtil(board, t, i - 1, j, buff, visited, result, col);

        buff.deleteCharAt(buff.length() - 1);
        visited.remove(val);
    }

    class TrieNode {
        TrieNode[] child = new TrieNode[26];
        boolean isWord;
        public TrieNode() {
        }
    }

    class Trie {
        private TrieNode root;

        public Trie() {
            root = new TrieNode();
        }

        // Inserts a word into the trie.
        public void insert(String word) {
            TrieNode current = root;
            for (int i = 0; i < word.length(); i++) {
                char ch = (char)(word.charAt(i) - 'a');

                if (current.child[ch] == null) {
                    current.child[ch] = new TrieNode();
                }
                current = current.child[ch];
            }
            current.isWord = true;
        }

        // Returns if the word is in the trie.
        public boolean search(String word) {
            TrieNode current = root;
            for (int i = 0; i < word.length(); i++) {
                char ch = (char)(word.charAt(i) - 'a');
                if (current.child[ch] == null) {
                    return false;
                }
                current = current.child[ch];
            }
            return current.isWord;
        }

        // Returns if there is any word in the trie
        // that starts with the given prefix.
        public boolean startsWith(String prefix) {
            TrieNode current = root;
            for (int i = 0; i < prefix.length(); i++) {
                char ch = (char)(prefix.charAt(i) - 'a');
                if (current.child[ch] == null) {
                    return false;
                }
                current = current.child[ch];
            }
            return true;
        }

        public void printTrie() {
            printTrieUtil(root);
        }

        private void printTrieUtil(TrieNode root) {
            if (root == null) {
                return;
            }
            for (int i = 0; i < root.child.length; i++) {
                if (root.child[i] != null) {
                    System.out.println((char)(i + 'a'));
                    printTrieUtil(root.child[i]);
                }
            }
        }
    }

    public static void main(String args[]) {
        char[][] board = {{'o','a','a','n'},{'e','t','a','e'},{'i','h','k','r'},{'i','f','l','v'}};
        String[] words = {"oath","pea","eat","rain"};
        Boggle boggle = new Boggle();
        List<String> result = boggle.findWords(board, words);
        result.stream().forEach(s -> System.out.println(s));
    }
}