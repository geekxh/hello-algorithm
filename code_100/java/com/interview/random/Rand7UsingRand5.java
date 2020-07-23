package com.interview.random;

public class Rand7UsingRand5 {

	public int rand7(){
		int r = (rand5()-1)*5 + rand5();
		while(r > 21){   // I just need to ignore [22, 25] 
			r = (rand5()-1)*5 + rand5();
		}
		return (r%7) + 1;
	}
	
	private int rand5(){
		return (int)(Math.ceil(Math.random()*5));
	}
	
	public static void main(String args[]){
		Rand7UsingRand5 rr = new Rand7UsingRand5();
		for(int i=0; i < 10; i++){
			System.out.print(rr.rand7());
		}
	}
}
