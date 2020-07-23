package com.interview.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MultiSpaceReplacement {

    public void replace(String str){
        Pattern pattern = Pattern.compile("^ +|  +| +$");
        Matcher matcher = pattern.matcher(str);
        System.out.println(matcher.replaceAll(""));
        
    }
    
    public static void main(String args[]){
        String str = "     This is Tushar  Roy  ";
        MultiSpaceReplacement mrs = new MultiSpaceReplacement();
        mrs.replace(str);
    }
}
