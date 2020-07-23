package com.interview.graph;

import java.util.*;

/**
 * There is a new alien language which uses the latin alphabet. However, the order among letters
 * are unknown to you. You receive a list of words from the dictionary, where words are sorted
 * lexicographically by the rules of this new language. Derive the order of letters in this language.
 * 
 * https://leetcode.com/problems/alien-dictionary/
 */
public class AlientDictionary {
    public String alienOrder(String[] words) {
        Set<Character> allCharacters = new HashSet<>();
        Map<Character, Set<Character>> graph = buildGraph(words, new HashMap<>(), allCharacters);
        Deque<Character> stack = new LinkedList<>();
        Set<Character> visited = new HashSet<>();
        Set<Character> dfs = new HashSet<>();

        for (char ch : allCharacters) {
            if (topSortUtil(ch, stack, visited, dfs, graph)) {
                return "";
            }
        }

        StringBuffer buff = new StringBuffer();
        while (!stack.isEmpty()) {
            buff.append(stack.pollFirst());
        }
        return buff.toString();
    }

    private boolean topSortUtil(char vertex, Deque<Character> stack, Set<Character> visited, Set<Character> dfs, Map<Character, Set<Character>> graph) {
        if (visited.contains(vertex)) {
            return false;
        }
        visited.add(vertex);
        dfs.add(vertex);
        Set<Character> set = graph.get(vertex);
        if (set != null) {
            for (char neighbor : set) {
                if (dfs.contains(neighbor)) {
                    return true;
                }
                if (topSortUtil(neighbor, stack, visited, dfs, graph)) {
                    return true;
                }
            }
        }
        dfs.remove(vertex);
        stack.offerFirst(vertex);
        return false;
    }

    /**
     * degree is only used for BFS. Not for DFS.
     */
    private Map<Character, Set<Character>> buildGraph(String words[], Map<Character, Integer> degree, Set<Character> allCharacters) {
        getAllChars(words, degree, allCharacters);
        Set<Character> all = new HashSet<>(allCharacters);
        Map<Character, Set<Character>> graph = new HashMap<>();
        for (int i = 0; i < words.length - 1; i++) {
            String nextWord = words[i + 1];
            for (int k = 0; k < Math.min(words[i].length(), nextWord.length()); k++) {
                if (words[i].charAt(k) != nextWord.charAt((k))) {
                    all.remove(words[i].charAt(k));
                    Set<Character> set = graph.get(words[i].charAt(k));
                    if (set == null) {
                        set = new HashSet<>();
                        graph.put(words[i].charAt(k), set);
                    }
                    set.add(nextWord.charAt(k));
                    degree.compute(nextWord.charAt(k), (key, count) -> count + 1);
                    break;
                }
            }
        }
        for (char ch : all) {
            graph.put(ch, null);
        }
        return graph;
    }

    private void getAllChars(String words[], Map<Character, Integer> degree, Set<Character> allCharacters) {
        for (String word : words) {
            for (char ch : word.toCharArray()) {
                allCharacters.add(ch);
                degree.computeIfAbsent(ch, key -> 0);
            }
        }
    }

    public String alienOrder1(String words[]) {
        Map<Character, Integer> degree = new HashMap<>();
        Map<Character, Set<Character>> graph = buildGraph(words, degree, new HashSet<>());

        Queue<Character> zeroDegreeNodes = new LinkedList<>();
        for (Map.Entry<Character, Integer> entry : degree.entrySet()) {
            if (entry.getValue() == 0) {
                zeroDegreeNodes.offer(entry.getKey());
            }
        }

        StringBuilder result = new StringBuilder();

        while (!zeroDegreeNodes.isEmpty()) {
            char vertex = zeroDegreeNodes.poll();
            result.append(vertex);
            Set<Character> neighbors = graph.get(vertex);
            if (neighbors != null) {
                for (char neighbor : graph.get(vertex)) {
                    int count = degree.get(neighbor);
                    count--;
                    if (count == 0) {
                        zeroDegreeNodes.offer(neighbor);
                    } else {
                        degree.put(neighbor, count);
                    }
                }
            }
            graph.remove(vertex);
        }

        return graph.size() > 0 ? "" : result.toString();
    }

    public static void main(String args[]) {
        AlientDictionary ad =  new AlientDictionary();
        String[] words1 = {"zy","zx"};
        String[] words = {"wrt", "wrf", "er", "ett", "rftt"};
        String[] words2 = {"wrtkj","wrt"};
        String result = ad.alienOrder1(words2);
        System.out.print(result);


        //w -> e
        // e -> r
        //t -> f
        //r -> t
        //
    }
}
