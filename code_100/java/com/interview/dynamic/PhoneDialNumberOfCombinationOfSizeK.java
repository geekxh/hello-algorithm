package com.interview.dynamic;

import java.util.HashMap;
import java.util.Map;

/**
 * Given the mobile numeric keypad. You can only press buttons that are
 * up,left,right or down to the current button.You are not allowed to press
 * bottom row corner buttons (i.e. * and # ).Given a N find out the number of
 * numbers possible of given length.
 * 
 * The idea is that to calculate for i all you have to do is add up i-1 values of all the neighbors.
 * e.g for i =2 and at 0 you need find i=1s of all neighbors including 0 and add them up.
 */
public class PhoneDialNumberOfCombinationOfSizeK {

	/**
	 * -1 in the input means don't use that position
	 * 
	 * @param k
	 * @param input
	 * @return
	 */
	public int numberOfCombination(int k, int input[][]) {
		if (input == null || input.length == 0) {
			throw new IllegalArgumentException();
		}
		Map<Integer, Integer> t = new HashMap<Integer, Integer>();
		Map<Integer, Integer> t1 = new HashMap<Integer, Integer>();

		for (int i = 0; i < input.length; i++) {
			for (int j = 0; j < input[i].length; j++) {
				if (input[i][j] < 0) {
					continue;
				}
				t1.put(input[i][j], 1);
			}
		}

		for (int x = 1; x < k; x++) {
			for (int i = 0; i < input.length; i++) {
				for (int j = 0; j < input[i].length; j++) {
					if (input[i][j] < 0) {
						continue;
					}
					findNeighborsAndPopulateMap(i, j, input, t, t1);
				}
			}
			copyMap(t, t1);
			t.clear();
		}
		int count = 0;
		for (int i : t1.keySet()) {
			count += t1.get(i);
		}
		return count;
	}

	private void copyMap(Map<Integer, Integer> t, Map<Integer, Integer> t1) {
		for (Integer i : t.keySet()) {
			int c = t.get(i);
			t1.put(i, c);
		}
	}

	private void findNeighborsAndPopulateMap(int x, int y, int input[][],
			Map<Integer, Integer> t, Map<Integer, Integer> t1) {
		updateMap(input, x, y, x - 1, y, t, t1);
		updateMap(input, x, y, x, y - 1, t, t1);
		updateMap(input, x, y, x, y, t, t1);
		updateMap(input, x, y, x, y + 1, t, t1);
		updateMap(input, x, y, x + 1, y, t, t1);
	}

	private void updateMap(int input[][], int x, int y, int i, int j,
			Map<Integer, Integer> t, Map<Integer, Integer> t1) {
		if (i < 0 || i >= input.length || j < 0 || j >= input[x].length
				|| input[i][j] < 0) {
			return;
		}
		Integer c = t.get(input[x][y]);
		if (c == null) {
			c = 0;
		}
		c += t1.get(input[i][j]);
		t.put(input[x][y], c);
	}

	public static void main(String args[]) {
		int input[][] = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 }, { -1, 0, -1 } };
		PhoneDialNumberOfCombinationOfSizeK pdn = new PhoneDialNumberOfCombinationOfSizeK();
		System.out.println(pdn.numberOfCombination(3, input));
	}

}
