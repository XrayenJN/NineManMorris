package player;

import undoFeatureInterface.PlayerMementoInterface;

import java.io.Serializable;

/**
 * PlayerMemento class to save the currentPlayer and opponent
 * act as Data Class
 *
 * @author billgultiano
 * @version JDK 19
 *
 * formatted by jordannathanael
 */
public class PlayerMemento implements Serializable, PlayerMementoInterface {
    /**
     * saved the current player
     */
    private Player savedCurrentPlayer;
    /**
     * saved the opponent
     */
    private Player savedOpponent;

    /**
     * Initialization
     * @param currentPlayer current player in the turn
     * @param opponent opponent in the turn
     */
    public PlayerMemento(Player currentPlayer, Player opponent){
        savedCurrentPlayer = currentPlayer;
        savedOpponent = opponent;
    }

    @Override
    public Player getSavedCurrentPlayer() {
        return savedCurrentPlayer;
    }

    @Override
    public Player getSavedOpponent() {
        return savedOpponent;
    }
}
