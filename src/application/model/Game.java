package application.model;

import java.util.Scanner;
import application.model.game_objects.Board;

/**
 * Class representing a playable instance of a 'Solo Game' object.
 * 
 * @author Nivranshu Bose | 06/12/20
 */
public class Game {

	private static Game instance;
	
	private Board gameBoard;
	private Score totalScore;
	private Score regionScore;
	private TargetScore targetScore;
	private Level level;
	private Timer timer;

	
	private Game() {
		gameBoard = new Board(10);
		level = new Level(1);
		totalScore = new Score(0);
		regionScore = new Score(gameBoard.computeRegionScore());
		targetScore = new TargetScore(level.getTarget());
		timer = Timer.getInstance();
	}
	
	public static Game getInstance() {
		if (instance == null) {
			instance = new Game();
		}		
		return instance;
	}
	
	public void resetGame() {
		instance = null;
		timer.resetTimer();
	}
	
	public void start() {
		
		boolean gameOver = false;
		
		System.out.println("Welcome to BlockD!\n");
		
		while(!gameOver) {
			
			System.out.println("");
			System.out.println("X========================================X");
			System.out.println("Current level: " + level.getLevel());
			System.out.println("Total Score: " + totalScore.getScore());
			System.out.println("Current Region's Value: " + regionScore.getScore());
			System.out.println("Target Score left: " + targetScore.getScore());
			System.out.println("X========================================X");
			
			System.out.println(gameBoard);
			
			System.out.println("\nCommands: 'w' for up, 'a' for left, 's' for down, 'd' for right, 'b' for break, 'q' "
					+ "for quit.\n");
			
			@SuppressWarnings("resource")
			Scanner sc = new Scanner(System.in); //System.in is a standard input stream  
			System.out.print("Enter a command: ");  
			String command= sc.nextLine();              //reads string  
			
			if (command.equalsIgnoreCase("w")) {
				gameBoard.move("w");
			} else if (command.equalsIgnoreCase("a")) {
				gameBoard.move("a");
			} else if (command.equalsIgnoreCase("s")) {
				gameBoard.move("s");
			} else if (command.equalsIgnoreCase("d")) {
				gameBoard.move("d");
			} else if (command.equalsIgnoreCase("b")) {
				this.updateScores();
			} else if (command.equalsIgnoreCase("q")) {
				gameOver = true;
			} else {
				System.out.println("Invalid command.");
			}
			
			this.updateRegionScore();
			
			if (targetScore.getScore() <= 0) {
				System.out.println("!!!!!!!!!!Level " + level.getLevel() + " complete!!!!!!!!!!\n");
				level.setLevel(level.getLevel() + 1);
				targetScore = new TargetScore(level.getTarget());
				
				System.out.println("Target score for level " + level.getLevel() + " is " + targetScore.getScore());
				System.out.println("Good luck :)");
			}
			
			if (gameBoard.complete()) {
				System.out.println("\nGame complete. Reloading the game board!");
				gameBoard = new Board(10);
				regionScore = new Score(gameBoard.computeRegionScore());
			}
		}
	
	}
	
	public void updateRegionScore() {
		regionScore = new Score(gameBoard.computeRegionScore());
	}
	
	public void updateScores() {
		totalScore.increaseScore(gameBoard.computeRegionScore());
		targetScore.decreaseScore(gameBoard.computeRegionScore());
		gameBoard.breakRegion();
	}
	
	public Board getBoard() {
		return gameBoard;
	}
	
	public void setBoard(Board newBoard) {
		gameBoard = newBoard;
	}
	
	public Score getTotalScore() {
		return totalScore;
	}
	
	public Score getRegionScore() {
		return regionScore;
	}
	
	public TargetScore getTargetScore() {
		return targetScore;
	}
	
	public void setTargetScore(TargetScore newTargetScore) {
		targetScore = newTargetScore;
	}
	
	public Timer getTimer() {
		return timer;
	}
	
	public Level getLevel() {
		return level;
	}
	
}


