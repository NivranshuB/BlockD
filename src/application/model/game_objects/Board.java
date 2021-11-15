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
	
	public Board(int height, int width) {
		blockArray = new Block[height][width];
		cursor = new Cursor(height, width);
		
		for (int i = 0; i < blockArray.length; i++) {
			for (int j = 0; j < blockArray[0].length; j++) {
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
		
		for (int j = 0; j < blockArray[0].length; j++) {
			Stack<Block> elements = new Stack<Block>();
			for (int i = 0; i < blockArray.length; i++) {
				//Add all non empty blocks to stack with the blocks at the bottom at the top of the stack
				if (blockArray[i][j] != null) {
					elements.push(blockArray[i][j]);
				}
			}
			for (int k = blockArray.length - 1; k >= 0; k--) {
				if (elements.size() > 0) {
					blockArray[k][j] = elements.pop();
				} else {
					blockArray[k][j] = null;
				}
			}
		}
		
		for (int j = 0; j < blockArray[0].length; j++) {
			for (int i = j; i < blockArray[0].length; i++) {
				if (blockArray[blockArray.length - 1][j] == null) {
					removeColumn(j);
				} else {
					break;
				}
			}
			
		}
		
		for (int i = 0; i < blockArray.length; i++) {
			for (int j = 0; j < blockArray[0].length; j++) {
				if (blockArray[i][j] != null) {
					blockArray[i][j].setRow(i);
					blockArray[i][j].setCol(j);
				}
			}
		}
		
	}
	
	private void removeColumn(int col) {
		for (int k = col + 1; k < blockArray[0].length; k++) {
			for (int i = 0; i < blockArray.length; i++) {
				blockArray[i][k - 1] = blockArray[i][k];
			}
		}
		for (int l = 0; l < blockArray.length; l++) {
			blockArray[l][blockArray[0].length - 1] = null;
		}
	}
	
	public boolean complete() {
		boolean boardIncomplete = true;
		for (int i = blockArray.length - 1; i >= 0; i--) {
			for (int j = 0; j < blockArray[0].length ; j++) {
				if (blockArray[i][j] != null && new Region(i, j, blockArray).regionSize() > 1) {
					boardIncomplete = false;
				}
			}
		}
		
		return boardIncomplete;
	}
	
	public GridPane boardRepresentation() {
		
		GridPane boardRep = new GridPane();
		boardRep.setPadding(new Insets(3));
		
		for (int i = 0; i < blockArray.length; i++) {
			for (int j = 0; j < blockArray[0].length; j++) {
				
				Rectangle blockRep = new Rectangle(23, 23);
				blockRep.setStroke(Color.BEIGE);
				
				if (blockArray[i][j] == null) {
					blockRep.setFill(Color.ANTIQUEWHITE);
				} else {
					if (blockArray[i][j].getType() == BlockType.Green) {
						blockRep.setFill(Color.GREEN);
						if (selectedRegion.getBlocks().size() > 1 &&
								selectedRegion.getBlocks().contains(blockArray[i][j])) {
							blockRep.setFill(Color.DARKGREEN);
						}
					} else if (blockArray[i][j].getType() == BlockType.Yellow) {
						blockRep.setFill(Color.YELLOW);
						if (selectedRegion.getBlocks().size() > 1 &&
								selectedRegion.getBlocks().contains(blockArray[i][j])) {
							blockRep.setFill(Color.rgb(180, 180, 10));
						}
					} else if (blockArray[i][j].getType() == BlockType.Red) {
						blockRep.setFill(Color.RED);
						if (selectedRegion.getBlocks().size() > 1 &&
								selectedRegion.getBlocks().contains(blockArray[i][j])) {
							blockRep.setFill(Color.rgb(171, 0, 0));
						}
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
