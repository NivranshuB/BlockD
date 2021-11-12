package test.model;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;

/**
 * This class represents a Timer object which holds the time remaining for the current level of
 * a game instance.
 * @author Nivranshu Bose | 07/12/20
 *
 */
public class Timer {
	
	private static Integer STARTTIME;
	private static Timeline timeline;
	private static Integer timeSeconds = STARTTIME;
	
	public Timer(int startTime) {
		STARTTIME = startTime;
		timeSeconds = startTime;
	}
	
	/**
	 * Method which starts the count-down timer that decrements the timeSeconds every second.
	 */
	void startTimer() {
		
		if (timeline != null) {
			timeline.stop();
		}
		timeSeconds = STARTTIME;

		//update timerLabel
		timeline = new Timeline();
		timeline.setCycleCount(Timeline.INDEFINITE);
		
		//update count-down timer every frame
		timeline.getKeyFrames().add(
				new KeyFrame(Duration.seconds(1),
						new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent arg0) {
						timeSeconds--;
						//update timerLabel
						if (timeSeconds <= 0) {
							//do something when timer reaches 0
						}
					}
				}));
		timeline.playFromStart();
	}
	
	int getTime() {
		return timeSeconds;
	}
}
