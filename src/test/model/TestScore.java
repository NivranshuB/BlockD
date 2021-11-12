package test.model;

import static org.junit.Assert.*;

import org.junit.Test;

import test.model.Score;

/**
 * This class tests the functionality of Score and TargetScore objects.
 * @author Nivranshu Bose | 06/12/20
 *
 */
public class TestScore {

	/**
	 * Test that at construction a Score instance has a value of 0.
	 */
	@Test
	public void testScoreConstruction() {
		Score scoreInstance = new Score();
		assertEquals(0, scoreInstance.getScore());
	}
	
	/**
	 * Test that incrementing a Score instance changes the value of the Score appropriately.
	 */
	@Test
	public void testScoreIncrease() {
		Score scoreInstance = new Score();
		scoreInstance.setScore(7);
		scoreInstance.increaseScore(7);
		
		assertEquals(14, scoreInstance.getScore());
	}
	
	/**
	 * Test that decrementing a TargetScore instance changes the value of the Score appropriately
	 * when the Score value after decrease is greater that 0.
	 */
	@Test
	public void testTargetScoreDecrease() {
		TargetScore targetScoreInstance = new TargetScore();
		targetScoreInstance.setScore(3000);
		targetScoreInstance.decreaseScore(30);
		
		assertEquals(2970, targetScoreInstance.getScore());
	}

	/**
	 * Test that decrementing a TargetScore instance changes the value of the Score appropriately
	 * when the Score value after decrease is less that 0.
	 */
	@Test
	public void testTargetScoreDecreaseLowerlimit() {
		TargetScore targetScoreInstance = new TargetScore();
		targetScoreInstance.setScore(20);
		targetScoreInstance.decreaseScore(30);
		
		assertEquals(0, targetScoreInstance.getScore());
	}
}
