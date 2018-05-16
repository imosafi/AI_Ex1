import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public class Node implements Comparable<Node> {
	int i, j; // used to know where we are in the matrix
	Definitions.AreaType type;
	Node ancestor;
	int price; // price is used only for UCS
	final static AtomicLong seq = new AtomicLong();
	final long seqNum;

	static Map<Character, Definitions.AreaType> areaDictionary;

	static {
		areaDictionary = new HashMap<Character, Definitions.AreaType>();
		areaDictionary.put('G', Definitions.AreaType.G);
		areaDictionary.put('S', Definitions.AreaType.S);
		areaDictionary.put('R', Definitions.AreaType.R);
		areaDictionary.put('H', Definitions.AreaType.H);
		areaDictionary.put('D', Definitions.AreaType.D);
		areaDictionary.put('W', Definitions.AreaType.W);
	}

	public Node(char c, int i, int j) {
		ancestor = null;
		this.i = i;
		this.j = j;
		this.price = 0;
		seqNum = seq.getAndIncrement();
		try {
			type = charToAreaType(c);
			if (type == Definitions.AreaType.UNDEF)
				throw new Exception("Area Type not defined");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int getPrice() {
		return this.price;
	}

	public boolean isNodeGoal() {
		return this.type == Definitions.AreaType.G;
	}

	private Definitions.AreaType charToAreaType(char c) {
		Definitions.AreaType type = areaDictionary.get(c);
		if (type != null)
			return type;
		return Definitions.AreaType.UNDEF;
	}

	@Override
	public int compareTo(Node other) {
		if (price > other.price)
			return 1;
		else if (price < other.price)
			return -1;
		else
			return (seqNum < other.seqNum ? -1 : 1);
	}

}
