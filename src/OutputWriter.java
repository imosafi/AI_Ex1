import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/*
 * this class is used to write the output to output.txt file
 * I assume that output.txt location should be the main project folder
 */
public class OutputWriter {
	FileWriter fileWriter;
	BufferedWriter bufferedWriter;

	public OutputWriter() {
		try {
			fileWriter = new FileWriter("output.txt");
			bufferedWriter = new BufferedWriter(fileWriter);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void WriteToFile(String content) {
		try {
			bufferedWriter.write(content);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if (bufferedWriter != null)
					bufferedWriter.close();
				if (fileWriter != null)
					fileWriter.close();
			}
			catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
}
