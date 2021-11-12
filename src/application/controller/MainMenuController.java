package application.controller;

import java.io.IOException;

import javafx.event.ActionEvent;

public class MainMenuController {

	/**
	 * Changes the scene from the main menu scene to the game menu scene.
	 */
	public void soloGameButtonPressed(ActionEvent event) throws IOException {
		new ViewController().changeScene(event, "/application/view/GameMenu.fxml");
	}
	
	/**
	 * Changes the scene from the main menu scene to the leaderboard scene.
	 */
	public void leaderboardButtonPressed(ActionEvent event) throws IOException {
		new ViewController().changeScene(event, "/application/view/LeaderboardScreen.fxml");
	}
	
	/**
	 * Changes the scene from the main menu scene to the game menu scene.
	 */
	public void exitButtonPressed(ActionEvent event) throws IOException {
		new ViewController().windowClose(event);
	}
}
