package game.game_logic;

public class leftCommand extends Command{

	public void engage(Board board) {
		board.cursorMove("left");
	}
}
