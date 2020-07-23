package com.interview.graph;

import java.util.*;

/**
 * Given two words (beginWord and endWord), and a dictionary's word list, find all shortest transformation sequence(s) from beginWord to endWord, such that:
 * Only one letter can be changed at a time
 * Each intermediate word must exist in the word list
 *
 * Solution -
 * Since we have to find all paths we need care about below extra stuff on top of regular BFS
 * 1) Maintain parent of every word to recreate the path
 * 2) Maintain 2 visited. First is for level and once the entire level is done then move it to visited.
 * 3) Also since we are looking result from beginWord to endWord switch them initially and go from endWord towards beginWord.
 *
 * https://leetcode.com/problems/word-ladder-ii/
 */
public class WordLadder {
    public List<List<String>> findLadders(String beginWord, String endWord, Set<String> wordList) {
        if (wordList == null || wordList.size() == 0) {
            return Collections.EMPTY_LIST;
        }
        String temp = endWord;
        endWord = beginWord;
        beginWord = temp;
        Map<String, List<String>> parent = new HashMap<>();
        Queue<String> queue1 = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        Set<String> levelVisited = new HashSet<>();
        List<List<String>> result = new ArrayList<>();
        parent.put(beginWord, null);
        queue1.offer(beginWord);
        visited.add(beginWord);
        boolean foundDestination = false;
        while (!queue1.isEmpty()) {
            while (!queue1.isEmpty()) {
                String word = queue1.poll();
                for (int i = 0; i < word.length(); i++) {
                    char wordArray[] = word.toCharArray();
                    for (char ch = 'a'; ch <= 'z'; ch++) {
                        wordArray[i] = ch;
                        String newWord = new String(wordArray);
                        if (!endWord.equals(newWord) && (!wordList.contains(newWord) || visited.contains(newWord))) {
                            continue;
                        }
                        List<String> parents = parent.get(newWord);
                        if (parents == null) {
                            parents = new ArrayList<>();
                            parent.put(newWord, parents);
                        }
                        parents.add(word);

                        levelVisited.add(newWord);
                        if (endWord.equals(newWord)) {
                            foundDestination = true;
                            break;
                        }
                    }
                }
            }
            if (foundDestination) {
                break;
            }
            for (String word : levelVisited) {
                queue1.offer(word);
                visited.add(word);
            }
            levelVisited.clear();
        }
        if (!foundDestination) {
            return Collections.EMPTY_LIST;
        } else {
            setParent(parent, beginWord, new ArrayList<>(), endWord, result);
        }
        return result;
    }

    private void setParent(Map<String, List<String>> parent, String startWord, List<String> path, String currentWord,
                           List<List<String>> result) {
        path.add(currentWord);
        if (startWord.equals(currentWord)) {
            result.add(new ArrayList<>(path));
            path.remove(path.size() - 1);
            return;
        }
        for (String p :  parent.get(currentWord)) {
            setParent(parent, startWord, path, p, result);
        }
        path.remove(path.size() - 1);
    }

    public static void main(String args[]) {
        String[] wordList = {"hot","dot","dog","lot","log"};
        Set<String> wordSet = new HashSet<>();
        wordSet.addAll(Arrays.asList(wordList));
        WordLadder wl = new WordLadder();
        List<List<String>> result = wl.findLadders("hit", "cog", wordSet);
        System.out.print(result);
    }
}
