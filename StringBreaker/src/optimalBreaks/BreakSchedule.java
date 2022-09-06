package optimalBreaks;

import java.util.ArrayList;
import java.util.*;

public class BreakSchedule {
	public static int[][] dptable;
	public static ArrayList<Integer> subList = new ArrayList<>();

	// Use this class to implement programs for Tasks 2 & 3. Your file must not
	// change the package name,
	// nor the class name. You must not change the names nor the signatures of the
	// two program stubs
	// in this file. You may augment this file with any other method or
	// variable/data declarations that you
	// might need to implement the dynamic programming strategy requested for Tasks
	// 2&3.
	// Make sure however that all your declarations are public.

	// Precondition: word is a string and breakList is an array of integers in
	// strictly increasing order
	// the last element of breakList is no more than the number of characters in
	// word.
	// Postcondition: Return the minimum total cost of breaking the string word into
	// |breakList|+1 pieces, where
	// the position of each each break is specified by the integers in breakList.
	// Refer to the assignment specification for how a single break contributes to
	// the cost.

	int totalCost(String word, ArrayList<Integer> breakList) { // TODO Complete for Task 2
		if (breakList == null || word.isEmpty() || breakList.isEmpty() || breakList.get(0) == word.length() - 1) {
			return 0;
		}
		dptable = new int[word.length()][word.length()];
		int result = 0;
		int i = 0;
		int j = word.length() - 1;
		for (int x = 0; x < dptable.length; x++) {
			for (int y = 0; y < dptable.length; y++) {
				dptable[x][y] = -1;
			}
		}
		if (breakList.size() == 1) {
			return j - i + 1;
		}
		result = calculateCost(i, j, breakList);

		return result;
	}

	int calculateCost(int i, int j, ArrayList<Integer> breakList) {
		int minCost = Integer.MAX_VALUE;
		int cost = 0;
		int k = 0;

		if (dptable[i][j] != -1) {
			return dptable[i][j];
		}
		for (int x = 0; x < breakList.size(); x++) {
			k = breakList.get(x);
			if (!(i > k || j <= k)) {
				subList.add(k);
				cost = (j - i + 1) + calculateCost(i, k, breakList) + calculateCost(k + 1, j, breakList);
				minCost = Math.min(cost, minCost);

			}

		}
		if (minCost == Integer.MAX_VALUE) {
			minCost = 0;

		}
		dptable[i][j] = minCost;
		return minCost;

	}

	// Precondition: word is a string and breakList is an array of integers in
	// strictly increasing order
	// the last element of breakList is no more than the number of characters in
	// word.
	// Postcondition: Return the schedule of breaks so that word is broken according
	// to the list of
	// breaks specified in breakList which yields the minimum total cost.

	ArrayList<Integer> breakSchedule(String word, ArrayList<Integer> breakList) { // TODO Complete for Task 3

		if (breakList == null || word.isEmpty() || breakList.isEmpty() || breakList.get(0) == word.length() - 1) {
			return null;
		}
		totalCost(word, breakList);
		if (breakList.size() == 1) {
			return breakList;
		}

		ArrayList<Integer> cutList = new ArrayList<>();
		int k = 0;
		int i = 0;
		int j = word.length() - 1;
		for (int x = 0; x < breakList.size(); x++) {
			k = breakList.get(x);
			if (j == k) {
				breakList.remove(x);
			}
		}
		cutList.addAll(subList.subList(subList.size() - breakList.size(), subList.size()));
		
		return cutList;
	}
}