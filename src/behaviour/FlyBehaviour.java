package behaviour;

import action.ActionManager;
import action.ActionStatus;
import piece.PieceManager;
import player.Player;

/**
 * Fly Behaviour class
 * check if player only has 3 pieces left on the game or not
 *
 * @author billgultiano, jordannathanael, yuchenwang
 * @version JDK 19
 */
public class FlyBehaviour extends Behaviour{
    /**
     * Initialization
     */
    public FlyBehaviour(){
        this.status = ActionStatus.FLY;
    }

    @Override
    public ActionStatus getActionStatus(Player player) {
        Integer pieceAmountOnHand = PieceManager.getInstance().getPiecesLeftInHand(player.getPieceColor());
        Integer pieceAmountOnBoard = PieceManager.getInstance().getPiecesLeft(player);
        System.out.println("---------------------------");
        System.out.println("FLY BEHAVIOUR PIECES MOUNT: "+pieceAmountOnHand);
        System.out.println("IN MIDDLE OF ACTION?: "+!ActionManager.getInstance().isInMiddleOfAction());

        if (pieceAmountOnHand == 0 && pieceAmountOnBoard == 3 && !ActionManager.getInstance().isInMiddleOfAction()){
            return this.status;
        }

        return null;
    }
}
