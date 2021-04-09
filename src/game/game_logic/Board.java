package game.game_logic;
import java.awt.Graphics;

import java.util.List;
import java.util.Stack;

import javax.swing.JPanel;

import game.GraphicsPainter;
import game.Painter;

/**
 * Board class represents a board object that will hold all the blocks in a certain 
 * position for every level/game. A board is always represented as a 10 rows and 15 columns
 * 2D array of blocks. Initially all 150 elements are Block instances. If a Block or 
 * set of Blocks are removed then these elements are replaced by null. There should be no 
 * "floating" blocks i.e. all block elements in the board must have a block above them
 * unless the block is the highest block in its column.
 * 
 * @author Nivranshu Bose | 07/07/20
 */
public class Board extends JPanel{

	//Score board instance
	private ScoreBoard _score;
	
	//Current cursor region blocks
	private List<Block> _regionBlocks;
	
	//The board representation as an array of Block instances
	private Block[][] _blockArray = new Block[10][15];
	
	//Number of blocks of each type in the board currently
	private int _redTally = 0;
	private int _yellowTally = 0;
	private int _greenTally = 0;
	
	private int cursorRow = 9;
	private int cursorColumn = 0;
	
	/**
	 * Board constructor that initially randomly creates (150) Blocks for each element
	 * of its 2D array and these blocks can be of the type Red, Yellow or Green. 
	 */
	public Board(ScoreBoard score) {
		_score = score;
		
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 15; j++) {
				int blockInt = RandomInt.getRandom(1, 3); 
				switch(blockInt) {
				case 1:
					_blockArray[i][j] = new Block(BlockType.Red, i, j);
					_redTally++;
					break;
				case 2:
					_blockArray[i][j] = new Block(BlockType.Yellow, i, j);
					_yellowTally++;
					break;
				case 3:
					_blockArray[i][j] = new Block(BlockType.Green, i, j);
					_greenTally++;
					break;
				}
			}
		}
		
		
		this.updateRegion();
	}
	
	private void updateRegion() {
		
		if (_blockArray[cursorRow][cursorColumn] == null) {
			_score.setRegion(0);
			return;
		}
		
		System.out.println("Updating region to region at point (" + cursorRow + ", " + cursorColumn + ")");
		
		_regionBlocks = (new ClickRegion(cursorRow, cursorColumn, this._blockArray)).getBlocks();
				
		if (_regionBlocks.size() < 2) {
			_score.setRegion(0);
			return;
		}
		
		_score.setRegion(_regionBlocks.size());
		System.out.println("Region blocks are " + _regionBlocks);
		
		System.out.println("Region size = " + _regionBlocks.size());
	}
	
	/**
	 * Given a position in the Block array representing this Board instance, removes that Block
	 * and all Blocks that this block is 'connected' to that are the same color type as this.
	 * 
	 * @param i | row position of element to be removed
	 * @param j | column position of element to be removed
	 */
	public void removeRegion(int i, int j) {
		 
		if (_blockArray[i][j] == null) {
			System.out.println("Region is empty");
			return;
		}
		
		//'region' represents all the block 'connected' to this block that are of the same type to
		//this block
		ClickRegion region = new ClickRegion(i, j, this._blockArray);
		List<Block> blocks = region.getBlocks();
		
		if (region.regionSize() < 2) {
			System.out.println("Region only contains one block");
			return;
		}
		
		System.out.println("Region size = " + region.regionSize());
		
		for (Block k: blocks) {
			this.remove(k.getRow(), k.getCol());
		}
		
		//After all the necessary blocks for this region have been removed, update the Board array
		//to fill any gaps
		this.update();
	}
	
	/**
	 * Given a row position and a column position, replaces the Block in this position
	 * with null. Changes the tally of each block type accordingly. For the current version 
	 * of the game this method should only be called from this.removeRegion().
	 *  
	 * @param i | row position of element to be removed
	 * @param j | column position of element to be removed
	 */
	public void remove(int i, int j) {
		//If the element at the specified position is null then do nothing.
		if (_blockArray[i][j] == null) {
			return;
		}
		System.out.println("Removing Block at point (" + i + ", " + j + ")");
		if (_blockArray[i][j].getType() == BlockType.Red) {
			_redTally--;
		} else if (_blockArray[i][j].getType() == BlockType.Yellow) {
			_yellowTally--;
		} else if (_blockArray[i][j].getType() == BlockType.Green) {
			_greenTally--;
		}

		_blockArray[i][j] = null;
	}
	
	/**
	 * Updates the Board representation array so that there are no 'holes' in the board.
	 * Essentially all elements should have a Block above them unless they are the top Block
	 * in their column.
	 */
	public void update() {
		for (int j = 0; j < 15; j++) {
			Stack<Block> elements = new Stack<Block>();
			for (int i = 0; i < 10; i++) {
				//If an empty element exists then replace it with its above element Block and \
				//let the above element be empty
				if (_blockArray[i][j] != null) {
					elements.push(_blockArray[i][j]);
				}
			}
			for (int k = 9; k >= 0; k--) {
				if (elements.size() > 0) {
					_blockArray[k][j] = elements.pop();
				} else {
					_blockArray[k][j] = null;
				}
			}
		}
		
		for (int j = 0; j < 15; j++) {
			for (int i = j; i < 15; i++) {
				if (_blockArray[9][j] == null) {
					removeColumn(j);
				} else {
					break;
				}
			}
			
		}
		
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 15; j++) {
				if (_blockArray[i][j] != null) {
					_blockArray[i][j].setRow(i);
					_blockArray[i][j].setCol(j);
				}
			}
		}
		
	}
	
	private void removeColumn(int col) {
		for (int k = col + 1; k < 15; k++) {
			for (int i = 0; i < 10; i++) {
				_blockArray[i][k - 1] = _blockArray[i][k];
			}
		}
		for (int l = 0; l < 10; l++) {
			_blockArray[l][14] = null;
		}
	}
	
	/**
	 * Visual display of the board
	 */
	public String toString() {
		String display = "";
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 15; j++) {
				if (i == cursorRow && j == cursorColumn) {
					display = display + "_ ";
				} else if (_blockArray[i][j] != null) {
					display = display + _blockArray[i][j] + " ";
				} else {
					display = display + "  ";
				}
			}
			display = display + "\n";
		}
		System.out.println(display);
		System.out.println(_redTally + " red blocks.");
		System.out.println(_yellowTally + " yellow blocks.");
		System.out.println(_greenTally + " green blocks.");
		
		return display;
	}
	
	public void cursorMove(String move) {
		if (move.equals("left")) {
			if (cursorColumn > 0) {
				cursorColumn--;
			}
		} else if (move.equals("up")) {
			if (cursorRow > 0) {
				cursorRow--;
			}
		} else if (move.equals("right")) {
			if (cursorColumn < 14) {
				cursorColumn++;
			}
		} else if (move.equals("down")) {
			if (cursorRow < 9) {
				cursorRow++;
			}
		}
		
		this.updateRegion();
	}
	
	public void removeCursorRegion() {
		
		if (_blockArray[cursorRow][cursorColumn] == null) {
			System.out.println("Region is empty");
			return;
		} else if (_regionBlocks.size() < 2) {
			System.out.println("Region only contains one block");
			return;
		}
		
		_score.update(_blockArray[cursorRow][cursorColumn].getType(), _regionBlocks.size());
		
		for (Block k: _regionBlocks) {
			this.remove(k.getRow(), k.getCol());
		}
		
		//After all the necessary blocks for this region have been removed, update the Board array
		//to fill any gaps
		this.update();
		
		this.updateRegion();
	}
	
	/**
	 * @return true if the board contains no Block instances, otherwise return false
	 */
	public boolean isEmpty() {
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 15; j++) {
				if (_blockArray[i][j] != null) {
					if ((new ClickRegion(i, j, this._blockArray)).regionSize() > 1) {
						return false;
					}
				}
			}
		}
		return true;
	}
	
	/**
	 * @return the Block[][] representation of this Board instance
	 */
	public Block[][] getBoardArray(){
		return _blockArray;
	}
	
	/**
	 * @return the Block[][] representation of this Board instance
	 */
	public Block getElement(int i, int j){
		return _blockArray[i][j];
	}
	
	/**
	 * @return the number of blocks of a particular type
	 */
	public int getTally(BlockType type){
		switch(type) {
		case Red:
			return _redTally;
		case Yellow:
			return _yellowTally;
		case Green:
			return _greenTally;
		default:
				return 1;
		}
	}
	
	/**
	 * Called by the Swing framework whenever this AnimationViewer object
	 * should be repainted. This can happen, for example, after an explicit 
	 * repaint() call or after the window that contains this AnimationViewer 
	 * object has been opened, exposed or moved.
	 * 
	 */
	public void paintComponent(Graphics g) {
		// Call inherited implementation to handle background painting.
		super.paintComponent(g);
		
		// Create a GraphicsPainter that Shape objects will use for drawing.
		// The GraphicsPainter delegates painting to a basic Graphics object.
		Painter painter = new GraphicsPainter(g);
		
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 15; j++) {
				if (_blockArray[i][j] != null) {
					_blockArray[i][j].paint(painter);
				}
			}
		}
	}
	
	public void Paint(Graphics g) {
		this.paintComponent(g);
	}
}
