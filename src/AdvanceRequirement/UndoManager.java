package AdvanceRequirement;

import action.ActionManager;
import board.Board;
import piece.PieceManager;
import player.PlayerManager;
import undoFeatureInterface.CaretakerInterface;
import undoFeatureInterface.Undoable;

/**
 * UndoManager class to take care all the careTaker class that we have
 *
 * @author billgultiano
 * @version JDK 19
 *
 * formatted by jordannathanael
 */
public class UndoManager implements Undoable {
    /**
     * this class instance
     */
    private static UndoManager instance;

    /**
     * get this class instance
     * @return object of this class
     */
    public static UndoManager getInstance() {
        if (instance == null) {
            instance = new UndoManager();
        }
        return instance;
    }

    @Override
    public void save(){
        Board.getInstance().save();
        ActionManager.getInstance().save();
        PlayerManager.getInstance().save();
        PieceManager.getInstance().save();
    }

    @Override
    public void undo(){
        Board.getInstance().undo();
        ActionManager.getInstance().undo();
        PlayerManager.getInstance().undo();
        PieceManager.getInstance().undo();
        ActionManager.getInstance().updateStatus();
    }
}
