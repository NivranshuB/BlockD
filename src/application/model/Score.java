package application.model;

/**
 * Class representing a 'Score' type object which stores as an int value a score for a Game instance.
 * @author Nivranshu Bose | 06/12/20
 */
public class Score {
	
	int value;	
	
	public Score(int scoreValue) {
		value = scoreValue;
	}
	
	
	public Score() {
		value = 0;
	}

	public void increaseScore(int increase) {
		value += increase;
	}
	
	void setScore(int score) {
		value = score;
	}
	
	public int getScore() {
		return value;
	}
}
