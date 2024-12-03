import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
	public static void main(String[] args) {

		String fileName = "day3/input.txt";

		int total = 0;

		String regex = "mul\\((\\d+),(\\d+)\\)";
		Pattern pattern = Pattern.compile(regex);

		try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
			String line;
			while ((line = reader.readLine()) != null) {
				Matcher matcher = pattern.matcher(line);
				while (matcher.find()) {
					int num1 = Integer.parseInt(matcher.group(1));
					int num2 = Integer.parseInt(matcher.group(2));
					total += num1 * num2;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Part 1 Result: " + total);
	}
}
