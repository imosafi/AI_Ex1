/*
 * this abstract class contains all functionality that
 * should be used in both search algorithms (IDS, UCS)
 * both IdsSearcher and UcsSearcher inherits from her
 */
public abstract class Searcher {
	private final int NeighborsSize = 8;
	private final int RNeighborIndex = 0;
	private final int RDNeighborIndex = 1;
	private final int DNeighborIndex = 2;
	private final int LDNeighborIndex = 3;
	private final int LNeighborIndex = 4;
	private final int LUNeighborIndex = 5;
	private final int UNeighborIndex = 6;
	private final int RUNeighborIndex = 7;

	char[][] matrix;

	public Searcher(char[][] matrix) {
		this.matrix = matrix;
	}

	abstract protected String ExecuteSearch();

	abstract protected int calculatePathPrice(Node goal);

	protected String getTextualPath(Node goal) {
		String path = "";
		Node ancestor, current;
		current = goal;

		while (current.ancestor != null) {
			if (current != goal)
				path = "-" + path;
			ancestor = current.ancestor;
			path = getPositionChange(current, ancestor) + path;
			current = ancestor;
		}
		path += " " + calculatePathPrice(goal);
		return path;
	}

	protected String getPositionChange(Node node, Node ancestor) {
		return getColumnChange(node, ancestor) + getRowChange(node, ancestor);
	}

	protected String getColumnChange(Node node, Node ancestor) {
		int columnChange = ancestor.j - node.j;
		return (columnChange == 0) ? "" : (columnChange == 1 ? "L" : "R");
	}

	protected String getRowChange(Node node, Node ancestor) {
		int rowChange = ancestor.i - node.i;
		return (rowChange == 0) ? "" : (rowChange == 1 ? "U" : "D");
	}

	protected Node[] GetValidNeighbors(int i, int j) {
		int maxLength = this.matrix.length - 1;
		Node[] neighborsArr = new Node[NeighborsSize];

		// check right neighbor
		if (j + 1 <= maxLength)
			if (matrix[i][j + 1] != 'W')
				neighborsArr[RNeighborIndex] = new Node(matrix[i][j + 1], i, j + 1);

		// right bottom diagonal
		if (i + 1 <= maxLength && j + 1 <= maxLength)
			if (matrix[i + 1][j + 1] != 'W' && matrix[i + 1][j] != 'W' && matrix[i][j + 1] != 'W')
				neighborsArr[RDNeighborIndex] = new Node(matrix[i + 1][j + 1], i + 1, j + 1);

		// check down neighbor
		if (i + 1 <= maxLength)
			if (matrix[i + 1][j] != 'W')
				neighborsArr[DNeighborIndex] = new Node(matrix[i + 1][j], i + 1, j);

		// left bottom diagonal
		if (i + 1 <= maxLength && j - 1 >= 0)
			if (matrix[i + 1][j - 1] != 'W' && matrix[i][j - 1] != 'W' && matrix[i + 1][j] != 'W')
				neighborsArr[LDNeighborIndex] = new Node(matrix[i + 1][j - 1], i + 1, j - 1);

		// check left neighbor
		if (j - 1 >= 0)
			if (matrix[i][j - 1] != 'W')
				neighborsArr[LNeighborIndex] = new Node(matrix[i][j - 1], i, j - 1);

		// left top diagonal
		if (i - 1 >= 0 && j - 1 >= 0)
			if (matrix[i - 1][j - 1] != 'W' && matrix[i - 1][j] != 'W' && matrix[i][j - 1] != 'W')
				neighborsArr[LUNeighborIndex] = new Node(matrix[i - 1][j - 1], i - 1, j - 1);

		// check up neighbor
		if (i - 1 >= 0)
			if (matrix[i - 1][j] != 'W')
				neighborsArr[UNeighborIndex] = new Node(matrix[i - 1][j], i - 1, j);

		// right top diagonal
		if (i - 1 >= 0 && j + 1 <= maxLength)
			if (matrix[i - 1][j + 1] != 'W' && matrix[i - 1][j] != 'W' && matrix[i][j + 1] != 'W')
				neighborsArr[RUNeighborIndex] = new Node(matrix[i - 1][j + 1], i - 1, j + 1);

		return neighborsArr;
	}
}
