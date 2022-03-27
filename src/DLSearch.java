import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class DLSearch {
	
	public static void searchRepeats(int[] board, String mode, int[] goal) {
		PuzzleNode root = new PuzzleNode(new PuzzleState(board, goal));
		Stack<PuzzleNode> stack = new Stack<>();
		
		stack.add(root);
		
		performSearchRepeats(stack, mode);
	}
	
	public static void searchNoRepeats(int[] board, String mode, int[] goal) {
		PuzzleNode root = new PuzzleNode(new PuzzleState(board, goal));
		Stack<PuzzleNode> stack = new Stack<>();
		
		stack.add(root);
		
		performSearchRepeats(stack, mode);
	}
	
	private static void performSearchRepeats(Stack<PuzzleNode> stack, String mode) {
		int searchCount = 1;
		
		while(!stack.isEmpty()) {
			PuzzleNode temp = stack.pop();
			if(temp.getDepth() <= 10) {
				if(!temp.getCurrentState().isGoal()) {
					ArrayList<PuzzleState> tempSuccessors = temp.getCurrentState().genSuccessors();
					
					for (int i=0; i< tempSuccessors.size();i++) {
						PuzzleNode newNode = new PuzzleNode(temp, tempSuccessors.get(i), temp.getCost() + tempSuccessors.get(i).findCost(),0,tempSuccessors.get(i).getDirection());
						if(mode.equals("-v"))
						stack.add(newNode);
					}
					searchCount++;
				} else {
					Stack<PuzzleNode> solutionPath = new Stack<PuzzleNode>();
					solutionPath.push(temp);
					temp = temp.getParent();
					
					while (temp.getParent() != null) {
						solutionPath.push(temp);
						temp = temp.getParent();
					}
					solutionPath.push(temp);
					
					int loopSize = solutionPath.size();
					
					for (int i = 0; i < loopSize; i++) {
						temp = solutionPath.pop();
						System.out.println(temp.getCurrentState().toString() + "\n");
					}
					break;
				}
			} else {
				System.out.println("NO SOLUTION: Maximum depth reached");
				break;
			}
		}
	}
}
