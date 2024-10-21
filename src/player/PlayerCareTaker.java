package player;

import undoFeatureInterface.CaretakerInterface;

import java.io.Serializable;
import java.util.Stack;

/**
 * CareTaker class for Player to take care the current player and opponent in the turn
 *
 * @author billgultiano
 * @version JDK 19
 *
 * formatted by: jordannathanael
 */
public class PlayerCareTaker implements Serializable, CaretakerInterface {
    /**
     * Save all the currentPlayer and opponent each turn
     */
    private Stack<PlayerMemento> history = new Stack<>();

    @Override
    public void save(){
        Player currentPlayer = PlayerManager.getInstance().getCurrentPlayer();
        Player opponent = PlayerManager.getInstance().getOpponent();
        history.push(new PlayerMemento(currentPlayer, opponent));
    }

    /**
     * Retrieve the previous state
     * @return Memento (depends on each memento class)
     */
    public PlayerMemento getLast(){
        return !history.empty() ? history.pop() : null;
    }
}
