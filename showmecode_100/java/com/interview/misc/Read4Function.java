package com.interview.misc;

import java.util.Arrays;

/**
 * Date 03/26/2016
 * @author Tushar Roy
 * 
 * Given a reader which only reads 4 bytes implement a Reader which can read bytes of give size.
 *
 * Reference
 * Read N Characters Given Read4 II
 * https://leetcode.com/problems/read-n-characters-given-read4-ii-call-multiple-times/
 */

class Read4 {
    int read4(char[] buff) {
        buff[0] = 'a';
        buff[1] = 'b';
        buff[2] = 'c';
        buff[3] = 'd';
        return 4;
    }
}

public class Read4Function extends Read4{

    class Queue {
        int start;
        int end;
        int count;
        char[] data;
        int size;
        Queue(int size) {
            data = new char[size];
            this.size = size;
        }

        boolean isEmpty() {
            return count == 0;
        }

        void offer(char b) {
            data[start] = b;
            start = (start + 1)%size;
            count++;
        }

        char poll() {
            char d = data[end];
            end = (end + 1)%size;
            count--;
            return d;
        }
    }

    private final Queue queue;
    public Read4Function() {
        queue = new Queue(4);
    }

    public int read(char[] buf, int n) {
        int r = 0;
        while (!queue.isEmpty() && r < n) {
            buf[r++] = queue.poll();
        }

        if (r == n) {
            return r;
        }
        int index = 0;
        int readSize = 0;
        char[] input = null;
        do {
            input = new char[4];
            readSize = read4(input);
            index = 0;
            while(r < n && index < readSize) {
                buf[r++] = input[index++];
            }
        } while (readSize == 4 && r < n);

        while (index < readSize) {
            queue.offer(input[index++]);
        }
        return r;
    }

    public static void main(String args[]) {
        Read4Function rf = new Read4Function();
        char[] buff = new char[10];
        int size = rf.read(buff, 2);
        System.out.print(size);
        System.out.println(Arrays.toString(buff));
        size = rf.read(buff, 1);
        System.out.print(size);
        System.out.println(Arrays.toString(buff));
        size = rf.read(buff, 1);
        System.out.print(size);
        System.out.println(Arrays.toString(buff));
    }
}
