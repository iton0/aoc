import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
	public static void main(String[] args) {
		int safe = 0;
		String fileName = "day2/day2input.txt";

		try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
			String line;
			while ((line = reader.readLine()) != null) {
				String[] strArr = line.split("\\s");

				ArrayList<Integer> list = new ArrayList<>();
				for (String num : strArr) {
					list.add(Integer.parseInt(num));
				}

				// First check if the report is already safe
				if (isSafe(list)) {
					safe++;
				} else if (canBeSafeByRemovingOne(list)) {
					safe++;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("Safe Reports: " + safe);
	}

	static boolean isSafe(ArrayList<Integer> list) {
		boolean increasing = true;
		boolean decreasing = true;

		for (int i = 1; i < list.size(); ++i) {
			int diff = list.get(i) - list.get(i - 1);
			// Check if difference is not between 1 and 3 or if the difference is 0
			if (Math.abs(diff) > 3 || diff == 0) {
				return false;
			}

			// Check if the list is strictly increasing or strictly decreasing
			if (diff < 0) {
				increasing = false;
			}
			if (diff > 0) {
				decreasing = false;
			}
		}

		return increasing || decreasing;
	}

	static boolean canBeSafeByRemovingOne(ArrayList<Integer> list) {
		// Try removing each element and check if the list becomes safe
		for (int i = 0; i < list.size(); ++i) {
			ArrayList<Integer> newList = new ArrayList<>(list);
			newList.remove(i);

			if (isSafe(newList)) {
				return true;
			}
		}

		return false;
	}
}
