package action;

import board.Tile;
import piece.Piece;
import undoFeatureInterface.CaretakerInterface;

import java.io.Serializable;
import java.util.Stack;

/**
 * ActionCareTaker
 * take care the undo and save feature
 *
 * @author billgultiano
 * @version JDK 19
 *
 * formatted by jordannathanael
 */
public class ActionCareTaker implements Serializable, CaretakerInterface {
    /**
     * Stack of memento for undo feature
     */
    private Stack<ActionMemento> history = new Stack<>();

    @Override
    public void save(){
        Piece piece = ActionManager.getInstance().getPiece();
        Tile tile = ActionManager.getInstance().getTile();
        ActionStatus status = ActionManager.getInstance().getStatus();
        boolean inMiddleOfAction = ActionManager.getInstance().isInMiddleOfAction();
        history.push(new ActionMemento(piece, tile, status, inMiddleOfAction));
    }

    /**
     * Retrieve the previous state
     * @return Memento (depends on each memento class)
     */
    public ActionMemento getLast(){
        return !history.empty() ? history.pop() : null;
    }
}
