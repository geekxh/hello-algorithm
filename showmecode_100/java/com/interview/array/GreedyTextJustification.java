package com.interview.array;

import java.util.ArrayList;
import java.util.List;

/**
 * Date 03/12/2016
 * @author Tushar Roy
 *
 * Given an array of words and a length L, format the text such that each line has exactly L characters and is fully
 * (left and right) justified.
 * You should pack your words in a greedy approach; that is, pack as many words as you can in each line.
 *
 * Time complexity - O(n) where n is the number of words
 * Space complexity - O(1)
 *
 * https://leetcode.com/problems/text-justification/
 */
public class GreedyTextJustification {
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < words.length; ) {
            int total = words[i].length();
            int j = i + 1;
            StringBuffer buff = new StringBuffer();
            buff.append(words[i]);
            while(j < words.length && total + words[j].length() + 1 <= maxWidth) {
                total += words[j].length() + 1;
                j++;
            }
            int remaining = maxWidth - total;
            //since j is not word length means its not a last line. So pad accordingly.
            if (j != words.length) {
                int count = j - i - 1;
                if (count == 0) {
                    padSpace(buff, remaining);
                } else {
                    int q = remaining/count;
                    int r = remaining % count;
                    for (int k = i + 1; k < j; k++) {
                        padSpace(buff, q);
                        if (r > 0) {
                            buff.append(" ");
                            r--;
                        }
                        buff.append(" ").append(words[k]);
                    }
                }
            } else { //if it is last line then left justify all the words.
                for (int k = i + 1; k < j; k++) {
                    buff.append(" ").append(words[k]);
                }
                padSpace(buff, remaining);
            }
            result.add(buff.toString());
            i = j;
        }
        return result;
    }

    private void padSpace(StringBuffer buff, int count) {
        for (int i = 0; i < count; i++) {
            buff.append(" ");
        }
    }

    public List<String> fullJustify1(String[] words, int maxWidth) {
       int currentLength = 0;
        int prevIndex = 0;
        List<String> result = new ArrayList<>();

        for (int i = 0; i < words.length; i++) {
            //keep track of length for currentLine. For first word only use length while for remaining words use
            //lenght + 1 since there will be a space b/w them.
            currentLength += (words[i].length() + (i == prevIndex ? 0 : 1));

            //if currentLength exceeds maxWidth it means currentWord cannot in same line.
            if (currentLength > maxWidth) {
                //subtract current word's length from currentLength
                currentLength -= words[i].length() + 1;
                StringBuffer builder = new StringBuffer();

                //find number of words which will find in the line.
                int gaps = i - 1 - prevIndex;
                if (gaps > 0) {//if more than one word fits in the gap.
                    //available number of spaces is below. Subtract gaps because that many spaces have been accounted
                    //for in currentLength.
                    int availableSpace = maxWidth - currentLength + gaps;

                    //first remaining gaps get one extra space.
                    int remaining = availableSpace % gaps;

                    //every gap gets this much extra space.
                    int atleast = availableSpace / gaps;
                    for (int j = prevIndex; j <= i - 2; j++) {
                        builder.append(words[j]);
                        padSpace(builder, atleast);
                        if (j - prevIndex < remaining) {
                            padSpace(builder, 1);
                        }
                    }
                    builder.append(words[i - 1]);
                } else { //if only one word can fit in a one line then left specify it.
                    builder.append(words[i - 1]);
                    padSpace(builder, maxWidth - words[i - 1].length());
                }
                result.add(builder.toString());
                prevIndex = i;
                currentLength = words[i].length();
            }
        }
        //handle the last line. Left justify the remaining words
        if (prevIndex < words.length) {
            StringBuffer builder = new StringBuffer();
            int count = 0;
            while (prevIndex < words.length) {
                builder.append(words[prevIndex]).append(" ");
                count += words[prevIndex].length() + 1;
                prevIndex++;
            }
            count--;
            //delete extra space added by above for looop.
            builder.deleteCharAt(builder.length() - 1);
            //whatever spae is left just put it at the end.
            padSpace(builder, maxWidth - count);
            result.add(builder.toString());
        }
        return result;
    }

    public static void main(String args[]) {
        String[] input = {"What","must","be","shall","be."};
        GreedyTextJustification gtj = new GreedyTextJustification();
        List<String> result = gtj.fullJustify(input, 12);
        System.out.print(result);
    }
}
