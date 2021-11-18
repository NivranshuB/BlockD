package application.controller;

import application.model.Game;
import application.model.Leaderboard;
import application.model.game_objects.LeaderboardEntry;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class HighScoreBoxController {

    @FXML
    private TextField nameInput;

    @FXML
    private Label textScore;

    @FXML
    protected void initialize() {
        textScore.setText("Enter your name:\n(Your score was " + Game.getInstance().getTotalScore().getScore() + ")");
    }

    /**
     *
     */
    public void submitButtonPressed(ActionEvent event) throws IOException {
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.close();

        String name = "Player Anon";

        if (!nameInput.getText().equals("")) {
            name = nameInput.getText();
        }

        Game game = Game.getInstance();
        Leaderboard leaderboard = Leaderboard.getInstance();

        LeaderboardEntry newEntry = new LeaderboardEntry(name, game.getTotalScore().getScore(),
                game.getLevel().getLevel());
        leaderboard.addLeaderboardEntry(newEntry);
        System.out.println("Added leaderboard entry: Name = " + newEntry.getName() + ", Score = " +
                newEntry.getScore() + ", Level = " + newEntry.getLevel());
        System.out.println(leaderboard);
    }
}
