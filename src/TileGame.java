
public class TileGame {
	
	//**Make sure to add input for files later
	public static void main(String[] args) {
		int[] testBoard = new int[]{1,3,5,4,2,0,7,8,6};
		BFSearch.searchRepeats(testBoard, "");
		BFSearch.searchNoRepeats(testBoard,"");
	}
}
