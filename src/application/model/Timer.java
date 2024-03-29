package application.model;

import application.controller.ViewController;
import application.model.game_objects.LeaderboardEntry;
import application.utility.ConfirmBox;
import application.utility.DataRetriever;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DialogEvent;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Optional;

/**
 * This class represents a Timer object which holds the time remaining for the current level of
 * a game instance.
 * @author Nivranshu Bose | 07/12/20
 *
 */
public class Timer {

	private int startTime = 120;

	private static Timer timerInstance;

	private static Timeline timeline;

	private int timeSeconds;

	private TextField timerField;

	private DecimalFormat formatter = new DecimalFormat("00");

	private Timer() {}

	public static Timer getInstance() {
		if (timerInstance == null) {
			timerInstance = new Timer();
		}
		return timerInstance;
	}

	
	/**
	 * Method which starts the count-down timer that decrements the timeSeconds every second.
	 */
	public void startTimer() {

		if (timeline != null) {
			timeline.stop();
		}

		timeSeconds = startTime;

		timerField.setText(Timer.this.getTimeString(timeSeconds));
		timerField.setEditable(false);

		//update timerLabel
		timeline = new Timeline();
		timeline.setCycleCount(Timeline.INDEFINITE);
		
		//update count-down timer every frame
		timeline.getKeyFrames().add(
				new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent arg0) {
						timeSeconds--;
						//update timerLabel
						if (timeSeconds <= 0) {
							//do something when timer reaches 0
							pauseTimer();
							timerField.setText("GameOver");
							timerField.setStyle("-fx-text-fill: black;");
							try {
								timeOut();
							} catch (IOException e) {
								e.printStackTrace();
							}
						} else if (timeSeconds < 30) {
							timerField.setText(Timer.this.getTimeString(timeSeconds));
							timerField.setStyle("-fx-text-fill: red;");
						} else {
							timerField.setText(Timer.this.getTimeString(timeSeconds));
							timerField.setStyle("-fx-text-fill: green;");
						}
					}
				}));

		timeline.playFromStart();
	}

	public String toString() {
		return timeSeconds + "";
	}

	public void resetTimer() {
		timerInstance = null;
	}

	public void pauseTimer() {
		timeline.pause();
	}

	public void setTimerField(TextField timer) {
		timerField = timer;
	}

	public void resumeTimer() {
		timerField.setText(Timer.this.getTimeString(timeSeconds));
		timerField.setEditable(false);
		timeline.play();
	}

	public int getSeconds() {
		return timeSeconds;
	}

	public void setStartTime(int newStartTime) {
		startTime = newStartTime;
	}

	private String getTimeString(int seconds) {
		formatter = new DecimalFormat("00");
		return formatter.format(seconds/60) + ":" + formatter.format(seconds%60);
	}

	private void timeOut() throws IOException {
		Game game = Game.getInstance();
		game.setStatus(false);

		Leaderboard leaderboard = Leaderboard.getInstance();
		int score = Game.getInstance().getTotalScore().getScore();

		if (leaderboard.qualifiesLeaderboard(score)) {
			highscoreAlert();
		} else {

			ViewController.getInstance().changeScene("/application/view/GameMenu.fxml");
			ViewController.getInstance().setOnGameScreen(false);

			game.setStatus(false);

			// set alert type
			Alert a = new Alert(Alert.AlertType.NONE);
			a.setAlertType(Alert.AlertType.INFORMATION);
			a.setTitle("Game over");
			a.setContentText("Your score was " + Game.getInstance().getTotalScore().getScore());

			// show the dialog
			a.show();
		}

	}

	private void highscoreAlert() throws IOException{

//		ViewController.getInstance().changeScene("/application/view/LeaderboardScreen.fxml");
		ViewController.getInstance().setOnGameScreen(false);

		Game game = Game.getInstance();
		game.setStatus(false);

		Leaderboard leaderboard = Leaderboard.getInstance();
		int score = Game.getInstance().getTotalScore().getScore();

		Stage highScoreBox = new Stage();
		highScoreBox.setTitle("New high score!");

		BorderPane root = (BorderPane) FXMLLoader.load(getClass().getResource("/application/view/HighScore.fxml"));
		root.requestFocus();
		Scene scene = new Scene(root,450,250);

		highScoreBox.setScene(scene);
		highScoreBox.show();

		highScoreBox.setOnCloseRequest(new EventHandler<WindowEvent>() {
			public void handle(final WindowEvent windowEvent) {
				highScoreBox.close();

				String name = "Player Anon";

				Game game = Game.getInstance();
				Leaderboard leaderboard = Leaderboard.getInstance();

				LeaderboardEntry newEntry = new LeaderboardEntry(name, game.getTotalScore().getScore(),
						game.getLevel().getLevel());
				leaderboard.addLeaderboardEntry(newEntry);
				System.out.println("Added leaderboard entry: Name = " + newEntry.getName() + ", Score = " +
						newEntry.getScore() + ", Level = " + newEntry.getLevel());
				System.out.println(leaderboard);

				try {
					ViewController.getInstance().changeScene("/application/view/LeaderboardScreen.fxml");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
	}
}
