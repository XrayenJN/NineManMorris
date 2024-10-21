package action;

import AdvanceRequirement.UndoManager;
import board.Board;
import board.Tile;
import player.PlayerManager;

import javax.swing.*;

/**
 * Move Action
 * players can choose a piece and move it into its adjacent tiles on the board
 *
 * @author billgultiano, jordannathanael, yuchenwang
 * @version JDK 19
 */
public class MoveAction implements Action{

    @Override
    public void doAction(Tile tile) {
        // Move piece
//        System.out.println(!ActionManager.getInstance().isPieceFilled());
//        System.out.println(!tile.isEmpty());
//        System.out.println(PlayerManager.getInstance().getCurrentPlayer().getPieceColor() != tile.getPiece().getPieceColor());


        if (!ActionManager.getInstance().isPieceFilled() && !tile.isEmpty() && !PlayerManager.getInstance().getCurrentPlayer().getPieceColor().toString().equals(tile.getPiece().getPieceColor().toString())){
            JOptionPane.showMessageDialog(null, "That's not your piece.");
        }

        // (Set the old position)
        if (!ActionManager.getInstance().isInMiddleOfAction() && PlayerManager.getInstance().getCurrentPlayer().getPieceColor().toString().equals(tile.getPiece().getPieceColor().toString())){

            // check whether there is an available neighbour or not,
            // if it is not, disable to be clicked when user want to move piece
            boolean neighbourIsFree = false;
            for (Tile neighbourTile : tile.getNeighbours()){
                if (neighbourTile.isEmpty()){
                    neighbourIsFree = true;
                }
            }

            if (neighbourIsFree){

                // Set the old position
                if (!tile.isEmpty() && !ActionManager.getInstance().isPieceFilled()) {
                    ActionManager.getInstance().setPiece(tile.getPiece());
                    ActionManager.getInstance().setTile(tile);
                    tile.setPiece(null);
                    ActionManager.getInstance().setInMiddleOfAction(true);
                }
            }
        }

        // Move piece (Set the new position)
        else if (ActionManager.getInstance().isInMiddleOfAction() && tile.isEmpty() && ActionManager.getInstance().isPieceFilled() && ActionManager.getInstance().isTileFilled()){
            // check if the clicked tile is the old position neighbour or not
            boolean thisIsTheNeighbour = false;

            for (Tile neighbourTile : ActionManager.getInstance().getTile().getNeighbours()){
                if (neighbourTile.equals(tile)) {
                    thisIsTheNeighbour = true;
                }
            }

            if (thisIsTheNeighbour){
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
