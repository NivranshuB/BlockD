package application.controller;

import java.io.IOException;

import application.model.Leaderboard;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class LeaderboardScreenController {

	@FXML
	private AnchorPane leaderboardRep;

	@FXML
	protected void initialize() {
		leaderboardRep.getChildren().add(Leaderboard.getInstance().leaderboardRepresentation());
	}

	/**
	 * Changes the scene from the main menu scene to the game menu scene.
	 */
	public void homeButtonPressed(ActionEvent event) throws IOException {
		ViewController.getInstance().changeScene(event, "/application/view/MainMenu.fxml");
	}
}
