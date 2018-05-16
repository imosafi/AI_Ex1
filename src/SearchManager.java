/*
 * this class determines the needed search algorithm and execute it
 * according to input
 */
public class SearchManager {
	Definitions.AlgorithmType algorithmType;
	char[][] matrix;
	Searcher idsSearcher;
	Searcher ucsSearcher;

	public SearchManager(char[][] matrix, Definitions.AlgorithmType algoritmType) {
		this.algorithmType = algoritmType;
		this.matrix = matrix;
		idsSearcher = new IdsSearcher(matrix);
		ucsSearcher = new UcsSearcher(matrix);
	}

	public String ExecuteSelectedSerch() {
		String searchResult;
		if (algorithmType == Definitions.AlgorithmType.IDS)
			searchResult = idsSearcher.ExecuteSearch();
		else
			searchResult = ucsSearcher.ExecuteSearch();
		return searchResult;
	}

}
