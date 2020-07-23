package com.interview.misc;

class Cordinate{
    int x;
    int y;
}

/**
 * Given four points in any order determine if they form a square
 * Test cases
 * Less than or more than 4 points in input
 * Points could be in any quadrants e.it neg and pos allowed
 */
public class FourPointsFormSquare {

    public boolean isSquare(Cordinate[] cordinates){
        Cordinate startPoint = cordinates[0];
        int a1 = distanceSquare(startPoint, cordinates[1]);
        int a2 = distanceSquare(startPoint, cordinates[2]);
        int a3 = distanceSquare(startPoint, cordinates[3]);
        
        
        if(a1 == a2){ //then 0,3 is diagonal
            return compare(cordinates[3],cordinates[1],cordinates[2],a1,a3);
        }else if(a1 == a3){
            return compare(cordinates[2],cordinates[1],cordinates[3],a1,a2);
        }else if(a2 == a3){
            return compare(cordinates[1],cordinates[2],cordinates[3],a2,a1);
        }else{
            return false;
        }
    }
    
    private boolean compare(Cordinate startPoint, Cordinate point1, Cordinate point2,int len, int diag){
        if(2*len != diag){
            return false;
        }
        int a1 = distanceSquare(startPoint,point1);
        int a2 = distanceSquare(startPoint,point2);
        if(a1 != len || a2 != len){
            return false;
        }
        return true;
    }
    
    private int distanceSquare(Cordinate c1, Cordinate c2){
        return (int)(Math.pow(Math.abs(c1.x - c2.x) ,2) +
                Math.pow(Math.abs(c1.y-c2.y), 2));
    }
    
    public static void main(String args[]){
        FourPointsFormSquare fpf = new FourPointsFormSquare();
        Cordinate c1 = new Cordinate();
        c1.x = 2;
        c1.y = 2;
        Cordinate c2 = new Cordinate();
        c2.x = 6;
        c2.y = 2;
        Cordinate c3 = new Cordinate();
        c3.x = 2;
        c3.y = -2;
        Cordinate c4 = new Cordinate();
        c4.x = 6;
        c4.y = -2;
        Cordinate[] c = new Cordinate[4];
        c[0] = c1;
        c[1] = c2;
        c[2] = c3;
        c[3] = c4;
        System.out.println(fpf.isSquare(c));
    }
}
