package application.model.game_objects;

/**
 * BlockType enum class represents all the possible types a Block instance can be. 
 * This includes:
 * 
 * Red: Red colored block
 * Yellow: Yellow colored block
 * Green: Green colored block
 * 
 * Bomb: Special block that...
 * Lightning: Special block that increases the selected 'region' by 1 block in all 4 directions
 * MultiplerX2: Special block that multiplies the score of the region by the multiplier
 * 
 * @author Nivranshu Bose | 07/07/20
 */
public enum BlockType {
	Red, Yellow, Green, Bomb, Lightning, MultiplierX2, MultiplierX3, MultiplierX4, MultiplierX5, MultiplierX6
}
