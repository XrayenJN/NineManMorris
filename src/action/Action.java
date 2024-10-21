package action;

import board.Tile;

/**
 * Action abstract class
 * give player an executable action in the game
 *
 * @author billgultiano, jordannathanael, yuchenwang
 * @version JDK 19
 */
public interface Action {
    /**
     * Do the action of the corresponding action
     * @param tile towards this tile
     */
    public abstract void doAction(Tile tile);
}
