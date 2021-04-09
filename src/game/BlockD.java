package game;

import game.game_logic.Game;

public class BlockD {

	/**
	 * Main program method to create a Game object and display this in the command line
	 */
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				Game game = new Game();
				game.startGame();
			}
		});
	}
}
