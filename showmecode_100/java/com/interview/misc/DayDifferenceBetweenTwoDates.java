package com.interview.misc;

/**
 * Number of days between two valid dates
 * The idea is to get number of days from 00/00/0000 for both the dates and find the
 * difference between them.
 */
public class DayDifferenceBetweenTwoDates {

    private static final int days[] = {31,28,31,30,31,30,31,31,30,31,30,31};
    
    public int diff(int year1,int month1, int day1,int year2, int month2,int day2){
        int days1 = year1*365 + day1;
        int days2 = year2*365 + day2;
        
        for(int i=0; i < month1-1; i++){
            days1 = days1 + days[i];
        }
    
        for(int i=0; i < month2-1; i++){
            days2 = days2 + days[i];
        }
        
        days1 = days1 + (year1-1)/4  - (year1 -1)/100 + (year1 -1 )/400; 
        days2 = days2 + (year2-1)/4  - (year2 -1)/100 + (year2 -1 )/400; 
        
        if(isLeapYear(year1) && month1 > 2){
            days1++;
        }
        if(isLeapYear(year2) && month2 > 2){
            days2++;
        }
        
        return days2 - days1;
    }
    
    
    public static void main(String args[]){
        DayDifferenceBetweenTwoDates dd = new DayDifferenceBetweenTwoDates();
        System.out.println(dd.diff(1945, 3, 7, 2009, 8, 31));
    }
    
    public boolean isLeapYear(int year){
        if(year % 400 == 0){
            return true;
        }
        if(year % 4 == 0 && year % 100 != 0){
            return true;
        }
        return false;
    }
}
