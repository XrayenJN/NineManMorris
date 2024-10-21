package undoFeatureInterface;

import player.Player;

/**
 * Player memento interface
 * To retrieve the current player and opponent from the memento itself
 */
public interface PlayerMementoInterface {
    /**
     * Applied for PlayerMemento to save current Player
     * @return the saved current player
     */
    Player getSavedCurrentPlayer();

    /**
     * Applied for PlayerMemento to save opponent Player
     * @return the saved opponent
     */
    Player getSavedOpponent();
}
