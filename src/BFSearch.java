import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import org.omg.CosNaming.NamingContextExtPackage.AddressHelper;

public class BFSearch {
	
	public static void searchRepeats(int[] board, String mode) {
		PuzzleNode root = new PuzzleNode(new PuzzleState(board));
		Queue<PuzzleNode> queue = new LinkedList<PuzzleNode>();
		
		queue.add(root);
		
		performSearchRepeats(queue, mode);
	}
	
	public static void searchNoRepeats(int[] board, String mode) {
		PuzzleNode root = new PuzzleNode(new PuzzleState(board));
		Queue<PuzzleNode> queue = new LinkedList<PuzzleNode>();
		
		queue.add(root);
		
		performSearchNoRepeats(queue,mode);
	}
	
	/** Checks if search node has been evaluated
	 * 
	 * @param n
	 * @return
	 */
	private static boolean checkForRepeats(PuzzleNode n) {
		boolean returnValue = false;
		PuzzleNode checkNode = n;
		
		//While checkNode parent != null, check if it's equal to the node inputed
		while(n.getParent() != null && !returnValue) {
			if (n.getParent().getCurrentState().equals(checkNode.getCurrentState())) {
				returnValue = true;
			}
			n = n.getParent();
		}
		return returnValue;
	}

	private static void performSearchRepeats(Queue<PuzzleNode> q, String mode) {
		
		//While queue is not empty
		while(!q.isEmpty()) {
			PuzzleNode tempNode = q.poll();
		
			
			//if the root node is not the goal state
			if (!tempNode.getCurrentState().isGoal() && tempNode.getDepth() <= 10) {
				 
				//Generate the successors for the root
				ArrayList<PuzzleState> tempSuccessors = tempNode.getCurrentState().genSuccessors();
				
				//Loop through successors
				for (int i = 0; i < tempSuccessors.size();i++) {
					//Create a new node with cost of the next successor to the current node
					PuzzleNode newNode = new PuzzleNode(tempNode, tempSuccessors.get(i), tempNode.getCost() + tempSuccessors.get(i).findCost(), 0, tempSuccessors.get(i).getDirection());
					//add to queue
					q.add(newNode);
				}
				
			} else {
				//Goal state found
				Stack<PuzzleNode> solution = new Stack<PuzzleNode>();
				solution.push(tempNode);
				
				//Work back up to start state, until there is no parent @ root
				tempNode = tempNode.getParent();
				while(tempNode.getParent() != null) {
					solution.push(tempNode);
					tempNode = tempNode.getParent();
				}
				//push root to stack
				solution.push(tempNode);
				
				//loop size before popping
				int loopSize = solution.size();
				
				//Print solution
				System.out.println("*****FINAL SOLUTION******");
				
				if (mode.equals("-v")) {
					for (int i = 0; i < loopSize; i++) {
						tempNode = solution.pop();
						System.out.println(tempNode.getCurrentState().toString()+"\n\n");
					}
					System.out.println("Cost was: " + tempNode.getCost());
				} else {
					for (int i = 0; i < loopSize; i++) {
						tempNode = solution.pop();
						if(i > 0)
							System.out.println(tempNode.getCurrentState().getDirection());
					}
					System.out.println("Cost was: " + tempNode.getCost());
				}
				
				break;
				
			}
			
		}
		
	}
	
	private static void performSearchNoRepeats(Queue<PuzzleNode> q, String mode) {
		
		//While queue is not empty
		while(!q.isEmpty()) {
			PuzzleNode tempNode = q.poll();
		
			
			//if the root node is not the goal state
			if (!tempNode.getCurrentState().isGoal() && tempNode.getDepth() <= 10) {
				 
				//Generate the successors for the root
				ArrayList<PuzzleState> tempSuccessors = tempNode.getCurrentState().genSuccessors();
				
				//Loop through successors
				for (int i = 0; i < tempSuccessors.size();i++) {
					//Create a new node with cost of the next successor to the current node
					PuzzleNode newNode = new PuzzleNode(tempNode, tempSuccessors.get(i), tempNode.getCost() + tempSuccessors.get(i).findCost(), 0, tempSuccessors.get(i).getDirection());
					
					//add to queue if not repeated
					if (!checkForRepeats(newNode)) {
						q.add(newNode);
					}
				}
				
			} else {
				//Goal state found
				Stack<PuzzleNode> solution = new Stack<PuzzleNode>();
				solution.push(tempNode);
				
				//Work back up to start state, until there is no parent @ root
				tempNode = tempNode.getParent();
				while(tempNode.getParent() != null) {
					solution.push(tempNode);
					tempNode = tempNode.getParent();
				}
				//push root to stack
				solution.push(tempNode);
				
				//loop size before popping
				int loopSize = solution.size();
				
				//Print solution
				System.out.println("*****FINAL SOLUTION******");
				
				if (mode.equals("-v")) {
					for (int i = 0; i < loopSize; i++) {
						tempNode = solution.pop();
						System.out.println(tempNode.getCurrentState().toString()+"\n\n");
					}
					System.out.println("Cost was: " + tempNode.getCost());
				} else {
					for (int i = 0; i < loopSize; i++) {
						tempNode = solution.pop();
						if(i > 0)
							System.out.println(tempNode.getCurrentState().getDirection());
					}
					System.out.println("Cost was: " + tempNode.getCost());
				}
				
				break;
				
			}
			
		}
		
	}
	
}
