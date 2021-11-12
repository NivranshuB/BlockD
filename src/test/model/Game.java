package test.model;

import application.model.game_objects.Board;

/**
 * Class representing a playable instance of a 'Solo Game' object.
 * 
 * @author Nivranshu Bose | 06/12/20
 */
public class Game {

	Board gameBoard;
	Score totalScore;
	Score regionScore;
	TargetScore targetScore;
	Level level;
	Timer timer;
}
