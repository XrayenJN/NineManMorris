package game;

import AdvanceRequirement.Loading;
import AdvanceRequirement.UndoManager;
import action.Action;
import action.ActionManager;
import action.ActionStatus;
import behaviour.Behaviour;
import board.Board;
import gui.BottomPanel;
import gui.Frame;
import gui.MainMenu;
import piece.PieceManager;
import player.Player;
import player.PlayerManager;
import java.awt.*;
import java.io.Serializable;

/**
 * Game class, runnable class
 *
 * @author billgultiano, jordannathanael, yuchenwang
 * @version JDK 19
 */
public class Game implements Serializable {
    /**
     * left-side player
     */
    private Player player1;
    /**
     * right-side player
     */
    private Player player2;
    /**
     * singleton instance
     */
    private static Game currentGame;
    /**
     * boolean this game is still running
     */
    private boolean gameIsRunning;

    /**
     * get instance
     * @return this class instance
     */
    public static Game getInstance() {
        if (currentGame == null) {
            currentGame = new Game();
        }
        return currentGame;
    }

    /**
     * Initialization
     */
    public Game() {
        player1 = PlayerManager.getInstance().getPlayer1();
        player2 = PlayerManager.getInstance().getPlayer2();
        gameIsRunning = true;

        PieceManager.getInstance().initializePieces(player1, player2, 9,9);
    }

    /**
     * Once the game done, close this frame
     */
    private void closeGame() {
        Frame.getInstance().dispose();
    }

    /**
     * Change the gameIsRunning attribute
     */
    public void endGame(){
        gameIsRunning = false;
    }

    /**
     * check if there is a winner already or not
     * @return true if there is a player who only has two pieces left
     */
    public boolean gameEnded(){
        if (PieceManager.getInstance().getPiecesLeft(PlayerManager.getInstance().getOpponent()) <= 2 || !gameIsRunning){
            return true;
        }
        return false;
    }

    /**
     *  main game run, each iteration check the available action for currentPlayer
     */
    public void playGame() {
        MainMenu mainMenu = new gui.MainMenu();

        while (!gameEnded()){
            if (!ActionManager.getInstance().isInMiddleOfAction()){
                Player currentPlayer = PlayerManager.getInstance().getCurrentPlayer();
                for (Behaviour behaviour : currentPlayer.getBehaviourList()){
                    ActionStatus updateStatus = behaviour.getActionStatus(currentPlayer);
                    if (updateStatus != null) {
                        ActionManager.getInstance().setStatus(updateStatus);
                        break;
                    }
                }
            }

        }
        this.closeGame();
    }

    /**
     * Executable function main
     * @param args args for execution
     */
    public static void main(String[] args) {
        currentGame = Game.getInstance();
        currentGame.playGame();
    }

}