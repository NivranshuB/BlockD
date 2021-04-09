package game.game_logic;
import java.util.ArrayList;
import java.util.List;

public class ClickRegion {
	private List<Block> _regionBlocks = new ArrayList<Block>();
	private boolean[][] _evaluated = new boolean[10][15];
	private Block[][] _blockSet;
	
	public ClickRegion(int i, int j, Block[][] blockSet) {
		_blockSet = blockSet;
		checkNeighbours(i, j);		
	}
	
	private void checkNeighbours(int i, int j) {
		_evaluated[i][j] = true;
		
		if (_blockSet[i][j] == null) {
			return;
		}
		
		_regionBlocks.add(_blockSet[i][j]);
		
		if (i > 0 && _blockSet[i - 1][j] != null && _blockSet[i][j].getType() == _blockSet[i - 1][j].getType())	{
			if (_evaluated[i - 1][j] == false) {
				checkNeighbours(i - 1, j);
			}
		}
		
		if (i < 9 && _blockSet[i + 1][j] != null && _blockSet[i][j].getType() == _blockSet[i + 1][j].getType())	{
			if ( _evaluated[i + 1][j] == false) {
				checkNeighbours(i + 1, j);
			}
		}
		
		if (j > 0 && _blockSet[i][j - 1] != null && _blockSet[i][j].getType() == _blockSet[i][j - 1].getType())	{
			if (_evaluated[i][j - 1] == false) {
				checkNeighbours(i, j - 1);
			}
		}
		
		if (j < 14 && _blockSet[i][j + 1] != null && _blockSet[i][j].getType() == _blockSet[i][j + 1].getType()) {
			if ( _evaluated[i][j + 1] == false) {
				checkNeighbours(i, j + 1);
			}
		}
		
	}
	
	public int regionSize() {
		return _regionBlocks.size();
	}
	
	public List<Block> getBlocks() {
		return _regionBlocks;
	}
}
