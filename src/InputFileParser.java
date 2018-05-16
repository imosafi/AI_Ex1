import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

/*
 * this class is used to read and parse the input.txt file
 * I assume that input.txt location is at the main project folder
 */
public class InputFileParser {
	private static final int AlgoRowIndex = 0;
	private static final int MatrixSizeRowIndex = 1;
	private static final int MatrixBeginningRowIndex = 2;

	Path path;
	Definitions.AlgorithmType algorithmType;
	int matrixSize;
	char[][] inputMatrix;

	public InputFileParser() {
		path = FileSystems.getDefault().getPath("", "input.txt");
	}

	public void parseFileContent() {
		try {
			ArrayList<String> fileLines = (ArrayList<String>)Files.readAllLines(path);
			algorithmType = fileLines.get(AlgoRowIndex).contains("IDS") ? Definitions.AlgorithmType.IDS : Definitions.AlgorithmType.UCS;
			matrixSize = Integer.parseInt(fileLines.get(MatrixSizeRowIndex));
			inputMatrix = new char[matrixSize][matrixSize];
			for (int i = 0; i < matrixSize; i++)
				inputMatrix[i] = fileLines.get(i + MatrixBeginningRowIndex).toCharArray();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

}
