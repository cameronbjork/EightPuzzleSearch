import java.util.ArrayList;
import java.util.Arrays;

/** State of 8-Puzzle
 * 
 * @author Cameron Bjork & Joe Koller
 *
 */
public class PuzzleState {
	
	private final int PUZZLE_SIZE = 9;
	private int outOfPlace = 0;
	
	private int[] goal;
	
	private int[] currentBoard;
	
	public String direction;
	
	
	/**
	 * Constructor for PuzzleState
	 * 
	 * @param board - the board for new state to be create
	 */
	public PuzzleState(int[] board, int[] inGoal) {
		currentBoard = board;
		goal = inGoal;
		setOutOfPlace();
	}
	
	/** Checks if goal is met at current board
	 * 
	 * @return
	 */
	public boolean isGoal() {
		return Arrays.equals(currentBoard, goal);
	}
	
	/** Checks if current board equals inputed state
	 * 
	 * @param s - Inputed state
	 * @return true or false, whether states are equal
	 */
	public boolean equals(PuzzleState s) {
		return Arrays.equals(currentBoard,((PuzzleState) s).getCurrentBoard());
	}

	/**Returns current board
	 * 
	 * @return currentBoard
	 */
	private int[] getCurrentBoard() {
		return currentBoard;
	}

	/**
	 * Count the amount of tiles out of place
	 */
	public void setOutOfPlace() {
		for (int i = 0; i < currentBoard.length; i++) {
			if (currentBoard[i] != goal[i]) {
				outOfPlace++;
			}
		}
	}
	
    /** Locates 0 spot
     * 
     * @return - Index of the hole (0 spot), or -1 if the hole does not exist
     */
    public int getHole() {
        int holeIndex = -1;

        for (int i = 0; i < PUZZLE_SIZE; i++) {
            if (currentBoard[i] == 0)
                holeIndex = i;
        }
        return holeIndex;
    }
    
    
    /** Gets outOfPlace value
     * 
     * @return - outOfPlace value
     */
    public int getOutOfPlace() {
    	return outOfPlace;
    }
    
	/** Generates all possible boards for next move
	 * 
	 * @return successors - All successors of current board
	 */
	public ArrayList<PuzzleState> genSuccessors() {
		ArrayList<PuzzleState> successors = new ArrayList<PuzzleState>();
		int hole = getHole();
		
		//Try sliding tile up, position 0,1,2 are all on top
		if (hole != 0 && hole != 1 && hole != 2) {
			//Swaps up which is [-3] from current spot based on toString
			swap(hole - 3, hole, successors, "Up");
		}
		
		//Try sliding to right, position 2,5,8 are all on right side
		if (hole != 2 && hole != 5 && hole != 8) {
			swap(hole + 1, hole, successors, "Right");
		}
		
		//Try sliding down, position 6,7,8 are all the bottom
		if (hole != 6 && hole != 7 && hole != 8) {
			//Swaps down, which is [+3] from current spot based on toString
			swap(hole + 3, hole, successors, "Down");
		}
		
		//Try sliding tile to left, position 6,3,0 are all on left side
		if (hole != 6 && hole != 3 && hole != 0) {
			//Swaps the hole 1 to the left as a new state stored in the list
			swap(hole - 1, hole, successors, "Left");
		}
		
		return successors;
		
	}
	
	private void setDirection(String dir) {
		direction = dir;
	}
	
	public String getDirection() {
		return direction;
	}
	
	
	/** Swaps the goTo value with the hole, and stores back to the successors ArrayList
	 * 
	 * @param goTo - spot hole should go to
	 * @param hole - current hole location
	 * @param successors - List of all successors
	 */
	private void swap(int goTo, int hole, ArrayList<PuzzleState> successors, String direction) {
		int[] copy = copyBoard(currentBoard);
		int temp = copy[goTo];
		copy[goTo] = currentBoard[hole];
		copy[hole] = temp;
		
		PuzzleState newPuzzle = new PuzzleState(copy, goal);
		newPuzzle.setDirection(direction);
		
		successors.add(newPuzzle);
	}

	/** Copies the board, used in successors ArrayList in swap() and getSuccessors()
	 * @param state - State to copy
	 * @return returnState - Copied puzzle
	 */
	private int[] copyBoard(int[] state) {
		int[] returnState = new int[PUZZLE_SIZE];
		for (int i =0; i < PUZZLE_SIZE; i++) {
			returnState[i] = state[i];
		}
		return returnState;
	}
	
	public double findCost() {
		int cost = 0;
		for (int i = 0; i < this.currentBoard.length; i++) {
			//Check which spot the space is at
			int goalNumber;
			
			if (goal[i] == 0)
				goalNumber = 9;
			else
				goalNumber = goal[i];
			cost += Math.abs(currentBoard[i] - goalNumber);
		}
		return cost;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "Direction: " + getDirection() + "\n" +
			   currentBoard[0] + " | " + currentBoard[1] + " | " + currentBoard[2] + "\n" + "---------\n" + 
			   currentBoard[3] + " | " + currentBoard[4] + " | " + currentBoard[5] + "\n" + "---------\n" + 
			   currentBoard[6] + " | " + currentBoard[7] + " | " + currentBoard[8] + "\n";
	}
	

}
