package com.interview.bits;

/**
 * http://www.geeksforgeeks.org/add-two-bit-strings/
 * http://www.geeksforgeeks.org/binary-representation-of-a-given-number/
 * http://www.geeksforgeeks.org/add-two-numbers-without-using-arithmetic-operators/
 */
public class AddTwoNumberInBinaryRepresentation {

    public int add(char[] num1,char[] num2){
        int index1 = num1.length -1;
        int index2 = num2.length -1;
        int carry = 0;
        int result = 0;
        int index = 0;
        while(index1 >= 0 && index2 >= 0){
            int r1 = num1[index1] - '0';
            int r2 = num2[index2] - '0';
            result = result | (r1^r2^carry)<<index;
            index1--;
            index2--;
            index++;
            carry = r1 & carry | r2 & carry | r1 & r2;
        }
        while(index1 >= 0){
            int r1 = num1[index1] - '0';
            result = result | (r1^carry)<<index;
            index1--;
            index++;
            carry = r1 & carry;
        }
        while(index2 >= 0){
            int r2 = num1[index2] - '0';
            result = result | (r2^carry)<<index;
            index2--;
            index++;
            carry = r2 & carry;
        }
        return result;
    }
    
    public int addTwoNumbersWithoutArithmeticOperator(int num1,int num2){
        int carry = 0;
        int result = 0;
        for(int i=0; i <=31; i++){
            int r1 = (num1 & 1<<i) != 0 ? 1 : 0;
            int r2 = (num2 & 1<<i) !=0 ? 1 : 0;
            
            result = result | (r1^r2^carry)<<i;
            if((r1 & r2) != 0 || (r1 & carry) != 0 || (r2 & carry) != 0){
                carry = 1;
            }else{
                carry = 0;
            }
        }
        return result;
    }
    
    public int addTwoNumbersWithoutArithmeticOperatorFaster(int num1,int num2){
        while(num2 != 0){
            int carry = num1 & num2;
            num1 = num1 ^ num2;
            num2 = carry << 1;
        }
        return num1;
    }
    
    public void printResult(int num){
        for(int i= 1<<31; i !=0 ; i = i>>>1){
            if((num & i) > 0){
                System.out.print("1");
            }else{
                System.out.print("0");
            }
        }
    }
    
    public static void main(String args[]){
        AddTwoNumberInBinaryRepresentation anp = new AddTwoNumberInBinaryRepresentation();
        char num1[] = "1010001110".toCharArray();
        char num2[] = "10011".toCharArray();
        int result = anp.add(num1, num2);
        System.out.println(anp.addTwoNumbersWithoutArithmeticOperator(296, 5662));
        System.out.println(anp.addTwoNumbersWithoutArithmeticOperatorFaster(296, 5662));
        anp.printResult(result);
    }
    
}
