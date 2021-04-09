package game.game_logic;

public class rightCommand extends Command{

	public void engage(Board board) {
		board.cursorMove("right");
	}
}
