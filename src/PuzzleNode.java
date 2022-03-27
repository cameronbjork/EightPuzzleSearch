
public class PuzzleNode {
	
	private PuzzleState currentState;
	private PuzzleNode parent;
	private double cost; //cost to get to this state
	private double hCost; //heuristic
	private int depth;
	private String previousDirection;
	
	/** Constructor for root PuzzleNode
	 * 
	 * @param s - state passed in
	 */
	public PuzzleNode(PuzzleState s) {
		currentState = s;
		parent = null;
		cost = 0;
		hCost = 0;
		depth = 0;
	}
	
	public PuzzleNode(PuzzleNode previousNode, PuzzleState s, double c, double h, String direction) {
		parent = previousNode;
		currentState = s;
		cost = c;
		hCost = h;
		depth = parent.getDepth() + 1;
		setPreviousDirection(direction);
	}
	
	public int getDepth() {
		return depth;
	}
	
	private void setPreviousDirection(String direction1) {
		previousDirection = direction1;
	}

	public PuzzleState getCurrentState() {
		return currentState;
	}

	public PuzzleNode getParent() {
		return parent;
	}

	public double getCost() {
		return cost;
	}

	public double getHCost() {
		return hCost;
	}

}
