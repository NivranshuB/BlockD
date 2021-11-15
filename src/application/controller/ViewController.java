package application.controller;

import java.io.IOException;

import application.utility.ConfirmBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DialogEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import javax.swing.text.View;

public class ViewController {

	private static ViewController instance;

	private Stage primaryWindow;

	private boolean onGameScreen = false;

	private ViewController() {
	}

	public static ViewController getInstance() {
		if (instance == null) {
			instance = new ViewController();
		}
		return instance;
	}

	public void setPrimaryWindow(Stage primaryStage) {
		primaryWindow = primaryStage;
	}

	/**
	 * When this method is called, it will change current scene to the scene given as an input string.
	 * @throws IOException 
	 */
	public void changeScene(ActionEvent event, String scenePath) throws IOException {
		Parent parent = FXMLLoader.load(getClass().getResource(scenePath));
		Scene newScene = new Scene(parent);
		
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		
		window.setScene(newScene);
		window.show();
		parent.requestFocus();
	}


	/**
	 * When this method is called, it will change current scene to the scene given as an input string.
	 * @throws IOException
	 */
	public void changeScene(String scenePath) throws IOException {
		Parent parent = FXMLLoader.load(getClass().getResource(scenePath));
		Scene newScene = new Scene(parent);

		primaryWindow.setScene(newScene);
		primaryWindow.show();
		parent.requestFocus();
	}

	/**
	 * When this method is called, it will change current scene to the scene given as an input string.
	 * @throws IOException 
	 */
	public void windowClose(ActionEvent event) throws IOException {
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		
		boolean gameExit = ConfirmBox.displayConfirm("Quit the game","Are you sure you want to quit the game?"
				+ " (don't worry your progress will be saved)");
		
		if (gameExit) {
			window.close();
		}
	}
	
	/**
	 * When this method is called, it will change current scene to the scene given as an input string.
	 * @throws IOException 
	 */
	public void stageClose(WindowEvent event) throws IOException {
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		
		boolean gameExit = ConfirmBox.displayConfirm("Quit the game","Are you sure you want to quit the game?"
				+ " (don't worry your progress will be saved)");
		
		if (gameExit) {
			window.close();
		}
	}

	public boolean getOnGameScreen() {
		return onGameScreen;
	}

	public void setOnGameScreen(boolean status) {
		onGameScreen = status;
	}
}
