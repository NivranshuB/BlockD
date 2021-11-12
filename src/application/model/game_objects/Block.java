package application.model.game_objects;

public class Block {

	private BlockType type;
	private int row;
	private int col;
	private boolean selected;
	
	/**
	 * Constructor for a block instance.
	 * 
	 * @param bType | a value from the enum block type
	 * @param bRow | the row this block belongs to in its associated board instance
	 * @param bCol | the column this block belongs to its associated board instance
	 */
	public Block(BlockType bType, int bRow, int bCol) {
		type = bType;
		row = bRow;
		col = bCol;
		selected = false;
	}
	
	/**
	 * Method that overrides the Object#toString() to provide another string
	 * representation for a Block instance. 
	 */
	public String toString() { 
		
		switch(type) {
		case Red:
			if (selected == true) {
				return "R";
			}
			return "r";
		case Yellow:
			if (selected == true) {
				return "Y";
			}
			return "y";
		case Green:
			if (selected == true) {
				return "G";
			}
			return "g";
		case Bomb:
			return "B";
		case Lightning:
			return "L";
		default:
			//Any other type of a block must be a "Multiplier" type Block instance
			return "M";
		}
	}
	
	public void toggleSelected() {
		selected = !selected;
	}
	
	/**
	 * @return the BlockType of this Block 
	 */
	public BlockType getType() {
		return type;
	}
	
	/**
	 * Set the row position of this Block in its Board instance 
	 */
	public void setRow(int i) {
		row = i;
	}
	
	/**
	 * @return the row position of this Block in its Board instance 
	 */
	public int getRow() {
		return row;
	}
	
	/**
	 * Set the column position of this Block in its Board instance 
	 */
	public void setCol(int j) {
		col = j;
	}
	
	/**
	 * @return the column position of this Block in its Board instance 
	 */
	public int getCol() {
		return col;
	}
	
}
