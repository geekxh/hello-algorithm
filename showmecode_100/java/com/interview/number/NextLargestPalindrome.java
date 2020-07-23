package com.interview.number;

/**
 http://www.geeksforgeeks.org/given-a-number-find-next-smallest-palindrome-larger-than-this-number/
 */
public class NextLargestPalindrome {

    public void nextPalindrome(int num[]){
        
        int mid = num.length/2;
        
        int i = mid-1;
        int j  = num.length % 2 == 0 ? mid :  mid+1;
        
        while(i >= 0 && j <= num.length-1 && num[i] == num[j]){
            i--;
            j++;
        }
        
        boolean leftSmaller = false;
        if(i < 0 || num[i] < num[j]){
            leftSmaller = true;
        }
        
        if(!leftSmaller){
            while(i >= 0){
                num[j] = num[i];
                i--;
                j++;
            }
        }else{
            int carry =0;
            if(num.length % 2 != 0){
                num[mid] = num[mid] + 1;
                carry = num[mid]/10;
                num[mid] = num[mid]%10;
                j = mid+1;
            }else{
                j = mid;
                carry = 1;
            }
            i = mid-1;
            while(i >= 0){
                num[i] = carry + num[i];
                carry = num[i]/10;
                num[i] = num[i]%10;
                num[j] = num[i];
                i--;
                j++;
            }
        }
    }
    
    public void printArray(int num[]){
        for(int i=0; i < num.length; i++){
            System.out.print(num[i] + " ");
        }
    }
    
    public static void main(String args[]){
        
        NextLargestPalindrome nextLargestPalindrom = new NextLargestPalindrome();
        int num[] = {6,5,4,4,5,6};
        //handle case of 999 separately
        nextLargestPalindrom.nextPalindrome(num);
        nextLargestPalindrom.printArray(num);
    }
}
