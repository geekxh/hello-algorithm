package com.interview.misc;

/**
 * Given two times in four digits number e.g 10:10 is 1010 find difference between them
 * Test cases
 * Time 1 better be less than equal to time 2
 * First 2 digits better be between 0 and 23
 * Last 2 digits of number better be between 0 to 59
 */
public class DifferenceBetweenTwoTime {

    public int diff(int time1, int time2){
        if(time2 < time1){
            throw new IllegalArgumentException();
        }
        
        int hourDiff = time2/100 - time1/100 -1;
        int minDiff = time2%100 + (60 - time1%100);
        if(minDiff >= 60){
            hourDiff++;
            minDiff = minDiff - 60;
        }
        return hourDiff*100 + minDiff;
    }
    
    public static void main(String args[]){
        DifferenceBetweenTwoTime dbtt = new DifferenceBetweenTwoTime();
        int time = dbtt.diff(1400, 1645);
        System.out.println(time);
        time = dbtt.diff(1223, 1246);
        System.out.println(time);
        time = dbtt.diff(1500, 1620);
        System.out.println(time);
        time = dbtt.diff(344, 936);
        System.out.println(time);
        time = dbtt.diff(1000, 1234);
        System.out.println(time);
    }
}
