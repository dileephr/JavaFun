package com.sup;

import java.util.ArrayList;
import java.util.List;

public class ParanthesisFun {
	int num_pairs;
	

	public ParanthesisFun(int num_pairs) {
		this.num_pairs = num_pairs;
	}

	public void print_solution() {

		solve(this.num_pairs);
		
	}

	private void solve(int num_pairs) {

		List<List<String>> masterSolution = new ArrayList<List<String>>();
		
		List<String> subSolution1 = null;
		List<String> subSolution2 = null;
		
		if (num_pairs == 0)
			return;

		/* Populating the Solution for base cases of 0 and 1 pair */
		List<String> solutionString1 = new ArrayList<String>();
		solutionString1.add("");
		masterSolution.add(solutionString1);

		List<String> solutionString2 = new ArrayList<String>();
		solutionString2.add("()");
		masterSolution.add(solutionString2);
		
		if (num_pairs == 1)
			return;

		/* Build on the solution using the base cases as seed
		 * Required solution is masterSolution at index "num_pairs"
		 */
		for (int k = 2; k <= num_pairs; k++) {
			List<String> solutionString = new ArrayList<String>();
			for (int space = 0; space < k; space++) {
				subSolution1 = masterSolution.get(space);
				subSolution2 = masterSolution.get(k - 1 - space);

				for (int i = 0; i < subSolution1.size(); i++) {
					for (int j = 0; j < subSolution2.size(); j++) {
						solutionString.add("(" + subSolution1.get(i) + ")"
								+ subSolution2.get(j));
					}
				}
			}
			masterSolution.add(solutionString);
		}
		
		for (int i = 0; i < masterSolution.get(num_pairs).size(); i++) {
			System.out.println(masterSolution.get(num_pairs).get(i));
		}

	}

	public static void main(String[] arg) {
		ParanthesisFun solution = new ParanthesisFun(8);

		solution.print_solution();
	}

}

