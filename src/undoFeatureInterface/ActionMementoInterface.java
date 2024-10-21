package undoFeatureInterface;

import action.ActionStatus;
import board.Tile;
import piece.Piece;

/**
 * Action Memento Interface
 *
 * @author billgultiano
 * @version JDK19
 *
 * formatted by jordannathanael
 */
public interface ActionMementoInterface {
    /**
     * Applied for ActionMemento to get savedPiece
     * @return piece of the current action state
     */
    Piece getSavedPiece();

    /**
     * Applied for ActionMemento to get the tile of the action
     * @return the tile of the current action state
     */
    Tile getSavedTile();

    /**
     * Applied for ActionMemento to get the ActionStatus
     * @return the status of the current action state
     */
    ActionStatus getSavedStatus();

    /**
     * Applied for ActionMemento to get the situation of the current attack
     * @return the status whether this current action state is still on progress or already finished / not yet started
     */
    boolean isSavedInMiddleOfAction();
}
