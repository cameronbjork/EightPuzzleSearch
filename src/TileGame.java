
public class TileGame {
	
	//**Make sure to add input for files later
	public static void main(String[] args) {
		int[] testBoard = new int[]{1,3,5,4,2,0,7,8,6};
		int[] goalBoard = new int[] {1,3,0,4,2,5,7,8,6};
		//System.out.println("***BFS SEARCH***");
		BFSearch.searchRepeats(testBoard, "-v", goalBoard);
		//System.out.println("***BFS SEARCH NO REPEATS***");
		//BFSearch.searchNoRepeats(testBoard,"");
		//DLSearch.searchRepeats(testBoard, "");
		//AStarSearch.searchRepeats(testBoard, "");
	}
}
