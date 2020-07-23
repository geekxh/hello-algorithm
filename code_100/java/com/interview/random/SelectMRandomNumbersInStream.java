package com.interview.random;

/**
 * Reservoir Sampling
 * 150qs 18.3
 */
public class SelectMRandomNumbersInStream {

	public int[] selectRandom(int arr[],int m){
		int result[] = new int[m];
		for(int i=0; i < m ;i++){
			result[i] = arr[i];
		}
		
		for(int i=m ; i < arr.length; i++){
			int random = (int)(Math.random()*i) + 1;
			if(random <= m){
				result[random-1] = arr[i];
			}
		}
		return result;
	}
	
	public static void main(String args[]){
		SelectMRandomNumbersInStream srn = new SelectMRandomNumbersInStream();
		int arr[] = {1,2,3,4,5,6,7,8,9,10,11,12,13};
		int result[] = srn.selectRandom(arr, 5);
		for(int i=0; i < result.length; i++){
			System.out.print(result[i] + " ");
		}
	}
}
