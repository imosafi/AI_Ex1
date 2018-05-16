import java.util.PriorityQueue;

/*
 * this class is used to execute UCS
 * inherits from class Searcher
 */
public class UcsSearcher extends Searcher {
	PriorityQueue<Node> queue;

	public UcsSearcher(char[][] matrix) {
		super(matrix);
	}

	@Override
	public String ExecuteSearch() {
		queue = new PriorityQueue<Node>();
		Node[] pricedNeighbors;
		Node root = new Node(matrix[0][0], 0, 0);
		queue.add(root);
		Node node;

		while (!queue.isEmpty()) {
			node = queue.remove();
			if (node.isNodeGoal())
				return getTextualPath(node);
			pricedNeighbors = getPricedNeighbors(GetValidNeighbors(node.i, node.j), node);
			insertNodesInOrder(pricedNeighbors);
		}
		return null;
	}

	private void insertNodesInOrder(Node[] pricedNeighbors) {
		for (int i = 0; i < pricedNeighbors.length; i++)
			if (pricedNeighbors[i] != null)
				queue.add(pricedNeighbors[i]);
	}

	private Node[] getPricedNeighbors(Node[] neighbors, Node ancestor) {
		for (int i = 0; i < neighbors.length; i++)
			if (neighbors[i] != null) {
				neighbors[i].ancestor = ancestor;
				neighbors[i].price = ancestor.price + neighbors[i].type.getValue();
			}
		return neighbors;
	}

	@Override
	protected int calculatePathPrice(Node goal) {
		Node current = goal;
		int price = 0;
		while (current.ancestor != null) {
			price += current.type.getValue();
			current = current.ancestor;
		}
		return price;
	}

}
