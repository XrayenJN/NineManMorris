package action;

import AdvanceRequirement.UndoManager;
import board.Board;
import board.Tile;
import piece.PieceManager;
import player.PlayerManager;

import javax.swing.*;

/**
 * Place Action
 * players can choose a piece from their hand and move it into any available tiles on the board
 *
 * @author billgultiano, jordannathanael, yuchenwang
 * @version JDK 19
 */
public class PlaceAction implements Action {

    @Override
    public void doAction(Tile tile) {
        // Place piece
        if (ActionManager.getInstance().isPieceFilled() && tile.isEmpty() && !ActionManager.getInstance().isTileFilled()) {

            tile.setPiece(ActionManager.getInstance().getPiece());
            ActionManager.getInstance().setPiece(null);
            if (Board.getInstance().isTilePartOfMill(tile)){
                ActionManager.getInstance().setInMiddleOfAction(true);
            } else {
                ActionManager.getInstance().setInMiddleOfAction(false);
            }


            Board.getInstance().millProtocol(tile);

        } else if (ActionManager.getInstance().isPieceFilled() && !tile.isEmpty() ){
            JOptionPane.showMessageDialog(null, "That tile is occupied.");
        } else if (!ActionManager.getInstance().isPieceFilled() && PieceManager.getInstance().getPiecesLeftInHand(PlayerManager.getInstance().getCurrentPlayer().getPieceColor()) > 0){
            JOptionPane.showMessageDialog(null, "That's not your piece.");
        }

    }
}
