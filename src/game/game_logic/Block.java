package game.game_logic;

import java.awt.Color;
import game.Painter;

/**
 * Block class represents a Block that is part of the Board instance. A Block has
 * can be of different types where different blocks will have different behaviour.  
 * A block will be associated with its x and y position on the board. A block
 * instance cannot exist without a board instance (composition relationship). 
 * 
 * @author Nivranshu Bose | 07/07/20
 */
public class Block {

	private BlockType _type;
	private int _row;
	private int _col;
	
	/**
	 * Constructor for a block instance.
	 * 
	 * @param type | a value from the enum block type
	 * @param row | the row this block belongs to in its associated board instance
	 * @param col | the column this block belongs to its associated board instance
	 */
	public Block(BlockType type, int row, int col) {
		_type = type;
		_row = row;
		_col = col;
	}
	
	/**
	 * Method that overrides the Object#toString() to provide another string
	 * representation for a Block instance. 
	 */
	public String toString() { 
		switch(_type) {
		case Red:
			return "r";
		case Yellow:
			return "y";
		case Green:
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
	
	/**
	 * @return the BlockType of this Block 
	 */
	public BlockType getType() {
		return _type;
	}
	
	/**
	 * Set the row position of this Block in its Board instance 
	 */
	public void setRow(int i) {
		_row = i;
	}
	
	/**
	 * @return the row position of this Block in its Board instance 
	 */
	public int getRow() {
		return _row;
	}
	
	/**
	 * Set the column position of this Block in its Board instance 
	 */
	public void setCol(int j) {
		_col = j;
	}
	
	/**
	 * @return the column position of this Block in its Board instance 
	 */
	public int getCol() {
		return _col;
	}

	public void paint(Painter painter) {
		switch (_type) {
			case Red:
				painter.setColor(new Color(255, 0, 0));
			case Yellow:
				painter.setColor(new Color(255, 255, 0));
			case Green:
				painter.setColor(new Color(0, 255, 0));
		}
		painter.drawRect(20 + 10 * _col, 20 + 10 * _row, 10, 10);
	}
}

