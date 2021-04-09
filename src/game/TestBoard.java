package game;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import game.game_logic.Block;
import game.game_logic.BlockType;
import game.game_logic.Board;
import game.game_logic.ClickRegion;

/**
 * A class that implements test cases aimed at identifying bugs in the 
 * implementation of class Board.
 * 
 * @author Nivranshu Bose | 07/07/20
 */
public class TestBoard {

	private Board _board;
	/**
	 * This method is called automatically by the JUnit test-runner immediately
	 * before each @Test method is executed. setUp() recreates the fixture so 
	 * that there no side effects from running individual tests.
	 */
	@Before
	public void setUp() {
		_board = new Board();
	}

	/**
	 * Test to remove the region in the board that has the greatest number of 
	 * blocks in it.
	 */
	@Test
	public void testRegionRemove() {
		int maximum = 0;
		int rowPos = 0;
		int colPos = 0;
		
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 15; j++) {
				ClickRegion region = new ClickRegion(i, j, _board.getBoardArray());
				System.out.println("Region score at position " + i + " " + j + " is " + region.regionSize());
				if (region.regionSize() > maximum) {
					maximum = region.regionSize();
					rowPos = i;
					colPos = j;
				}
			}
		}
		
		System.out.println(_board);
		
		Block blockRemove = _board.getElement(rowPos, colPos);
		BlockType type = blockRemove.getType();
		int blockNumber1 = _board.getTally(type);
		
		_board.removeRegion(rowPos, colPos);
		
		int blockNumber2 = _board.getTally(type);
		
		assertTrue(blockNumber1 - blockNumber2 == 150 - _board.getTally(BlockType.Red) - 
				_board.getTally(BlockType.Yellow) - _board.getTally(BlockType.Green));

		System.out.println(_board);
	}

}
