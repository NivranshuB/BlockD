package application.model;

import application.model.game_objects.BlockType;
import application.model.game_objects.LeaderboardEntry;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Leaderboard {

    private static Leaderboard instance;

    List<LeaderboardEntry> entries = new ArrayList<>();

    private Leaderboard() { }

    public static Leaderboard getInstance() {
        if (instance == null) {
            instance = new Leaderboard();
        }
        return instance;
    }

    public void sortLeaderboard() {
        Collections.sort(entries);
    }

    public boolean addLeaderboardEntry(LeaderboardEntry newEntry) {
        if (qualifiesLeaderboard(newEntry.getScore())) {
            if (entries.size() >= 10) {
                entries.remove(entries.size() - 1);
            }
            entries.add(newEntry);
            sortLeaderboard();
            return true;
        } else {
            return false;
        }
    }

    public boolean qualifiesLeaderboard(int score) {
        sortLeaderboard();
        if (entries.size() < 10) {
            return true;
        }

        if (score > entries.get(entries.size() - 1).getScore()) {
            return true;
        } else {
            return false;
        }
    }

    public String toString() {
        String leaderboardStr = "X====================X";
        leaderboardStr += "\n      Leaderboard\n";
        leaderboardStr += "=======================";
        for (LeaderboardEntry entry : entries) {
            leaderboardStr += "\nName: " + entry.getName() + ", Score: " + entry.getScore() + ", Level: "
                    + entry.getLevel();
        }

        return leaderboardStr;
    }

    public LeaderboardEntry getEntryAtPosition(int pos) {
        return entries.get(pos);
    }

    public GridPane leaderboardRepresentation() {

        GridPane leaderboardRep = new GridPane();

        leaderboardRep.setHgap(60); //horizontal gap in pixels => that's what you are asking for
        leaderboardRep.setVgap(10); //vertical gap in pixels
        leaderboardRep.setPadding(new Insets(5, 10, 5, 10));

        for (int i = 0; i < entries.size(); i++) {

            HBox gridEntry = new HBox();

            LeaderboardEntry entry = entries.get(i);

            Text nameLabel = new Text(entry.getName());
            Text scoreLabel = new Text(entry.getScore() + "");
            Text levelLabel = new Text(entry.getLevel() + "");

            leaderboardRep.add(nameLabel, 0, i);
            leaderboardRep.add(scoreLabel, 1, i);
            leaderboardRep.add(levelLabel, 2, i);
        }

        return leaderboardRep;
    }
}
