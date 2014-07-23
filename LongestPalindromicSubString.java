
package com.sup;

public class LongestPalindromicSubString {
	public int findPalindrome(String string) {
		int maxLength = 0;
		int start = 0;

		/* solution[i][j] = true if there exists a palindrome of length i starting
		 * at jth character of the string
		 */
		Boolean solution[][] = new Boolean[string.length()+1][string.length()+1];

		/* A single character is a palindrome in itself */
		for (int i = 0; i < string.length(); i++) {
			solution[1][i] = true;
			maxLength = 1;
		}

		/* Compute palindromic substrings of length 2 */
		for (int i = 0; i < string.length() - 1; i++) {
			if (string.charAt(i) == string.charAt(i + 1)) {
				solution[2][i] = true;
				maxLength = 2;
				start = i;
			} else {
				solution[2][i] = false;
			}

		}

		/* Compute palindromic substrings of length 3 */
		for (int i = 2; i < string.length(); i++) {
			if (string.charAt(i - 2) == string.charAt(i)) {
				solution[3][i - 2] = true;
				maxLength = 3;
				start = i - 2;
			} else {
				solution[3][i-2] = false;
			}
		}

		/* Using length 1, 2 and 3 palindromic substrings, extend the solution
		 * to all the possible lengths of palindromic substrings.
		 */
		for (int i = 4; i <= string.length(); i++) {
			for (int j = 0; j < string.length() - i + 1; j++) {
				//System.out.println(i+ " " + j);
				if (string.charAt(j) == string.charAt(j + i - 1)
						&& solution[i - 2][j + 1] == true) {
					solution[i][j] = true;
					maxLength = i;
					start = j;
				} else {
					solution[i][j] = false;
				}
			}
		}
		return maxLength;
	}
	
}
