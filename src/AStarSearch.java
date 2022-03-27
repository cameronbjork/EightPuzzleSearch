import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class AStarSearch {
	public static void searchRepeats(int[] board, String mode, int[] goal) {
		PuzzleNode root = new PuzzleNode(new PuzzleState(board, goal));
		Queue<PuzzleNode> q = new LinkedList<PuzzleNode>();
		q.add(root);
		
		int searchCount = 1;
		
		while (!q.isEmpty()) {
			PuzzleNode temp = q.poll();
			
			//if current node is not the goal
			if(!temp.getCurrentState().isGoal() && temp.getDepth() <= 10) {
				//generate successors
				ArrayList<PuzzleState> tempSuccessors = temp.getCurrentState().genSuccessors();
				ArrayList<PuzzleNode> nodeSuccessors = new ArrayList<PuzzleNode>();
				
				//Print if mode is in verbose, if not add to nodeSuccessors
				for (int i = 0; i < tempSuccessors.size(); i++) {
					PuzzleNode checkedNode;
					checkedNode = new PuzzleNode(temp, tempSuccessors.get(i), temp.getCost() + tempSuccessors.get(i).findCost(), ((PuzzleState) tempSuccessors.get(i)).getOutOfPlace(), tempSuccessors.get(i).getDirection());
					if (mode.equals("-v")) {
						System.out.println(checkedNode.getCurrentState().toString());
					}
					nodeSuccessors.add(checkedNode);
				}
				
				PuzzleNode lowestNode = nodeSuccessors.get(0);
				
				for(int i=0; i < nodeSuccessors.size(); i++) {
					if (lowestNode.getFCost() > nodeSuccessors.get(i).getFCost()) {
						lowestNode = nodeSuccessors.get(i);
					}
				}
				
				int lowestValue = (int) lowestNode.getFCost();
				
				//Adds any nodes that have the same lowest value
				for (int i = 0; i < nodeSuccessors.size(); i++) {
					if (nodeSuccessors.get(i).getFCost() == lowestValue) {
						q.add(nodeSuccessors.get(i));
					}
				}
				searchCount++;
				
			} else {
				//Goal state found
				Stack<PuzzleNode> solution = new Stack<PuzzleNode>();
				solution.push(temp);
				temp = temp.getParent();
				System.out.println("******A* FINAL SOLUTION******");
				
				while(temp.getParent() != null) {
					solution.push(temp);
					temp = temp.getParent();
				}
				solution.push(temp);
				
				int loopSize = solution.size();
				
				for(int i = 0; i < loopSize; i++) {
					temp = solution.pop();
					System.out.println(temp.getCurrentState().toString() +"\n");
				}
				System.out.println("COST: " + temp.getCost());
				break;
				
			}
			
			
		}
	}
}
