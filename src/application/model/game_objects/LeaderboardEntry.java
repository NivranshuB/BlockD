package application.model.game_objects;

public class LeaderboardEntry implements Comparable<LeaderboardEntry>{
    private String name;
    private int score, level;

    public LeaderboardEntry(String nm, int sr, int lvl) {
        name = nm;
        score = sr;
        level = lvl;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public int getLevel() {
        return level;
    }

    @Override
    public int compareTo(LeaderboardEntry o) {
        if (o.getScore() > this.getScore()) {
            return 1;
        } else if (o.getScore() < this.getScore()) {
            return -1;
        } else {
            return 0;
        }
    }
}
