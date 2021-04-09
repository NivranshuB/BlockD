package game.game_logic;
import java.util.Random;

public class RandomInt {

	public static int getRandom(int min, int max) {
		if (min >= max) {
			throw new IllegalArgumentException("max must be greater than min");
		}

		Random r = new Random();
		int returnNumber = r.nextInt((max - min) + 1) + min;
		if (returnNumber == 0) {
			return returnNumber + 1;
		}
		return returnNumber;
	}
}
