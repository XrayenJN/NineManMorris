package piece;

import undoFeatureInterface.PieceMementoInterface;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * PieceMemento class to save the player pieces detail
 * act as Data Class
 *
 * @author billgultiano
 * @version JDK 19
 *
 * formatted by jordannathanael
 */
public class PieceMemento implements Serializable, PieceMementoInterface {
    /**
     * hashmap for player with their own pieces
     */
    private  Map<String, Piece[]> savedPlayerPieces;
    /**
     * hashmap storing player with their pieces number on their hand
     */
    private  Map<String, Integer> savedPiecesInHand;
    /**
     * hashmap storing player with their pieces number on the game
     */
    private  Map<String, Integer> savedPiecesInGame;

    /**
     * Initialization
     *
     * @param playerPieces hashmap for player with their own pieces
     * @param piecesInHand hashmap storing player with their pieces number on their hand
     * @param piecesInGame hashmap storing player with their pieces number on the game
     */
    public PieceMemento(Map<String, Piece[]> playerPieces, Map<String, Integer> piecesInHand, Map<String, Integer> piecesInGame){
        savedPlayerPieces = new HashMap<>();
        savedPiecesInHand = new HashMap<>();
        savedPiecesInGame = new HashMap<>();

        // Set the data with this class attribute
        for (Map.Entry<String, Piece[]> entry : playerPieces.entrySet()) {
            String color = entry.getKey();
            Piece[] piecesToCopy = entry.getValue();
            Piece[] pieces = new Piece[piecesToCopy.length];

            for (int i = 0; i < pieces.length; i++){
                pieces[i] = new Piece(piecesToCopy[i].getPieceColor());
            }
            savedPlayerPieces.put(color, pieces);
        }
        for (Map.Entry<String, Integer> entry : piecesInHand.entrySet()) {
            String color = entry.getKey();
            int value = entry.getValue();
            savedPiecesInHand.put(color, value);
        }

        for (Map.Entry<String, Integer> entry : piecesInGame.entrySet()) {
            String color = entry.getKey();
            int value = entry.getValue();
            savedPiecesInGame.put(color, value);
        }

    }
    @Override
    public Map<String, Piece[]> getSavedPlayerPieces() {
        return savedPlayerPieces;
    }

    @Override
    public Map<String, Integer> getSavedPiecesInHand() {
        return savedPiecesInHand;
    }

    @Override
    public Map<String, Integer> getSavedPiecesInGame() {
        return savedPiecesInGame;
    }
}
