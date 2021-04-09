package game.game_logic;

public class upCommand extends Command {

	public void engage(Board board) {
		board.cursorMove("up");
	}

}
