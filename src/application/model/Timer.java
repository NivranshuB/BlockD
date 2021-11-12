package application.model;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.util.Duration;

import java.text.DecimalFormat;

/**
 * This class represents a Timer object which holds the time remaining for the current level of
 * a game instance.
 * @author Nivranshu Bose | 07/12/20
 *
 */
public class Timer {

	private static int STARTTIME = 300;

	private static Timer timerInstance;

	private static Timeline timeline;

	private int timeSeconds;

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
	public void startTimer(TextField timerField) {

		if (timeline != null) {
			timeline.stop();
		}

		timerField.setText("05:00");
		timerField.setEditable(false);

		timeSeconds = STARTTIME;

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
							timerField.setText("GameOver");
							timerField.setStyle("-fx-text-fill: black;");
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
	
	int getTime() {
		return timeSeconds;
	}

	public void resetTimer() {
		timerInstance = null;
	}

	private String getTimeString(int seconds) {
		formatter = new DecimalFormat("00");
		return formatter.format(seconds/60) + ":" + formatter.format(seconds%60);
	}
}
