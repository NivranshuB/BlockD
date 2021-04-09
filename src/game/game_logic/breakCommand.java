package game.game_logic;

public class breakCommand extends Command{

	public void engage(Board board) {
		board.removeCursorRegion();
	}
}
