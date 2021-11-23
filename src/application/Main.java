package application;

import java.util.Scanner;

import application.controller.ViewController;
import application.model.Game;
import application.model.game_objects.Board;
import application.utility.ConfirmBox;
import application.utility.DataRetriever;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Main extends Application {

	public void start(Stage primaryStage) {
		DataRetriever.getInstance().checkSaveData();
		try {
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("view/MainMenu.fxml"));
			Scene scene = new Scene(root,500,550);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			ViewController.getInstance().setPrimaryWindow(primaryStage);
			primaryStage.setMinHeight(550);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}

		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			public void handle(final WindowEvent windowEvent) {
				if (ViewController.getInstance().getOnGameScreen()) {
					Game.getInstance().pauseTimer();
				}

				boolean exit = ConfirmBox.displayConfirm("Game exit", "Are you sure you want to exit the game?");

				if (exit) {
					DataRetriever.saveAndExitGame();
					primaryStage.close();
				}
				windowEvent.consume();
				if (ViewController.getInstance().getOnGameScreen()) {
					Game.getInstance().getTimer().resumeTimer();
				}
			}
		});
	}

	public static void main(String[] args) {
		launch(args);
		//Game.getInstance().start();
	}
}
