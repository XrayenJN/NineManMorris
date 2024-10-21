package behaviour;

import action.ActionManager;
import action.ActionStatus;
import piece.PieceManager;
import player.Player;

/**
 * Move Behaviour class
 * Check if player has established all pieces on the board yet or not.
 *
 * @author billgultiano, jordannathanael, yuchenwang
 * @version JDK 19
 */
public class MoveBehaviour extends Behaviour{
    /**
     * Initialization
     */
    public MoveBehaviour() {
        this.status = ActionStatus.MOVE;
    }

    @Override
    public ActionStatus getActionStatus(Player player) {
        Integer pieceAmountOnHand = PieceManager.getInstance().getPiecesLeftInHand(player.getPieceColor());
        Integer pieceAmountOnBoard = PieceManager.getInstance().getPiecesLeft(player);
        System.out.println("---------------------------");
        System.out.println("MOVE BEHAVIOUR PIECES MOUNT: "+pieceAmountOnHand);
        System.out.println("IN MIDDLE OF ACTION?: "+!ActionManager.getInstance().isInMiddleOfAction());


        if (pieceAmountOnHand == 0 && pieceAmountOnBoard > 3 && !ActionManager.getInstance().isInMiddleOfAction()){
            return this.status;
        }

        return null;
    }
}
