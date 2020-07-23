package com.interview.bits;

public class ByteAsStorage {

    void useByteAsBoolean(boolean[] visited){
        byte[] bytes = new byte[(int)(Math.ceil(visited.length*1.0/8))];
        for(int i=0; i < visited.length; i++){
            int row = i/8;
            int col = i%8;
            if(visited[i]){
                bytes[row] = (byte)(bytes[row] | (byte)(1<<col));
            }else{
                bytes[row] = (byte)(bytes[row] & ~(byte)(1<<col));
            }
        }
        
        for(int i=0; i < visited.length; i++){
            int row = i/8;
            int col = i%8;
            if((bytes[row] & 1<<col) >= 1){
                System.out.print("True");
            }else{
                System.out.print("False");
            }
        }
    }
    public static void main(String args[]){
        boolean visited[] = {true,false,true,true,false};
        ByteAsStorage bas = new ByteAsStorage();
        bas.useByteAsBoolean(visited);
    }
}
