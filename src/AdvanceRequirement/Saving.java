package AdvanceRequirement;


import action.ActionManager;
import board.Board;
import game.Game;
import gui.BottomPanel;
import gui.Frame;
import piece.PieceManager;
import player.PlayerManager;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * Saving class
 * Corresponding with all responsibilities which are related with saving the games into txt file
 *
 * @author billgultiano, jordannathanael, yuchenwang
 * @version JDK 19
 */
public class Saving {
    /**
     * Total games that we have in saved directory
     */
    private static int totalGames = 0;
    /**
     * This class instance
     */
    private static Saving instance;
    /**
     * saved game directory
     */
    public static String DIRECTORY = "./savedFiles";;

    /**
     * Initialization
     */
    private Saving() {
        setUpTotalGames();
    }

    /**
     * Find how many games that we have, if we create a new game, create a new file
     * by using this number
     */
    private void setUpTotalGames(){
        // find how many saved games we have now.
        String directory = "./savedFiles";

        File saveDirectory = new File(directory);

        // Get the list of files in the directory
        File[] files = saveDirectory.listFiles();

        // get the length of the files
        if (files != null){
            totalGames = files.length;
        } else {
            totalGames = 0;
        }
    }

    /**
     * Get instance of this class
     * @return this class's instance
     */
    public static Saving getInstance() {
        if (instance == null) {
            instance = new Saving();
        }
        return instance;
    }

    /**
     * Save the game to txt file
     * @param frame  frame of the saved game
     */
    public void saveGameToFile(Frame frame) {
        try {
            File saveDirectory = new File(DIRECTORY);

            if (!saveDirectory.exists()){

                boolean created = saveDirectory.mkdir();
                if (created){
                    System.out.println("New directory has been established");
                }
            }

            // if it a loaded game, get which game it is
            String loadGame = Loading.getInstance().getLoadedGameFile();
            String path = DIRECTORY;
            if (loadGame == null) {
                // create unique directory for this game run.
                path += "/game" + totalGames;
            } else {
                path += "/" + loadGame;
            }

            String fileName = path;

            File file = new File(fileName);
            if (file.exists()) {
                if (!file.delete()) {
                    System.out.println("Error deleting previous file.");
                    return;
                }
            }
            FileOutputStream fileOut = new FileOutputStream(fileName);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);

            // Retrieve the related data
            Game game = Game.getInstance();
            ActionManager actionManager = ActionManager.getInstance();
            PlayerManager playerManager = PlayerManager.getInstance();
            PieceManager pieceManager = PieceManager.getInstance();
            Board board = Board.getInstance();

            objectOut.writeObject(game);
            objectOut.writeObject(frame);
            objectOut.writeObject(board);
            objectOut.writeObject(actionManager);
            objectOut.writeObject(playerManager);
            objectOut.writeObject(pieceManager);
            objectOut.close();
            System.out.println("Game saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving game: " + e.getMessage());
        }
    }
}