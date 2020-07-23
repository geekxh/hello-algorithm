package com.interview.string;

public class RunLengthEncoding {

    private int updateCounter(char[] result, int current, int counter){
    
        int tempCounter = counter;
        int len = 1;
        while(tempCounter > 0){
            len = len*10;
            tempCounter /= 10;
        }
        len = len/10;
        
        while(counter > 0){
            result[current++] = (char)((counter/len) + '0');
            counter = counter%len;
            len /= 10;
        }
        return current;
    }
    
    public int encoding(char[] str,char[] result){

        char pickedChar = str[0];
        int current =0;
        int counter =1;
        for(int i=1; i < str.length; i++){
            if(str[i] == pickedChar){
                counter++;
            }
            else{
                result[current++] = pickedChar;
                current = updateCounter(result, current, counter);
                pickedChar = str[i];
                counter =1;
            }
        }
        result[current++] = pickedChar;
        current = updateCounter(result, current, counter);
        return current;
    }
    
    public static void main(String args[]){
        
        String str = "AAAAAAAAAAAAABBCDDEEEEE";
        char result[] = new char[str.length()*2];
        RunLengthEncoding rle = new RunLengthEncoding();
        int current = rle.encoding(str.toCharArray(),result);
        for(int i=0; i < current; i++){
            System.out.print(result[i]);
        }
    }
}
