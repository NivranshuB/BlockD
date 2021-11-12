package application.model;

/**
 * Class representing a 'TargetScore' type object which inherits from Score but has an additional decrement()
 * method.
 * @author Nivranshu Bose | 06/12/20
 */
public class TargetScore extends Score {
	
	public TargetScore(int score) {
		super(score);
	}

	/**
	 * Decrement the value of the target value by the input amount with a lower limit of 0.
	 * @param decrement
	 */
	public void decreaseScore(int decrement) {
		if ((value - decrement) < 1) {
			value = 0;
		} else {
			value -= decrement;
		}
	}
}
