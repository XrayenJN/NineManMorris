package piece;

import undoFeatureInterface.CaretakerInterface;

import java.io.Serializable;
import java.util.Map;
import java.util.Stack;

/**
 * PieceCareTaker to take care the pieces for each player in each turn
 *
 * @author billgultiano
 * @version JDK 19
 *
 * formatted by jordannathanael
 */
public class PieceCareTaker implements Serializable, CaretakerInterface {
    /**
     * Save all the piece stat each turn
     */
    private Stack<PieceMemento> history = new Stack<>();

    @Override
    public void save(){
        Map<String, Piece[]> playerPieces = PieceManager.getInstance().getPlayerPieces();
        Map<String, Integer> piecesInHand = PieceManager.getInstance().getPiecesInHand();
        Map<String, Integer> piecesInGame = PieceManager.getInstance().getPiecesInGame();
        history.push(new PieceMemento(playerPieces, piecesInHand, piecesInGame));
    }

    /**
     * Retrieve the previous state
     * @return Memento (depends on each memento class)
     */
    public PieceMemento getLast(){
        return !history.empty() ? history.pop() : null;
    }
}