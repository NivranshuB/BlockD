package application.controller;

import java.awt.*;
import java.io.IOException;

import application.model.Game;
import application.utility.DataRetriever;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class GameMenuController {

	@FXML
	private Button continueButton;

	@FXML
	protected void initialize() {
		if (!Game.getInstance().getStatus()) {
			continueButton.setDisable(true);
		}
	}

	/**
	 * Changes the scene from the main menu scene to the game menu scene.
	 */
	public void continueGameButtonPressed(ActionEvent event) throws IOException {
		ViewController.getInstance().changeScene(event, "/application/view/GameScreen.fxml");
	}
	
	/**
	 * Changes the scene from the main menu scene to the game menu scene.
	 */
	public void newGameButtonPressed(ActionEvent event) throws IOException {
		Game.getInstance().resetGame();
		Game.getInstance().setStatus(true);
		ViewController.getInstance().changeScene(event, "/application/view/GameScreen.fxml");
	}
	
	/**
	 * Changes the scene from the main menu scene to the game menu scene.
	 */
	public void homeButtonPressed(ActionEvent event) throws IOException {
		ViewController.getInstance().changeScene(event, "/application/view/MainMenu.fxml");
	}
}
