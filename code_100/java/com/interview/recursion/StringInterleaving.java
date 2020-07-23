package com.interview.recursion;

public class StringInterleaving {

    private void printArray(char[] str){
        for(int i=0; i < str.length; i++){
            System.out.print(str[i]);
        }
        System.out.println();
    }
    
    public void interleaving(char[] str1,char[] str2,int len1,int len2,int current, char []result){
        
        if(current == result.length){
            printArray(result);
            return;
        }

        if(len1 < str1.length){
            result[current] = str1[len1];
            interleaving(str1, str2, len1+1, len2, current+1, result);
        }
        if(len2 < str2.length){
            result[current] = str2[len2];
            interleaving(str1,str2,len1,len2+1,current+1,result);
        }
    }
    
    public static void main(String args[]){
        
        StringInterleaving si = new StringInterleaving();
        String str1 ="AB";
        String str2 = "CDE";
        char[] result = new char[str1.length() + str2.length()];
        si.interleaving(str1.toCharArray(), str2.toCharArray(), 0, 0, 0, result);
    }
    
}
