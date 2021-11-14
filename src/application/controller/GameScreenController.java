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
import javafx.scene.control.TextField;
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

	@FXML
	private TextField levelField, totalScoreField, regionScoreField, targetScoreField, timerField;
	
	private Game game = Game.getInstance();

    @FXML
    protected void initialize() {
    	game.getTimer().setTimerField(timerField);
    	if (game.getTimer().getSeconds() == 0) {//if time is 0 then timer has not been started yet
			game.getTimer().startTimer();
		} else {
    		game.getTimer().resumeTimer();
		}

    	boardRep.getChildren().add(game.getBoard().boardRepresentation());

    	level.setText("Level: ");
    	levelField.setText(game.getLevel().getLevel() + "");
    	levelField.setEditable(false);

    	totalScore.setText("Score: ");
    	totalScoreField.setText(game.getTotalScore().getScore() + "");
    	totalScoreField.setEditable(false);

    	regionScore.setText("Region: ");
    	regionScoreField.setText(game.getRegionScore().getScore() + "");
    	regionScoreField.setEditable(false);

    	targetScore.setText("Target: ");
    	targetScoreField.setText(game.getTargetScore().getScore() + "");
    	targetScoreField.setEditable(false);

    	timer.setText("Time: ");
    }

    protected void update() {
    	boardRep.getChildren().clear();
		boardRep.getChildren().add(game.getBoard().boardRepresentation());

		levelField.setText(game.getLevel().getLevel() + "");

		totalScoreField.setText(game.getTotalScore().getScore() + "");

		regionScoreField.setText(game.getRegionScore().getScore() + "");

		targetScoreField.setText(game.getTargetScore().getScore() + "");
	}
	
	/**
	 * Changes the scene from the game screen to the home menu scene.
	 */
	public void homeButtonPressed(ActionEvent event) throws IOException {
		game.getTimer().pauseTimer();
		game.setStatus(true);
		new ViewController().changeScene(event, "/application/view/MainMenu.fxml");
	}
	
	/**
	 * Changes the scene from the home screen to the help menu screen.
	 */
	public void helpButtonPressed(ActionEvent event) throws IOException {
		game.getTimer().pauseTimer();
		new ViewController().changeScene(event, "/application/view/HelpScreen.fxml");
	}

	public void keyPressed(KeyEvent event) {
		help.setText("Help: Use arrow keys to move along the board, and 'b' to break area");
        switch (event.getCode()) {
        case UP:
        	game.getBoard().move("w");
            break;
        case LEFT:
        	game.getBoard().move("a");
            break;
        case DOWN:
        	game.getBoard().move("s");
            break;
        case RIGHT:
        	game.getBoard().move("d");
            break;
        case B:
        	game.updateScores();
            break;
        default:
        	help.setText("Help: Invalid move, please try again");
            break;
        }

		game.updateRegionScore();
		this.update();
    	
		if (game.getTargetScore().getScore() <= 0) {

			game.getLevel().setLevel(game.getLevel().getLevel() + 1);
			levelField.setText(game.getLevel().getLevel() + "");
			
			game.setTargetScore(new TargetScore(game.getLevel().getTarget()));
			targetScoreField.setText(game.getTargetScore().getScore() + "");
			
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
			this.update();
		}
    }
}
