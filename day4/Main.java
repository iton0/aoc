import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
	// TODO: uncomment when finished with test
	static final String FILENAME = "day4/input.txt";
	// static final String FILENAME = "day4/test.txt"; // 18 times this appears
	static final String WORD = "XMAS";

	public static void main(String[] args) {
		ArrayList<String> arr = new ArrayList<String>();

		int xmasAppears = 0;

		try (BufferedReader reader = new BufferedReader(new FileReader(FILENAME))) {
			String line;
			while ((line = reader.readLine()) != null) {
				arr.add(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		for (int i = 0; i < arr.size(); ++i) {
			for (int j = 0; j < arr.get(i).length(); ++j) {
				if (arr.get(i).charAt(j) == 'X') {
					boolean right = checkForWord(arr, i, j, 0, 1);
					boolean upright = checkForWord(arr, i, j, -1, 1);
					boolean up = checkForWord(arr, i, j, -1, 0);
					boolean upleft = checkForWord(arr, i, j, -1, -1);
					boolean left = checkForWord(arr, i, j, 0, -1);
					boolean downleft = checkForWord(arr, i, j, 1, -1);
					boolean down = checkForWord(arr, i, j, 1, 0);
					boolean downright = checkForWord(arr, i, j, 1, 1);

					if (right) {
						xmasAppears++;
					}

					if (upright) {
						xmasAppears++;
					}

					if (up) {
						xmasAppears++;
					}

					if (upleft) {
						xmasAppears++;
					}

					if (left) {
						xmasAppears++;
					}

					if (downleft) {
						xmasAppears++;
					}

					if (down) {
						xmasAppears++;
					}

					if (downright) {
						xmasAppears++;
					}
				}
			}
		}

		System.out.println("Part 1 Solution: " + xmasAppears);
		System.out.println("Part 2 Solution: " + xmasAppears);
	}

	static boolean checkForWord(ArrayList<String> arr, int curX, int curY, int x, int y) {

		for (int i = 1; i < 4; ++i) {
			try {
				char letter = arr.get(curX + (x * i)).charAt(curY + (y * i));
				if (WORD.charAt(i) != letter) {
					return false;
				}
			} catch (IndexOutOfBoundsException e) {
				return false;
			}
		}

		return true;

	}
}
