package application.model.game_objects;
import java.util.ArrayList;
import java.util.List;

public class Region {
	
	private List<Block> regionBlocks = new ArrayList<Block>();
	private boolean[][] evaluated = new boolean[10][10];
	private Block[][] blockSet;
	
	public Region(int i, int j, Block[][] boardBlocks) {
		blockSet = boardBlocks;
		checkNeighbours(i, j);		
	}
	
	/**
	 * 
	 * @param i
	 * @param j
	 */
	private void checkNeighbours(int i, int j) {

		evaluated[i][j] = true;
		
		if (blockSet[i][j] == null) {
			return;
		}
		
		regionBlocks.add(blockSet[i][j]);
		
		if (i < 9 && blockSet[i + 1][j] != null && blockSet[i][j].getType() == blockSet[i + 1][j].getType()) {
			if ( evaluated[i + 1][j] == false) {
				checkNeighbours(i + 1, j);
			}
		}

		if (i > 0 && blockSet[i - 1][j] != null && blockSet[i][j].getType() == blockSet[i - 1][j].getType()) {
			if (evaluated[i - 1][j] == false) {
				checkNeighbours(i - 1, j);
			}
		}


		
		if (j > 0 && blockSet[i][j - 1] != null && blockSet[i][j].getType() == blockSet[i][j - 1].getType()) {
			if (evaluated[i][j - 1] == false) {
				checkNeighbours(i, j - 1);
			}
		}
		
		if (j < 9 && blockSet[i][j + 1] != null && blockSet[i][j].getType() == blockSet[i][j + 1].getType()) {
			if ( evaluated[i][j + 1] == false) {
				checkNeighbours(i, j + 1);
			}
		}
				
	}
	
	public int regionSize() {
		return regionBlocks.size();
	}
	
	public List<Block> getBlocks() {
		return regionBlocks;
	}
}
