import java.io.FileWriter;
import java.io.IOException;

public class DataGenerator {

	static String[] operationsStep1 = new String[] { "A", "S", "RO", "RA" };
	static String[] operationsStep2 = new String[] { "P", "Q" };
	static String[] words = new String[] { "apple", "boy", "cat", "dog", "egg", "frog", "god" };

	public static String getFullOperation() {

		String operation = operationsStep1[(int) (Math.random() * operationsStep1.length)];
		String word = words[(int) (Math.random() * words.length)];
		String fullOperation = operation + " " + word;
		return fullOperation;
	}

	public static void main(String[] args) throws IOException {

		FileWriter fileWriter = new FileWriter("self-Generated-Test.txt");
		for (int i = 0; i <= Math.random() * 100; i++) {
			String content = getFullOperation();
			fileWriter.write(content);
			fileWriter.write(System.getProperty("line.separator"));
			System.out.println("'" + content + "'" + " has been written into the file");
		}
		for (int i = 0; i < operationsStep2.length; i++) {
			fileWriter.write(operationsStep2[i]);
			fileWriter.write(System.getProperty("line.separator"));
			System.out.println("'" + operationsStep2[i] + "'" + " has been written into the file");
		}
		fileWriter.close();
	}
}
