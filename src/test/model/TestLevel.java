package test.model;

import static org.junit.Assert.*;

import org.junit.Test;

import test.model.Level;

/**
 * This class tests the functionality of Level type objects.
 * @author Nivranshu Bose | 06/12/20
 *
 */
public class TestLevel {

	/**
	 * Test that at construction a Level instance has a value of 0.
	 */
	@Test
	public void testScoreConstruction() {
		Level instance = new Level();
		assertEquals(0, instance.getLevel());
	}
	
	/**
	 * Test that incrementing a Level instance changes the value of the Score appropriately.
	 */
	@Test
	public void testScoreIncrease() {
		Level instance = new Level();
		instance.setLevel(7);
		instance.increment();
		
		assertEquals(8, instance.getLevel());
	}
	
	/**
	 * Test that the appropriate target score is returned when getTarget() method is invoked
	 * on a Level instance.
	 */
	@Test
	public void testTargetScoreDecrease() {
		Level instance = new Level();
		instance.setLevel(8);
		
		assertEquals(8500, instance.getTarget());
	}
}
