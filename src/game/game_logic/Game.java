package game.game_logic;
import java.util.Scanner;

public class Game {

	private Board _board;
	private ScoreBoard _score;
	private int _maximum = 0;
	private int _X = 0;
	private int _Y = 0;


	public Game() {
		_score = new ScoreBoard();
		this.startGame();
	}

	public void startGame() {

		BlockDGUI GUI = new BlockDGUI(_score);
		_board = new Board(_score);

		while (!_board.isEmpty()) {

			_score.displayScore();
			_board.toString();

			Scanner keyboard = new Scanner(System.in);
			System.out.println("Enter a move (a = left, w = up, d = right, s = down, b = break): ");
			String move = keyboard.nextLine();

			Command command = null; 
			if (move.contentEquals("a")) {
				command = new leftCommand();
			} else if (move.contentEquals("w")) {
				command = new upCommand();
			} else if (move.contentEquals("d")) {
				command = new rightCommand();
			} else if (move.contentEquals("s")) {
				command = new downCommand();
			} else if (move.contentEquals("b")) {
				command = new breakCommand();
			} else {
				System.out.println("Invalid move");
			}

			if (command != null) {
				command.engage(_board);
			}

			if (_board.isEmpty()) {
				_board = new Board(_score);
			}
		}

	}


	/*
	String s = _board.toString();

	for (int i = 0; i < 10; i++) {
		for (int j = 0; j < 15; j++) {
			ClickRegion region = new ClickRegion(i, j, _board.getBoardArray());
			System.out.println("Region score at position " + i + " " + j + " is " + region.regionSize());
			if (region.regionSize() > _maximum) {
				_maximum = region.regionSize();
				_X = i;
				_Y = j;
			}
		}
	}

	_board.removeRegion(_X, _Y);
	s = _board.toString();
	*/

}
