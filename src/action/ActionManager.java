package action;

import board.Tile;
import gui.BottomPanel;
import piece.Piece;
import piece.PieceColorUtils;
import player.PlayerManager;
import undoFeatureInterface.Undoable;

import java.awt.*;
import java.io.Serializable;

/**
 * Action Manager
 * take care of the action for each turn
 *
 * @author billgultiano, jordannathanael, yuchenwang
 * @version JDK 19
 */
public class ActionManager implements Serializable, Undoable {
    /**
     * this class instance
     */
    private static ActionManager instance;
    /**
     * The piece that want to be moved around / deleted
     */
    private Piece piece;
    /**
     * Corresponding tile that is related with the action
     */
    private Tile tile;
    /**
     * Action status of the current action
     */
    private ActionStatus status;
    /**
     * Determine whether the action is still an action that hasn't been done yet or not
     */
    private boolean inMiddleOfAction = false;
    /**
     * Caretaker class for undo feature
     */
    private ActionCareTaker careTaker;

    /**
     * Initialization
     */
    private ActionManager(){
        careTaker = new ActionCareTaker();
    }

    /**
     * Get this instance object
     * @return this class's object
     */
    public static ActionManager getInstance() {
        if (instance == null) {
            instance = new ActionManager();
        }
        return instance;
    }

    /**
     * set the this class instance with existing object
     * @param actionManager existing action manager object
     */
    public void setCurrentActionManager(ActionManager actionManager){
        instance = actionManager;
//        setStatus(actionManager.getStatus());
    }

    /**
     * set in the middle of action status
     * in case the action is not done yet, the behaviour will not change the current action
     *
     * @param process the process of attack action
     */
    public void setInMiddleOfAction(Boolean process){ this.inMiddleOfAction = process; }

    /**
     * check if the current action is done yet or not / still on progress
     * @return the value
     */
    public boolean isInMiddleOfAction() { return inMiddleOfAction; }

    /**
     * set the piece that want to be moved / removed around
     * @param piece piece that want to be moved / removed
     */
    public void setPiece(Piece piece){
        this.piece = piece;
    }

    /**
     * set the current action status
     * @param status status of the new action
     */
    public void setStatus(ActionStatus status) {
        this.status = status;
        updateStatus();
    }

    /**
     * update the status and corresponding to the panel
     */
    public void updateStatus(){
        this.status = status;
        if (this.status != null) {
            Color pieceColor = PlayerManager.getInstance().getCurrentPlayer().getPieceColor();

            String label =  PieceColorUtils.colorToString(pieceColor) + "'s Turn. " + this.status.toString() +" mode.";
            BottomPanel.setLabel(label);
        }
    }

    /**
     * get the actionStatus
     * @return ActionStatus
     */
    public ActionStatus getStatus() { return status; }

    /**
     * get the piece that want to be removed / moved around
     * @return piece
     */
    public Piece getPiece(){return piece;}

    /**
     * check if the piece for the current action is already established or not
     * @return true if it is
     */
    public boolean isPieceFilled(){ return piece != null; }

    /**
     * set the corresponding tile that want to be moved or removed
     * @param tile tile
     */
    public void setTile(Tile tile){
        this.tile = tile;
    }

    /**
     * get the tile for the action
     * @return tile that is related with current action
     */
    public Tile getTile() {return tile;}

    /**
     * check if the tile is filled or not
     * @return true if it is filled
     */
    public boolean isTileFilled(){ return tile != null; }

    @Override
    public void save(){
        careTaker.save();
    }

    @Override
    public void undo(){
        ActionMemento lastMemento = careTaker.getLast();

        if (lastMemento != null){
            piece = lastMemento.getSavedPiece();
            tile = lastMemento.getSavedTile();
            status = lastMemento.getSavedStatus();
            inMiddleOfAction = lastMemento.isSavedInMiddleOfAction();
        }
    }
}
