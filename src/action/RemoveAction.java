package action;

import AdvanceRequirement.UndoManager;
import board.Board;
import board.Tile;
import piece.PieceManager;
import player.PlayerManager;

import javax.swing.*;

/**
 * Remove Action
 * players can choose opponent's piece to be deleted from board
 * two situations:
 * 1) it can't be deleted if it is part of a mill
 * 2) if there is no other available pieces left (all opponent pieces are part of a mill), one of those can be deleted
 *
 * @author billgultiano, jordannathanael, yuchenwang
 * @version JDK 19
 */
public class RemoveAction implements Action{
    /**
     * Remove protocol
     * @param tile tile that has opponent's piece that want to be removed
     */
    private void removeAction(Tile tile){
        // set the tile piece into empty
        tile.setPiece(null);
        PieceManager.getInstance().setPiecesLeft(PlayerManager.getInstance().getOpponent());

        // take care the action
        ActionManager.getInstance().setStatus(null);
        ActionManager.getInstance().setInMiddleOfAction(false);

        // check if there is a winner, otherwise change turn
        PlayerManager.getInstance().checkWinner();
        PlayerManager.getInstance().changeTurn();
    }

    @Override
    public void doAction(Tile tile) {
        // Delete piece
        if (tile.getPiece() != null && PlayerManager.getInstance().getCurrentPlayer().getPieceColor().toString().equals(tile.getPiece().getPieceColor().toString())){
            JOptionPane.showMessageDialog(null, "You can't remove your own piece.");
        }

        // check whether the deleted piece is the opponent piece or not
        if (tile.getPiece() != null && PlayerManager.getInstance().getOpponent().getPieceColor().toString().equals(tile.getPiece().getPieceColor().toString())) {
//            UndoManager.getInstance().save();
            // check if all the targets are mill, you can remove it, otherwise you can't
            if (Board.getInstance().areAllPlayersPiecesPartOfMill(PlayerManager.getInstance().getOpponent().getPieceColor())){
                // if it is, user can still delete one of them
                removeAction(tile);
            }
            // check if the target is part of a mill or not
            else if (!Board.getInstance().isTilePartOfMill(tile)){
                removeAction(tile);
            }
            else {
                JOptionPane.showMessageDialog(null, "You cannot remove from a mill.");
            }


        }
    }
}
