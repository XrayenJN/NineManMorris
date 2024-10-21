package action;

import board.Tile;
import piece.Piece;
import undoFeatureInterface.ActionMementoInterface;

import java.io.Serializable;

/**
 * ActionMemento class
 * Data Class to save the ActionManager state for each turn
 *
 * @author billgultiano
 * @version JDK 19
 *
 * formatted by jordannathanael
 */
public class ActionMemento implements Serializable, ActionMementoInterface {
    /**
     * The piece that want to be moved around / deleted
     */
    private Piece savedPiece;
    /**
     * Corresponding tile that is related with the action
     */
    private Tile savedTile;
    /**
     * Action status of the current action
     */
    private ActionStatus savedStatus;
    /**
     * Determine whether the action is still an action that hasn't been done yet or not
     */
    private boolean savedInMiddleOfAction;

    /**
     * Initialization
     *
     * @param piece piece of the current action
     * @param tile tile of the current action
     * @param status status of the current action
     * @param inMiddleOfAction is current progress in the middle of action or not
     */
    public ActionMemento(Piece piece, Tile tile, ActionStatus status, boolean inMiddleOfAction){
        savedPiece = (piece != null) ? new Piece(piece.getPieceColor()) : null;
        savedTile = (tile != null) ? new Tile(tile) : null;
        savedStatus = (status != null) ? status : null;
        savedInMiddleOfAction = inMiddleOfAction;
    }

    @Override
    public Piece getSavedPiece() {
        return savedPiece;
    }

    @Override
    public Tile getSavedTile() {
        return savedTile;
    }

    @Override
    public ActionStatus getSavedStatus() {
        return savedStatus;
    }

    @Override
    public boolean isSavedInMiddleOfAction() {
        return savedInMiddleOfAction;
    }
}
