package application.controller;

import java.io.IOException;

import javafx.event.ActionEvent;

public class HelpScreenController {
	/**
	 * Changes the scene from the help screen to the game screen.
	 */
	public void okButtonPressed(ActionEvent event) throws IOException {
		new ViewController().changeScene(event, "/application/view/GameScreen.fxml");
	}
}
