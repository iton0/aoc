import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Set;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
	public static void main(String[] args) {

		final String FILENAME = "day3/input.txt";
		final String MULRGX = "mul\\((\\d+),(\\d+)\\)";
		final String DORGX = "do\\(\\)";
		final String DONTRGX = "don't\\(\\)";

		Pattern mulPattern = Pattern.compile(MULRGX);
		Pattern doPattern = Pattern.compile(DORGX);
		Pattern dontPattern = Pattern.compile(DONTRGX);

		int total = 0;

		try (BufferedReader reader = new BufferedReader(new FileReader(FILENAME))) {
			String line;
			while ((line = reader.readLine()) != null) {
				TreeMap<Integer, String> map = new TreeMap<>();

				Matcher mulMatch = mulPattern.matcher(line);
				Matcher doMatch = doPattern.matcher(line);
				Matcher dontMatch = dontPattern.matcher(line);

				while (doMatch.find()) {
					map.put(doMatch.start(), "do");
				}

				while (dontMatch.find()) {
					map.put(dontMatch.start(), "dont");
				}

				while (mulMatch.find()) {
					int num1 = Integer.parseInt(mulMatch.group(1));
					int num2 = Integer.parseInt(mulMatch.group(2));
					map.put(mulMatch.start(), "" + (num1 * num2));
				}

				Set<Integer> keys = map.keySet();
				Integer[] keyArr = keys.toArray(new Integer[0]);

				boolean dont = false;
				for (int i = 0; i < keyArr.length; ++i) {
					int key = keyArr[i];
					String val = map.get(key);

					switch (val) {
						case "dont":
							dont = true;
							break;
						case "do":
							dont = false;
							break;
						default:
							if (!dont) {
								total += Integer.parseInt(val);
							}
							break;
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("Part 2 Result: " + total);
	}
}
