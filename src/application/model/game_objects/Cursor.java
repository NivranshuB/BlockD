package application.model.game_objects;

public class Cursor {

	int xPos;//column
	int yPos;//row
	int maxX;//max column position
	int maxY;//max row position
	Region region;
	
	public Cursor(int height, int width) {
		xPos = 0;
		yPos = 0;
		maxX = width - 1;
		maxY = height - 1;
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

	public void setyPos(int y) {
		yPos = y;
	}

	public void setxPos(int x) {
		xPos = x;
	}
}
