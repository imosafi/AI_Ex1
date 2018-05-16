/*
 * this class is used to execute IDS
 * inherits from class Searcher
 */
public class IdsSearcher extends Searcher {
	private final int MaxSearchDepth = 15;

	public IdsSearcher(char[][] matrix) {
		super(matrix);
	}

	@Override
	protected String ExecuteSearch() {
		Node root = new Node(matrix[0][0], 0, 0);
		Node goalNode = null;
		for (int i = 0; i < MaxSearchDepth; i++) {
			goalNode = ExecuteBoundDfs(root, i);
			if (goalNode != null)
				break;
		}
		if (goalNode != null)
			return getTextualPath(goalNode);
		return "no path";
	}

	private Node ExecuteBoundDfs(Node node, int depth) {
		if (depth == 0 && node.isNodeGoal())
			return node;
		if (depth > 0) {
			Node[] childs = GetValidNeighbors(node.i, node.j);
			for (int i = 0; i < childs.length; i++) {
				if (childs[i] != null) {
					childs[i].ancestor = node;
					Node goalNode = ExecuteBoundDfs(childs[i], depth - 1);
					if (goalNode != null)
						return goalNode;
				}
			}
		}
		return null;
	}

	@Override
	protected int calculatePathPrice(Node goal) {
		int length = 1;
		Node current = goal;
		while (current.ancestor != null) {
			current = current.ancestor;
			length++;
		}
		return length;
	}

}
