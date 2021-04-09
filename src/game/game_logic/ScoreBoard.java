package game.game_logic;

public class ScoreBoard {
	private int _redScore;
	private int _yellowScore;
	private int _greenScore;
	
	private int _totalScore;
	private int _regionScore;
	private int _level;
	private int _target;
	private int _levelTarget;
	
	public ScoreBoard() {
		_redScore = 0;
		_yellowScore = 0;
		_greenScore = 0;
		
		_totalScore = 0;
		_regionScore = 0;
		_level = 1;
		_target = 5000;
		_levelTarget = 5000;
	}
	
	public void update(BlockType type, int incrementSize) {
		if (type.equals(BlockType.Red)) {
			_redScore += incrementSize * incrementSize;
			if (_redScore > 799) {
				_redScore = 0;
			}
		} else if (type.equals(BlockType.Yellow)){
			_yellowScore += incrementSize * incrementSize;
			if (_yellowScore > 799) {
				_yellowScore = 0;
			}
		} else if (type.equals(BlockType.Green)) {
			_greenScore += incrementSize * incrementSize;
			if (_greenScore > 799) {
				_greenScore = 0;
			}
		}
		
		_totalScore += incrementSize * incrementSize;
		_target -= incrementSize * incrementSize;
		if (_target < 1) {
			_level++;
			_levelTarget += 1000;
			_target = _levelTarget;
		}
	}
	
	public void reset(String colour) {
		if (colour.equals("red")) {
			_redScore = 0;
		} else if (colour.equals("yellow")){
			_yellowScore = 0;
		} else if (colour.equals("green")) {
			_greenScore = 0;
		}
	}
	
	public void setRegion(int regionSize) {
		_regionScore = regionSize * regionSize;
	}
	public int getRegion() {
		return _regionScore;
	}
	
	public void displayScore() {
		System.out.println("X---------Scoreboard---------X");
		System.out.println("");
		System.out.println("X--Level: " + _level + "---Target: " + _target + " ----X");
		System.out.println("Red score: " + _redScore);
		System.out.println("Yellow score: " + _yellowScore);
		System.out.println("Green score: " + _greenScore);
		System.out.println("");
		System.out.println("Region score: " + _regionScore);
		System.out.println("");
		System.out.println("Total score: " + _totalScore);
		System.out.println("X----------------------------X");
	}
}	
