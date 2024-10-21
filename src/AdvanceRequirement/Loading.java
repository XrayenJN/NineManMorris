package AdvanceRequirement;

import action.ActionManager;
import board.Board;
import game.Game;
import gui.Frame;
import player.Player;
import player.PlayerManager;
import piece.PieceManager;

import java.io.*;

/**
 * Loading class
 * Corresponding with all responsibility to load the available saved games.
 *
 * @author billgultiano, jordannathanael, yuchenwang
 * @version JDK 19
 */
public class Loading {
    /**
     * chosen saved games
     */
    private String loadedGameFile;
    /**
     * this class instance
     */
    private static Loading instance;

    /**
     * Initialization
     */
    private Loading() {}

    /**
     * get this class instance
     * @return instance
     */
    public static Loading getInstance() {
        if (instance == null) {
            instance = new Loading();
        }
        return instance;
    }

    /**
     * Set the chosen saved file
     * @param loadedGameFile saved file
     */
    public void setLoadedGameFile(String loadedGameFile){
        this.loadedGameFile = loadedGameFile;
    }

    /**
     * get the chosen saved file
     * @return saved file name
     */
    public String getLoadedGameFile(){ return loadedGameFile; }

    /**
     * Load the file from the directory
     * @param fileName filename name
     * @return the game object
     */
    public Game loadGameFromFile(String fileName) {
        try {
            fileName = "./savedFiles/" + fileName;

            FileInputStream fileIn = new FileInputStream(fileName);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);

            // read the corresponding object from txt file
            Game game = (Game) objectIn.readObject();
            Frame frame = (Frame) objectIn.readObject();
            Board board = (Board) objectIn.readObject();
            ActionManager actionManager = (ActionManager) objectIn.readObject();
            PlayerManager playerManager = (PlayerManager) objectIn.readObject();
            PieceManager pieceManager = (PieceManager) objectIn.readObject();

            // set the corresponding class that affects the gameplay
            Board.getInstance().setCurrentBoard(board);
            ActionManager.getInstance().setCurrentActionManager(actionManager);
            PlayerManager.getInstance().setCurrentPlayerManager(playerManager);
            PieceManager.getInstance().setCurrentPieceManager(pieceManager);
            Frame.getInstance();

            objectIn.close();
            System.out.println("Game loaded successfully.");
            return game;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading game: " + e.getMessage());
            return null;
        }
    }
}