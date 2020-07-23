package com.interview.recursion;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Date 07/05/2015
 * @author Tushar Roy
 * 
 * Given an input with duplicate characters generate a shuffle which does not have 
 * two duplicate characters together
 * 
 * Do regular permutation with repetition with additional constraint of keeping
 * duplicates away from each other.
 *
 */
public class FancyShuffle {

	public char[] shuffle(char input[]){
		char result[] = new char[input.length];
		
		//create a map of character to its frequency.
		Map<Character, Integer> map = new HashMap<Character,Integer>();
		for(int i=0; i < input.length; i++){
			Integer count  = map.putIfAbsent(input[i], 1);
			if(count != null) {
				count++;
				map.put(input[i], count);
			}
		}
		char newInput[] = new char[map.size()];
		int freq[] = new int[map.size()];
		
		//create a new char array and freq array from map.
		int index = 0;
		for(Entry<Character,Integer> entry :  map.entrySet()){
			newInput[index] = entry.getKey();
			freq[index] = entry.getValue();
			index++;
		}
		//assuming char with ASCII value 0 does not exists in the input
		shuffleUtil(newInput,freq, result, 0, (char)0);
		return result;
	}
	
	//regular permutation code. If we do find a permutation which satisfies the
	//constraint then return true and stop the process.
	private boolean shuffleUtil(char input[], int freq[], char result[], int pos, char lastVal) {
		if(pos == result.length){
			return true;
		}
		
		for(int i=0; i < input.length; i++){
			if(lastVal == input[i]) {
				continue;
			}
			if(freq[i] == 0) { 
				continue;
			}
			freq[i]--;
			result[pos] = input[i];
			if(shuffleUtil(input, freq, result, pos+1, input[i])){
				return true;
			}
			freq[i]++;
		}
		return false;
	}
	
	public static void main(String args[]) {
		FancyShuffle fs = new FancyShuffle();
		char result[] = fs.shuffle("bbcdaaaaa".toCharArray());
		for(char ch : result) { 
			System.out.print(ch);
		}
	}
}
