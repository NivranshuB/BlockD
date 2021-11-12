package application.controller;

import java.io.IOException;

import application.model.Game;
import application.model.Score;
import application.model.TargetScore;
import application.model.game_objects.Board;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.Window;

public class GameScreenController {
	
	@FXML
    private AnchorPane boardRep;
	
	@FXML
	private Label level, totalScore, regionScore, targetScore, timer, help;
	
	private Game game = Game.getInstance();

    @FXML
    protected void initialize() {
    	boardRep.getChildren().add(game.getBoard().boardRepresentation());
    	level.setText("Level: " + game.getLevel().getLevel());
    	totalScore.setText("Total Score: " + game.getTotalScore().getScore());
    	regionScore.setText("Region Score: " + game.getRegionScore().getScore());
    	targetScore.setText("Target Score: " + game.getTargetScore().getScore());
    	timer.setText("Time: 05:00");
    }
	
	/**
	 * Changes the scene from the game screen to the home menu scene.
	 */
	public void homeButtonPressed(ActionEvent event) throws IOException {
		new ViewController().changeScene(event, "/application/view/MainMenu.fxml");
	}
	
	/**
	 * Changes the scene from the home screen to the help menu screen.
	 */
	public void helpButtonPressed(ActionEvent event) throws IOException {
		new ViewController().changeScene(event, "/application/view/HelpScreen.fxml");
	}
	
	public void keyPressed(KeyEvent event) {
        switch (event.getCode()) {
        case UP:
        	help.setText("Help: Use arrow keys to move along the board, and 'b' to break area");
        	game.getBoard().move("w");
        	game.updateRegionScore();
        	this.initialize();
            break;
        case LEFT:
        	help.setText("Help: Use arrow keys to move along the board, and 'b' to break area");
        	game.getBoard().move("a");
        	game.updateRegionScore();  
        	this.initialize();
            break;
        case DOWN:
        	help.setText("Help: Use arrow keys to move along the board, and 'b' to break area");
        	game.getBoard().move("s");
        	game.updateRegionScore();  
        	this.initialize();
            break;
        case RIGHT:
        	help.setText("Help: Use arrow keys to move along the board, and 'b' to break area");
        	game.getBoard().move("d");
        	game.updateRegionScore();  
        	this.initialize();
            break;
        case B:
        	help.setText("Help: Use arrow keys to move along the board, and 'b' to break area");
        	game.updateScores();
        	game.updateRegionScore();  
        	this.initialize();
            break;
        default:
        	help.setText("Help: Invalid move, please try again");
        	this.initialize();
            break;
        }         
        
    	
		if (game.getTargetScore().getScore() <= 0) {
			game.getLevel().setLevel(game.getLevel().getLevel() + 1);
			
			game.setTargetScore(new TargetScore(game.getLevel().getTarget()));
			
            help.setText("Help: Target met, Leveling up!!!");
		}
		
		if (game.getBoard().complete()) {
			System.out.println("\nGame board complete. Reloading the game board!");
			
			if (game.getBoard().boardEmpty()) {
				help.setText("Help: Board cleared perfectly. Extra 1000 points for you!!!");
				game.getTotalScore().increaseScore(1000);
				game.getTargetScore().decreaseScore(1000);
			}

			game.setBoard(new Board(10));
			game.updateRegionScore();			
			this.initialize();
		}
    }
}
