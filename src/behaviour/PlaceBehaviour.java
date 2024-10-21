package behaviour;

import action.ActionStatus;
import piece.PieceManager;
import player.Player;

/**
 * Place Behaviour class
 * Check if the player still has pieces left on the hand.
 *
 * @author billgultiano, jordannathanael, yuchenwang
 * @version JDK 19
 */
public class PlaceBehaviour extends Behaviour{
    /**
     * Initialization
     */
    public PlaceBehaviour(){
        this.status = ActionStatus.PLACE;
    }

    @Override
    public ActionStatus getActionStatus(Player player) {

        if (PieceManager.getInstance().getPiecesLeftInHand(player.getPieceColor()) > 0){
            return this.status;
        }
        return null;
    }
}
