package com.interview.random;

/**
 * Shuffle deck of cards
 * 150 qs 18.2
 */
public class ShuffleArray {

	public void shuffle(int arr[]){
		for(int i=arr.length-1; i>=0; i--){
			int random = (int)(Math.random()*(i+1)) ;
			System.out.print(random +  " ");
			swap(arr,random,i);
		}
	}
	
	private void swap(int arr[],int a,int b){
		int temp = arr[a];
		arr[a] = arr[b];
		arr[b] = temp;
	}
	
	public static void main(String args[]){
		int arr[] = {1,2,3,4,5,6,7,8};
		ShuffleArray sa = new ShuffleArray();
		sa.shuffle(arr);
		System.out.println();
		for(int i=0; i < arr.length; i++){
			System.out.print(arr[i] + " ");
		}
		
		for(int i=0; i < arr.length; i++){
			System.out.println((int)(Math.random()*10) + 1);
		}
	}
}
