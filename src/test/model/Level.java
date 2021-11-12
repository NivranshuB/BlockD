package test.model;

/**
 * This class represents what level the a game is at and what is the target score for this
 * level. 
 * @author Nivranshu Bose | 06/12/20
 *
 */
public class Level {

	int level;
	
	public Level() {
		level = 0;
	}
	
	int[] targets = {5000, 5500, 6000, 6500, 7000, 7500, 8000, 8500, 9000, 9500, 10000,
			10500, 11000, 11500, 12000};
	
	void increment() {
		 level++;
	}
	
	void setLevel(int newLevel) {
		level = newLevel;
	}
	
	int getLevel() {
		return level;
	}
	
	int getTarget() {
		return targets[level - 1];
	}
}
