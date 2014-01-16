package com.sup;

import java.util.Vector;

public class KmpSubString {

	private Vector<Integer> computeLps(String string) {

		/* Compute the lps array wherein lps value at i is the
		 * length of the longest prefix of string which is also
		 * a suffix of the string.
		 */
		int i = 1;
		int lps_length = 0; /* lps_length is the lps value for the previous element of the pattern */
		Vector<Integer> lps = new Vector<Integer>();

		/* Base case, longest prefix which is also a suffix does not exist for a 1 character string */
		lps.add(0);

		while (i < string.length()) {
			if (string.charAt(i) == string.charAt(lps_length)) {
				i++;
				lps.add(lps_length + 1);
				lps_length++;
			} else {
				/* Special case : If the previous guy had a non-zero lps-value
				 * Check if the string ending at the current "i" position also
				 * have a lps value same as that of the previous guy. 
				 * Example : AAABAAAA at index of 7 has a lps value of 3 which is
				 * same as lps value at index 6.
				 */
				if (lps_length != 0) {
					lps_length = lps.get(lps_length - 1);
				} else {
					lps_length = 0;
					lps.add(0);
					i++;
				}
			}

		}
		for (int k = 0; k < lps.size(); k++) {
			System.out.println(lps.get(k));
		}
		return lps;
	}

	public int isSubstring(String text, String pattern) {
                /* First compute the longest-prefix-suffix array for each
                 * of the prefixes of the "pattern" string
                 */
		Vector<Integer> lps = computeLps(pattern);

		int textLen = text.length();
		int patternLen = pattern.length();
		Boolean startingPosValid = false;
		int startingPos = -1;

		int i = 0;
		int j = 0;

		if (textLen == 0 || patternLen == 0) {
			return -1;
		}

		if (lps == null) {
			return -1;
		}

		while (i < textLen && j < patternLen) {
			if (text.charAt(i) == pattern.charAt(j)) {
				if (startingPosValid == false) {
					startingPos = i;
					startingPosValid = true;
				}
				i++;
				j++;
			} else {
				if (j > 0) {
					j = lps.get(j - 1);
					if (j == 0)
						i++;
				} else {
					i++;
				}
				startingPosValid = false;
			}
		}

		if (j == patternLen) {
			return startingPos;
		} else {
			return -1;
		}
	}

}
