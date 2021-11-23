package application.utility;

import application.model.Game;
import application.model.Leaderboard;
import application.model.game_objects.Block;
import application.model.game_objects.BlockType;
import application.model.game_objects.Board;
import application.model.game_objects.LeaderboardEntry;

import javax.xml.crypto.Data;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DataRetriever {

    private static DataRetriever instance;

    private DataRetriever() {

    }

    public static DataRetriever getInstance() {
        if (instance ==  null) {
            instance = new DataRetriever();
        }
        return instance;
    }

    /**
     * This method first checks if there exists a save file from which it should retrieve the saved
     * categories and questions of a game that has not been finished yet. If such a file exists
     * then it will set up the questionBank to consists of these saved questions, or else it will
     * set up the _questionBank to consists of new categories and questions.
     */
    public void checkSaveData() {
        String save_loc = System.getProperty("user.dir") + System.getProperty("file.separator") + "save_data";
        File saveDirectory = new File(save_loc);

        //check if there exists a game_data containing leaderboard and game data
        if (Files.exists(saveDirectory.toPath())) {
            String leaderboard_save_loc = save_loc + System.getProperty("file.separator") + "leaderboard_data" +
                    System.getProperty("file.separator") + "leaderboard";
            File leaderboardFile = new File(leaderboard_save_loc);

            parseLeaderboardData(leaderboardFile);

            //File[] saveFile = saveDirectory.listFiles();

            //parseSavedData(saveFile[0]);
        }
    }

    private void parseSavedData(File saveFile) {
        String name = saveFile.getName();
        String path = saveFile.getAbsolutePath();

        try {
            BufferedReader fileReader = new BufferedReader(new FileReader(saveFile));
            String line;
            while ((line = fileReader.readLine()) != null) {
                //read in board line
                //read in game other info
            }
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void parseLeaderboardData(File saveFile) {
//        String name = saveFile.getName();
//        String path = saveFile.getAbsolutePath();

        try {
            BufferedReader fileReader = new BufferedReader(new FileReader(saveFile));
            System.out.println("Leaderboard saved data:");
            String line;
            while ((line = fileReader.readLine()) != null) {
                System.out.println(line);
                String[] entryDetails = line.split("\\|");
                System.out.println("Number of parts = " + entryDetails.length);
                System.out.println("Name is '" + entryDetails[0] + "'");
                System.out.println("Score is '" + entryDetails[1] + "'");
                System.out.println("Level is '" + entryDetails[2] + "'");
                Leaderboard leaderboard = Leaderboard.getInstance();
                LeaderboardEntry lineEntry = new LeaderboardEntry(entryDetails[0], Integer.valueOf(entryDetails[1]),
                        Integer.valueOf(entryDetails[2]));
                leaderboard.addLeaderboardEntry(lineEntry);
            }
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method deletes any previous save_data recursively and then rewrites
     * the question/category data and the winnings data into the save_data directory.
     */
    public static void saveAndExitGame() {

        String save_loc = System.getProperty("user.dir") + System.getProperty("file.separator") + "save_data";
        String game_save_loc = save_loc + System.getProperty("file.separator") + "game_data";
        String leaderboard_save_loc = save_loc + System.getProperty("file.separator") + "leaderboard_data";

        Path saveData = Paths.get(save_loc);
        Path pathGameSaveData = Paths.get(game_save_loc);
        Path leaderboardSaveData = Paths.get(leaderboard_save_loc);

        deleteDirectory(new File(save_loc));//Delete existing app details that are saved

        try {
            Files.createDirectories(saveData);
            Files.createDirectories(pathGameSaveData);//Create a new folder for where game data will be saved
            Files.createDirectories(leaderboardSaveData);//Create a new folder for where leaderboard data will be saved
        } catch (IOException e) {
            System.err.println("Failed to create directory!" + e.getMessage());
        }

        try {//Write data in leaderboard file
            FileWriter leaderboardWriter = new FileWriter(leaderboard_save_loc + System.getProperty("file.separator")
                    + "leaderboard");

            Leaderboard leaderboard = Leaderboard.getInstance();

            for (LeaderboardEntry entry : leaderboard.getEntries()) {
                leaderboardWriter.write(entry.getName() + "|" + entry.getScore() + "|" +
                        entry.getLevel() + "\n");
            }
            leaderboardWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Game gameToSave = Game.getInstance();

        try {//Write details for the game info barring the game board
            FileWriter gameWriter = new FileWriter((game_save_loc + System.getProperty("file.separator") +
                    "game_details"));

            gameWriter.write("Level:" + gameToSave.getLevel().getLevel() + "\n");
            gameWriter.write("Total:" + gameToSave.getTotalScore().getScore() + "\n");
            gameWriter.write("Region:" + gameToSave.getRegionScore().getScore() + "\n");
            gameWriter.write("Time:" + gameToSave.getTimer() + "\n");
            gameWriter.write("Target:" + gameToSave.getTargetScore().getScore());

            gameWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Board gameBoard = gameToSave.getBoard();
        Block[][] blocks = gameBoard.getBlockArray();

        try {//Write details of the game board
            FileWriter boardWriter = new FileWriter(game_save_loc + System.getProperty("file.separator")
                    + "game_board");

            for (int i = 0; i < blocks.length; i++) {
                for (int j = 0; j < blocks[0].length; j++) {

                    Block currBlock = blocks[i][j];

                    if (currBlock == null) {
                        boardWriter.write("_");
                    } else if (currBlock.getType() == BlockType.Yellow) {
                        boardWriter.write("y");
                    } else if (currBlock.getType() == BlockType.Green) {
                        boardWriter.write("g");
                    } else if (currBlock.getType() == BlockType.Red) {
                        boardWriter.write("r");
                    }

                }
                boardWriter.write("\n");
            }

            boardWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * This method deletes a specified directory and all its contents recursively.
     *
     * @param directoryToBeDeleted
     * @return | true if File deleted successfully else return false
     */
    private static boolean deleteDirectory(File directoryToBeDeleted) {
        File[] contents = directoryToBeDeleted.listFiles();
        if (contents != null) {
            for (File file : contents) {
                deleteDirectory(file);
            }
        }

        return directoryToBeDeleted.delete();
    }
}
