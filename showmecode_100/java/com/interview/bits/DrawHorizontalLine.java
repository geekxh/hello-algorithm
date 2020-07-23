package com.interview.bits;

/**
 * Exercise 5.8 150qs 
 */
public class DrawHorizontalLine {

    public void draw(byte[] screen, int width, int x1, int x2,int y){
        int pos1 = y*width + x1;
        int pos2 = y*width + x2;
        
        int start = pos1;
        while(start <= pos2){
            int row = start/8;
            int col = start%8;
            
            screen[row] = (byte)(screen[row] | 1<<col);
            start++;
        }
        printScreen(screen,width);
    }
    
    public void drawFaster(byte[] screen,int width,int x1,int x2,int y){
        int pos1 = y*width + x1;
        int pos2 = y*width + x2;
        
        int row1 = pos1/8;
        int col1= pos1%8;
        
        int row2 = pos2/8;
        int col2= pos2%8;

        int mask = ~((1<<col1) -1);
        screen[row1] = (byte)(screen[row1] | mask);
        
        mask = ((1<<(col2+1)) -1);
        screen[row2] = (byte)(screen[row2] | mask);
        
        while(row1 + 1 < row2){
            screen[row1+1] = (byte)(screen[row1+1] | 0xFF);
            row1++;
        }
        
        printScreen(screen,width);
    }
    
    private void printScreen(byte[] screen,int width){
        int w = width/8;
        for(int i=0; i < screen.length; i++){
            if(i%w == 0){
                System.out.println();
            }
            for(int j=0; j < 8; j++){
                if((screen[i] & 1<<j) != 0){
                    System.out.print("*");
                }else{
                    System.out.print("+");
                }
            }
        }
    }
    
    
    public static void main(String args[]){
        byte[] screen = {0,0,0,0,0,0,0,0,0};
        DrawHorizontalLine dwl = new DrawHorizontalLine();
        dwl.drawFaster(screen, 24,1 , 22, 1);
    }
}
