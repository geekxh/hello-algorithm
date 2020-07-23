package com.interview.string;

/**
 * http://www.geeksforgeeks.org/an-in-place-algorithm-for-string-transformation/
 */
public class InPlaceTransformationOfString {

    private void reverse(char []str, int low, int high){
        while(low<high){
            swap(str,low,high);
            low++;
            high--;
        }
    }
    
    private void swap(char str[],int index1,int index2){
        char temp = str[index1];
        str[index1] = str[index2];
        str[index2] = temp;
    }
    
    public void cycleLeaderIteration(char []str,int start,int end){
        
        int power = 1;
        int index = start,new_index;
        int len = end -start +1;
        char temp,temp1;
        while(power < len){
            index = start+power;
            new_index = start;
            temp = str[index];
            while(new_index != power + start){
                if(index % 2 == 0){
                    new_index = (index+start)/2;
                }else{
                    new_index = len/2 + (index+start)/2;
                }
                temp1 = str[new_index];
                str[new_index] = temp;
                temp = temp1;
                index = new_index;
            }
            power *= 3;
        }
    }
    
    public void inPlaceTransformationImproved(char str[]){
        int low=0;
        int size = str.length;
        while(size > 0){
            int end = get3PowerK1(size);
            size = size-end;
            CycleLeaderIteration cli = new CycleLeaderIteration();
            cli.iterate(str, low, end + low-1);
            low = low+end;
        }
        size = str.length;
        low =0;
        int end = get3PowerK1(size);
        while(end < str.length){
            int nextEnd = get3PowerK1(str.length-end);
            reverse(str,end/2,end-1);
            reverse(str,end/2,end+nextEnd/2-1);
            reverse(str,end/2,end/2+nextEnd/2-1);
    //      size = str.length - (end + nextEnd);
            end = end + nextEnd;
        }
    }
    
    private int get3PowerK1(int size){
        int power = 1;
        while((power*3 +1)<= size){
            power = power*3;
        }
        return power+1;
    }
    
    public static void main(String args[]){
        char str[] = {'a','1','b','2','c','3','d','4','e','5','f','6','g','7','h','8','i','9','j','A','k','B','l','C','m','D'};
        InPlaceTransformationOfString ip = new InPlaceTransformationOfString();
        ip.inPlaceTransformationImproved(str);
        for(int i=0; i < str.length; i++){
            System.out.print(str[i]);
        }
    }
    
}
