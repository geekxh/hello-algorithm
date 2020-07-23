package com.interview.misc;

/**
 * Date 2/7/16
 * @author Tushar Roy
 *
 *
 * There are N children standing in a line. Each child is assigned a rating value.
 * You are giving candies to these children subjected to the following requirements:
 * Each child must have at least one candy.
 * Children with a higher rating get more candies than their neighbors.
 * What is the minimum candies you must give?
 *
 *
 * Time complexity - O(n)
 * Space complexity - O(1)
 *
 * Reference
 * https://leetcode.com/problems/candy/
 *
 *
 */
public class CandiesProblem {

    public int minCandies(int ratings[]) {

        int pointOfChange = 0;
        int totalCandies = 1;
        int currentCandy = 1;
        boolean isIndependent = true;
        int maxHeight = 0;
        int diff = 0;
        for (int i = 1; i < ratings.length; i++) {
            diff = 0;
            //if the rating of ith guy is greater than rating of i-1 guy
            //then give ith guy one more candy than i-1 guy.
            if (ratings[i] > ratings[i-1]) {
                currentCandy += 1;
            }
            //if rating of ith guy is same as i-1 guy then give ith guy only 1 candy
            //because in same rating ith guy can get less candy.
            //But also mark that if need we can keep increasing candy given to ith
            //guy without affecting i-1 guy's candy since it can also go more than
            //i-1th guy candy as they have same rating.
            else if (ratings[i] == ratings[i-1]) {
                isIndependent = true;
                pointOfChange = i;
                currentCandy = 1;
            }
            //if rating of ith guy is less than i-1th guy then
            //give ith guy one candy. If i-1th guy has only one candy
            //then all the guys till point of change needs to be given one more candy
            //which is what diff takes care off.
            //if point of change guy is independent then we are cool otherwise
            //point of change needs to point to one less number if we reach its height.
            else {
                if (currentCandy == 1) {
                    if (!isIndependent) {
                        if (i - pointOfChange == maxHeight - 1) {
                            pointOfChange--;
                        }
                    }
                }
                else {
                    maxHeight = currentCandy;
                    currentCandy = 1;
                    isIndependent = false;
                    pointOfChange = i;
                }
                diff = i - pointOfChange;
            }
            totalCandies += (diff + currentCandy);
        }
        return totalCandies;
    }

    public static void main(String args[]) {
        int input[] = {1,3,4,3,2,1};
        CandiesProblem cp = new CandiesProblem();
        System.out.println(cp.minCandies(input));
    }

}

