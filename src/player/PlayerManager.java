package player;

import piece.PieceColorUtils;
import piece.PieceManager;
import undoFeatureInterface.Undoable;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;

/**
 * Player Manager class
 * Manage the player's turn
 *
 * @author billgultiano, jordannathanael, yuchenwang
 * @version JDK 19
 */
public class PlayerManager implements Serializable, Undoable {
    /**
     * current player of current turn
     */
    private Player currentPlayer;
    /**
     * opponent player in current turn
     */
    private Player opponent;
    /**
     * singleton instance
     */
    private static PlayerManager instance;
    /**
     * left-side player on the board (used for save-load feature)
     */
    private final Player player1;
    /**
     * right-side player on the board (used for save-load feature)
     */
    private final Player player2;
    /**
     * caretaker class which is used for undo feature
     */
    private PlayerCareTaker careTaker;

    /**
     * Initialization
     *
     * Create two players,
     */
    private PlayerManager(){
        player1 = new Player(Color.BLACK);
        player2 = new Player(Color.WHITE);
        currentPlayer = player1;
        opponent = player2;
        careTaker = new PlayerCareTaker();
    }

    /**
     * get instance
     * @return this class instance
     */
    public static PlayerManager getInstance(){
        if (instance == null){
            instance = new PlayerManager();
        }
        return instance;
    }

    /**
     * get left-side player
     * @return return the left-side player
     */
    public Player getPlayer1(){
        return player1;
    }

    /**
     * get right-side player
     * @return right-side player
     */
    public Player getPlayer2(){
        return player2;
    }

    /**
     * set this instance with existing instance from load feature
     * @param playerManager existed player manager object
     */
    public void setCurrentPlayerManager(PlayerManager playerManager){ instance = playerManager;}

    /**
     * get current player of current turn
     * @return the current player
     */
    public Player getCurrentPlayer(){ return currentPlayer;}

    /**
     * get the opponent of the current turn
     * @return the opponent player
     */
    public Player getOpponent() { return opponent; }

    /**
     * swap the current player of the turn
     */
    public void changeTurn() {
        Player player = currentPlayer;

        currentPlayer = opponent;
        opponent = player;
    }

    /**
     * check whether we already find a winner or not.
     * @return true if there is a winner, otherwise not
     */
    public boolean checkWinner(){
        if (PieceManager.getInstance().getPiecesLeft(PlayerManager.getInstance().getOpponent()) <= 2){
            JOptionPane.showMessageDialog(null, PieceColorUtils.colorToString(currentPlayer.getPieceColor()) + " wins!");
            return true;
        }
        return false;
    }

    @Override
    public void save(){
        careTaker.save();
    }

    @Override
    public void undo(){
        PlayerMemento lastMemento = careTaker.getLast();

        if (lastMemento != null){
            currentPlayer = lastMemento.getSavedCurrentPlayer();
            opponent = lastMemento.getSavedOpponent();
        }
    }
}
