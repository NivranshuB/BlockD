package test.model;

/**
 * Class representing a 'Score' type object which stores as an int value a score for a Game instance.
 * @author Nivranshu Bose | 06/12/20
 */
public class Score {
	
	int value;
	
	public Score() {
		value = 0;
	}
	
	void increaseScore(int increase) {
		value += increase;
	}
	
	void setScore(int score) {
		value = score;
	}
	
	int getScore() {
		return value;
	}
}
