package game.game_logic;

public class downCommand extends Command{

	public void engage(Board board) {
		board.cursorMove("down");
	}
}
