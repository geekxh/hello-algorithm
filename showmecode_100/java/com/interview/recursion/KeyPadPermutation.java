package com.interview.recursion;

/**
 * http://stackoverflow.com/questions/2344496/how-can-i-print-out-all-possible-letter-combinations-a-given-phone-number-can-re
 */
public class KeyPadPermutation {

    public void permute(int input[]) {
        char result[] = new char[input.length];
        permute(input,0,result);
    }

    private void permute(int input[], int pos, char result[]) {
        if (pos == input.length) {
            for (int i = 0; i < result.length; i++) {
                System.out.print(result[i]);
            }
            System.out.println();
            return;
        }

        char[] str = getCharSetForNumber(input[pos]);
        for (char ch : str) {
            result[pos] = ch;
            permute(input, pos+1, result);
        }
    }

    private char[] getCharSetForNumber(int num) {
        switch(num){
            case 1 : return "abc".toCharArray();
            case 2 : return "def".toCharArray();
            case 3: return "ghi".toCharArray();
            case 4: return "jkl".toCharArray();
            case 5: return "mno".toCharArray();
            case 6: return "pqrs".toCharArray();
            case 8: return "tuv".toCharArray();
            case 9: return "wxyz".toCharArray();
        }
        throw new IllegalArgumentException();
    }
    
    public static void main(String args[]){
        int input[] = {2,3,1,5};
        KeyPadPermutation kpp = new KeyPadPermutation();
        kpp.permute(input);
    }
}
