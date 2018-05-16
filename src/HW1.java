/*
 * main class containing the main function
 * class name is identical to the exercise name
 */
public class HW1 {

	public static void main(String[] args) {
		InputFileParser inputFileParser = new InputFileParser();
		OutputWriter outputWriter = new OutputWriter();
		SearchManager searchManager;

		inputFileParser.parseFileContent();
		searchManager = new SearchManager(inputFileParser.inputMatrix, inputFileParser.algorithmType);

		String searchResult = searchManager.ExecuteSelectedSerch();
		outputWriter.WriteToFile(searchResult);
	}
}
