package board;

import undoFeatureInterface.CaretakerInterface;

import java.io.Serializable;
import java.util.Stack;

/**
 * BoardCareTaker class to save and undo the board state
 * @author billgultiano
 * @version JDK19
 *
 * formatted by jordannathanael
 */
public class BoardCareTaker implements Serializable, CaretakerInterface {
    /**
     * Stack of the board memento
     */
    private Stack<BoardMemento> history = new Stack<>();

    @Override
    public void save(){
        history.push(new BoardMemento(Board.getInstance().getBoard()));
    }

    /**
     * Retrieve the previous state
     * @return Memento (depends on each memento class)
     */
    public BoardMemento getLast(){
        return !history.empty() ? history.pop() : null;
    }
}