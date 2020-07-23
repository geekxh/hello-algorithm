package com.interview.random;

public class RandomCountrySelectionByPopluation {

	public int getRandom(int []arr){
		int sum[] = new int[arr.length];
		sum[0] = arr[0];
		int n = arr[0];
		for(int i=1; i < sum.length; i++){
			sum[i] = sum[i-1] + arr[i];
			n += arr[i];
		}
		
		int ran = (int)(Math.random()*n + 1);
		
		int low = 0;
		int high = arr.length-1;
		int mid = (high + low)/2;
		while(true){
			if(sum[mid] >= ran && (mid-1 == -1 || sum[mid-1] < ran)){
				break;
			}
			if(sum[mid] > ran){
				high = mid-1;
			}else{
				low = mid+1;
			}
			mid = (high + low)/2;
		}
		return mid;
	}
	
	public static void main(String args[]){
		
		RandomCountrySelectionByPopluation rcsp = new RandomCountrySelectionByPopluation();
		int arr[] = {5,5,2,3,7,1};
		for(int i=0; i < 10; i++){
			System.out.print(rcsp.getRandom(arr));
		}
	}
}
