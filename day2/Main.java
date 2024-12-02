package day2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) {
		int safe = 0;
		String fileName = "day2/day2input.txt";

		try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
			String line;
			while ((line = reader.readLine()) != null) {
				int[] parts = Arrays.stream(line.split("\\s")).mapToInt(Integer::parseInt).toArray();

				if (isSafe(parts)) {
					safe++;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("Safe Reports: " + safe);
	}

	static boolean isSafe(int[] arr) {
		boolean increasing = false;
		boolean decreasing = false;

		if (arr[0] > arr[arr.length - 1]) {
			decreasing = true;
		} else {
			increasing = true;
		}

		for (int i = 1; i < arr.length; ++i) {
			int diff = arr[i] - arr[i - 1];

			if (Math.abs(diff) > 3 || diff == 0) {
				return false;
			}

			if (decreasing && diff > 0) {
				return false;
			}
			if (increasing && diff < 0) {
				return false;
			}
		}

		return true;
	}
}
