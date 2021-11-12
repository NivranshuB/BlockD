package test.model;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * This class tests an instance of a Timer object.
 * @author Nivranshu Bose | 07/12/20
 */
public class TestTimer {

	/**
	 * This method tests the construction of a Timer object.
	 */
	@Test
	public void testTimerConstruction() {
		Timer instance = new Timer(80);
		instance.startTimer();
		
		assertEquals(80, instance.getTime());
	}

	/**
	 * This method tests that after starting a Timer, after a set amount of time, the value
	 * of timeSeconds is correct.
	 */
	@Test
	public void testTimePassed() {
		Timer instance = new Timer(10);
		instance.startTimer();
		try {
			Thread.sleep(2 * 1000);
		} catch (InterruptedException ie) {
			ie.printStackTrace();
		}
		
		assertEquals(8, instance.getTime());	
	}
	
	/**
	 * This method tests that when the time of the timer runs out the value
	 * of timeSeconds is 0.
	 */
	@Test
	public void testTimeReachesZero() {
		Timer instance = new Timer(3);
		instance.startTimer();
		try {
			Thread.sleep(5 * 1000);
		} catch (InterruptedException ie) {
			ie.printStackTrace();
		}
		
		assertEquals(0, instance.getTime());	
	}
	
}
