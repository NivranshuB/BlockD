package application.controller;

import java.io.IOException;

import javafx.event.ActionEvent;

public class LeaderboardScreenController {
	/**
	 * Changes the scene from the main menu scene to the game menu scene.
	 */
	public void homeButtonPressed(ActionEvent event) throws IOException {
		new ViewController().changeScene(event, "/application/view/MainMenu.fxml");
	}
}
