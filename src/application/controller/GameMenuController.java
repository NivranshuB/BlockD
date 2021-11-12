package application.controller;

import java.io.IOException;

import application.model.Game;
import javafx.event.ActionEvent;

public class GameMenuController {
	/**
	 * Changes the scene from the main menu scene to the game menu scene.
	 */
	public void continueGameButtonPressed(ActionEvent event) throws IOException {
		new ViewController().changeScene(event, "/application/view/GameScreen.fxml");
	}
	
	/**
	 * Changes the scene from the main menu scene to the game menu scene.
	 */
	public void newGameButtonPressed(ActionEvent event) throws IOException {
		Game.getInstance().resetGame();
		new ViewController().changeScene(event, "/application/view/GameScreen.fxml");
	}
	
	/**
	 * Changes the scene from the main menu scene to the game menu scene.
	 */
	public void homeButtonPressed(ActionEvent event) throws IOException {
		new ViewController().changeScene(event, "/application/view/MainMenu.fxml");
	}
}
