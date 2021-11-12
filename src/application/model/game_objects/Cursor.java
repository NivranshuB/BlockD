package application.model.game_objects;

public class Cursor {

	int xPos;
	int yPos;
	int maxX;
	int maxY;
	Region region;
	
	public Cursor(int boardSize) {
		xPos = 0;
		yPos = 0;
		maxX = boardSize - 1;
		maxY = boardSize - 1;
	}
	
	int getX() {
		return xPos;
	}
	
	int getY() {
		return yPos;
	}
	
	void right() {
		if (xPos + 1 <= maxX) {
			xPos++;
		}
	}
	
	void left() {
		if (xPos - 1 >= 0) {
			xPos--;
		}
	}
	
	void down() {
		if (yPos + 1 <= maxY) {
			yPos++;
		}
	}
	
	void up() {
		if (yPos - 1 >= 0) {
			yPos--;
		}
	}
}
