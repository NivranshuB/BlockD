package game;
import static org.junit.Assert.*;

import org.junit.Test;

import game.game_logic.Block;
import game.game_logic.BlockType;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.Test;

/**
 * A class that implements test cases aimed at identifying bugs in the 
 * implementation of class Block.
 * 
 * @author Nivranshu Bose | 07/07/20
 */
public class TestBlock {

	/**
	 * Test the String representation of a Block instance
	 */
	@Test
	public void testSimpleMove() {
		Block redBlock = new Block(BlockType.Red, 0, 0);
		Block multiplyBlock = new Block(BlockType.MultiplierX2, 0, 0);
		Block bombBlock = new Block(BlockType.Bomb, 0, 0);
		
		assertEquals("r", redBlock.toString());
		assertEquals("M", multiplyBlock.toString());
		assertEquals("B", bombBlock.toString());
	}

}
