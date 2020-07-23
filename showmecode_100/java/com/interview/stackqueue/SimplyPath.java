package com.interview.stackqueue;

import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 * Date 04/18/2016
 * @author Tushar Roy
 *
 * Given an absolute path for a file (Unix-style), simplify it.
 * For example,
 * path = "/home/", => "/home"
 * path = "/a/./b/../../c/", => "/c"
 *
 * Space complexity O(n)
 * Time complexity O(n)
 *
 * https://leetcode.com/problems/simplify-path/
 */
public class SimplyPath {

    public String simplifyPath(String path) {
        Deque<String> stack = new LinkedList<>();
        StringTokenizer token = new StringTokenizer(path, "/");
        while (token.hasMoreTokens()) {
            String tok = token.nextToken();
            if (tok.equals(".")) {
                continue;
            } else if (tok.equals("..")) {
                stack.pollFirst();
            } else {
                stack.offerFirst(tok);
            }
        }
        StringBuffer buff = new StringBuffer();
        if (stack.isEmpty()) {
            buff.append("/");
        }
        while(!stack.isEmpty()) {
            buff.append("/").append(stack.pollLast());
        }
        return buff.toString();
    }
    
    public static void main(String args[]){
        String absCurrentFolder = "/home/tusroy";
        SimplyPath mfc = new SimplyPath();
        System.out.println(mfc.simplifyPath(absCurrentFolder));
    }
}
