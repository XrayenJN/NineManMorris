package behaviour;

import action.ActionStatus;
import player.Player;

import java.io.Serializable;

/**
 * Behaviour class
 * Check the player status like pieces on their hand / game
 * Determine which action is suitable for the corresponding situation.
 *
 * @author billgultiano, jordannathanael, yuchenwang
 * @version JDK 19
 */
public abstract class Behaviour  implements Serializable {
    /**
     * Action that can be produced by corresponding behaviour
     */
    protected ActionStatus status;

    /**
     * Produce the action in case the situation is met
     * @param player player who will do action this turn
     * @return action status which determines which action is possible to be executed next
     */
    public abstract ActionStatus getActionStatus(Player player);
}
