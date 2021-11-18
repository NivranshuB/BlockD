package application.model;

import application.model.game_objects.LeaderboardEntry;

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
}
