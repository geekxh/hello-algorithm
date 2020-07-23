package com.interview.string;

public class CycleLeaderIteration {

    //assumption that size is going to be 3^k +1 from start to end
    public void iterate(char str[],int start,int end){
        int len = end - start +1;
        int power = 1;
        while(power < len){
            int index = power;
            int newIndex = -1;
            char temp = str[start+index];
            char temp1;
            while(newIndex != power){
                if(index % 2 ==0){
                    newIndex = index/2;
                }else{
                    newIndex = len/2 + index/2;
                }
                temp1 = str[start + newIndex];
                str[start+newIndex] = temp;
                temp = temp1;
                index = newIndex;
            }
            power = power*3;        
        }
    }
    
    public static void main(String args[]){
        String str = "1a2b3c4d5e6f7g8h9iAjBkClDmEn";
        char[] str1 = str.toCharArray();
        CycleLeaderIteration cli = new CycleLeaderIteration();
        cli.iterate(str1, 0, str1.length);
        for(char ch: str1){
            System.out.print(ch + " ");
        }
    }
    
}
