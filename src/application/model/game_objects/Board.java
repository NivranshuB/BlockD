package application.model.game_objects;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class Board {

	Block[][] blockArray;
	Cursor cursor;
	private Region selectedRegion;
	
	public Board(int size) {
		blockArray = new Block[size][size];
		cursor = new Cursor(size);
		
		for (int i = 0; i < blockArray[0].length; i++) {
			for (int j = 0; j < blockArray.length; j++) {
				double randomValue = Math.random();
				if (randomValue < 0.33) {
					blockArray[i][j] = new Block(BlockType.Red, i, j);
				} else if (randomValue > 0.67) {
					blockArray[i][j] = new Block(BlockType.Green, i, j);
				} else {
					blockArray[i][j] = new Block(BlockType.Yellow, i, j);
				}
			}
		}
		
		selectedRegion = new Region(this.getCursorY(), this.getCursorX(), this.getBlockArray());
		for (Block x : selectedRegion.getBlocks()) {
			x.toggleSelected();
		}
	}
	
	public String toString() {
		String boardStr = "";
		
		for (int i = 0; i < blockArray[0].length; i++) {
			for (int j = 0; j < blockArray.length; j++) {
				if (i == cursor.yPos && j == cursor.xPos) {
					boardStr = boardStr + " " + "_";
				} else if (blockArray[i][j] == null) {
					boardStr = boardStr + "  ";
				} else {
					boardStr = boardStr + " " + blockArray[i][j];
				}				
			}
			boardStr = boardStr + "\n";
		}		
		
		return boardStr;
	}
	
	public void move(String move) {
		if (move.equalsIgnoreCase("w")) {
			cursor.up();
		} else if (move.equalsIgnoreCase("a")) {
			cursor.left();
		} else if (move.equalsIgnoreCase("s")) {
			cursor.down();
		} else if (move.equalsIgnoreCase("d")) {
			cursor.right();
		} else {
			return;
		}
		this.updateSelectedRegion();
	}
	
	private void updateSelectedRegion() {
		for (Block x : selectedRegion.getBlocks()) {
			x.toggleSelected();
		}
		selectedRegion = new Region(this.getCursorY(), this.getCursorX(), this.getBlockArray());
		for (Block x : selectedRegion.getBlocks()) {
			x.toggleSelected();
		}
	}
	
	public int getCursorX() {
		return cursor.getX();
	}
	
	public int getCursorY() {
		return cursor.getY();
	}
	
	public Block[][] getBlockArray() {
		return blockArray;
	}
	
	public int computeRegionScore() {
		return (selectedRegion.regionSize() * selectedRegion.regionSize());
	}
	
	public void breakRegion() {
		if (selectedRegion.regionSize() < 2) {
			System.out.println("Region only contains one block");
			return;
		}
		
		for (Block x : selectedRegion.getBlocks()) {
			blockArray[x.getRow()][x.getCol()] = null;
		}
		
		this.update();
		this.updateSelectedRegion();
	}
	
	/**
	 * Updates the Board representation array so that there are no 'holes' in the board.
	 * Essentially all elements should have a Block above them unless they are the top Block
	 * in their column.
	 */
	public void update() {
		
		for (int j = 0; j < 10; j++) {
			Stack<Block> elements = new Stack<Block>();
			for (int i = 0; i < 10; i++) {
				//If an empty element exists then replace it with its above element Block and \
				//let the above element be empty
				if (blockArray[i][j] != null) {
					elements.push(blockArray[i][j]);
				}
			}
			for (int k = 9; k >= 0; k--) {
				if (elements.size() > 0) {
					blockArray[k][j] = elements.pop();
				} else {
					blockArray[k][j] = null;
				}
			}
		}
		
		for (int j = 0; j < 10; j++) {
			for (int i = j; i < 10; i++) {
				if (blockArray[9][j] == null) {
					removeColumn(j);
				} else {
					break;
				}
			}
			
		}
		
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (blockArray[i][j] != null) {
					blockArray[i][j].setRow(i);
					blockArray[i][j].setCol(j);
				}
			}
		}
		
	}
	
	private void removeColumn(int col) {
		for (int k = col + 1; k < 10; k++) {
			for (int i = 0; i < 10; i++) {
				blockArray[i][k - 1] = blockArray[i][k];
			}
		}
		for (int l = 0; l < 10; l++) {
			blockArray[l][9] = null;
		}
	}
	
	public boolean complete() {
		for (int i = blockArray.length - 1; i >= 0; i--) {
			for (int j = 0; j < blockArray[0].length; j++) {
				if (blockArray[i][j] != null && new Region(i, j, blockArray).regionSize() > 1) {
					return false;
				}
			}
		}
		
		return true;
	}
	
	public GridPane boardRepresentation() {
		
		GridPane boardRep = new GridPane();
		boardRep.setPadding(new Insets(3));
		
		for (int i = 0; i < blockArray[0].length; i++) {
			for (int j = 0; j < blockArray.length; j++) {
				
				Rectangle blockRep = new Rectangle(30, 30);
				blockRep.setStroke(Color.BEIGE);
				
				if (blockArray[i][j] == null) {
					blockRep.setFill(Color.ANTIQUEWHITE);
				} else {
					if (blockArray[i][j].getType() == BlockType.Green) {
						blockRep.setFill(Color.GREEN);
					} else if (blockArray[i][j].getType() == BlockType.Yellow) {
						blockRep.setFill(Color.YELLOW);
					} else if (blockArray[i][j].getType() == BlockType.Red) {
						blockRep.setFill(Color.RED);
					} else {
						blockRep.setFill(Color.ANTIQUEWHITE);
					}
				}
				
				if (j == cursor.xPos & i == cursor.yPos) {
					blockRep.setStroke(Color.BLACK);
				}
				
				boardRep.add(blockRep, j, i);
			}
		}
		
		return boardRep;
	}
	
	public boolean boardEmpty() {
		for (int i = 0; i < blockArray.length; i++) {
			for (int j = 0; j < blockArray[0].length; j++) {
				if (blockArray[i][j] != null) {
					return false;
				}
			}
		}
		
		return true;
	}
	
}
