package action;

import AdvanceRequirement.UndoManager;
import board.Board;
import board.Tile;
import player.PlayerManager;

import javax.swing.*;

/**
 * Fly Action
 * players can choose a piece from the board and move it into any available tiles on the board
 *
 * @author billgultiano, jordannathanael, yuchenwang
 * @version JDK 19
 */
public class FlyAction implements Action {

    @Override
    public void doAction(Tile tile) {
        // Fly Piece
        if (!ActionManager.getInstance().isPieceFilled() && !tile.isEmpty() && !PlayerManager.getInstance().getCurrentPlayer().getPieceColor().toString().equals(tile.getPiece().getPieceColor().toString())){
            JOptionPane.showMessageDialog(null, "That's not your piece.");
        }

        // (Set the old position)
        if (!ActionManager.getInstance().isInMiddleOfAction() && tile.getPiece() != null && PlayerManager.getInstance().getCurrentPlayer().getPieceColor().toString().equals(tile.getPiece().getPieceColor().toString())){

            // Set the old position
            if (!tile.isEmpty() && !ActionManager.getInstance().isPieceFilled()) {
                ActionManager.getInstance().setPiece(tile.getPiece());
                ActionManager.getInstance().setTile(tile);
                tile.setPiece(null);
                ActionManager.getInstance().setInMiddleOfAction(true);
            }
        }
        // Move piece (Set the new position)
        else if (ActionManager.getInstance().isInMiddleOfAction() && tile.isEmpty() && ActionManager.getInstance().isPieceFilled() && ActionManager.getInstance().isTileFilled()) {

            // if the new position is the old position, it is not valid fly action
            if (tile == ActionManager.getInstance().getTile()){
                JOptionPane.showMessageDialog(null, "Choose another tile.");
            } else {
                tile.setPiece(ActionManager.getInstance().getPiece());

                ActionManager.getInstance().setTile(null);
                ActionManager.getInstance().setPiece(null);
                if (Board.getInstance().isTilePartOfMill(tile)){
                    ActionManager.getInstance().setInMiddleOfAction(true);
                } else {
                    ActionManager.getInstance().setInMiddleOfAction(false);
                }

                Board.getInstance().millProtocol(tile);
            }
        }

        else if (ActionManager.getInstance().isPieceFilled() && !tile.isEmpty()) {
            JOptionPane.showMessageDialog(null, "That tile is occupied.");
        }
    }
}
