package day1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Main {

	public static void main(String[] args) {
		String fileName = "day1input.txt";

		int[] list1 = new int[1000];
		int[] list2 = new int[1000];

		int lnum = 0; // Keeps track of the number of valid lines read

		try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
			String line;
			while ((line = reader.readLine()) != null) {
				String[] parts = line.split("\\s+");

				if (parts.length == 2) {
					list1[lnum] = Integer.parseInt(parts[0]);
					list2[lnum] = Integer.parseInt(parts[1]);
					lnum++;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Only sort up to the last valid index (lnum - 1)
		sort(list1, 0, lnum - 1);
		sort(list2, 0, lnum - 1);

		System.out.println("Total Dist: " + findTotalDist(list1, list2, lnum));

		System.out.println("Similarity Score: " + findSimilarityScore(list1, list2, lnum));
	}

	// Merges two subarrays of a[]
	static void merge(int a[], int l, int m, int r) {
		int n1 = m - l + 1;
		int n2 = r - m;

		int L[] = new int[n1];
		int R[] = new int[n2];

		for (int i = 0; i < n1; ++i)
			L[i] = a[l + i];

		for (int j = 0; j < n2; ++j)
			R[j] = a[m + 1 + j];

		int i = 0, j = 0;
		int k = l;
		while (i < n1 && j < n2) {
			if (L[i] <= R[j]) {
				a[k] = L[i];
				i++;
			} else {
				a[k] = R[j];
				j++;
			}
			k++;
		}

		while (i < n1) {
			a[k] = L[i];
			i++;
			k++;
		}

		while (j < n2) {
			a[k] = R[j];
			j++;
			k++;
		}
	}

	// Main function that sorts a[l..r] using merge()
	static void sort(int a[], int l, int r) {
		if (l < r) {
			int m = (l + r) / 2;
			sort(a, l, m);
			sort(a, m + 1, r);
			merge(a, l, m, r);
		}
	}

	static int findTotalDist(int[] list1, int[] list2, int lnum) {
		int total = 0;

		// Set initial indices for the left and right parts of the arrays
		int l = 0;
		int r = lnum / 2;
		int l2 = r - 1;
		int r2 = lnum - 1;

		while (l <= l2 && r <= r2) {
			int ldiff = Math.abs(list1[l] - list2[l]);
			int rdiff = Math.abs(list1[r] - list2[r]);
			int l2diff = Math.abs(list1[l2] - list2[l2]);
			int r2diff = Math.abs(list1[r2] - list2[r2]);

			total += ldiff + rdiff + l2diff + r2diff;

			l++;
			r++;
			l2--;
			r2--;
		}

		return total;
	}

	static int findSimilarityScore(int[] l1, int[] l2, int lnum) {
		Map<Integer, Double> map = new HashMap<>();
		int score = 0;

		for (int i = 0; i < lnum; ++i) {
			// if

		}

		for (Map.Entry<Integer, Double> entry : map.entrySet()) {
			score += entry.getKey() * entry.getValue().getFreq1() * entry.getValue().getFreq2();
		}

		return score;
	}
}
