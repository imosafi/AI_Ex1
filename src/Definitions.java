
/*
 * this class is used to define useful enums
 */
public class Definitions {
	public enum AlgorithmType {
		IDS, UCS
	}

	public enum AreaType {
		S(0), G(0), R(1), D(3), H(10), W, UNDEF;

		private final int id;

		AreaType() {
			this.id = 0;
		}

		AreaType(int id) {
			this.id = id;
		}

		public int getValue() {
			return id;
		}
	}
}
