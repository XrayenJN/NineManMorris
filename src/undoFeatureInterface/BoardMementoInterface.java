package undoFeatureInterface;

import board.Tile;

/**
 * Board memento interface, used by the BoardMemento
 */
public interface BoardMementoInterface {
    /**
     * Applied for BoardMemento to get board data
     * @return Tile[][] represents the 9ManMorris board
     */
    Tile[][] getBoard();
}
