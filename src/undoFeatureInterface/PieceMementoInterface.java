package undoFeatureInterface;

import piece.Piece;

import java.util.Map;

/**
 * Piece memento interface
 * to saved all the corresponding thing from pieceManager
 */
public interface PieceMementoInterface{
    /**
     * Applied for PieceMemento to save player pieces
     * @return hashmap of player and pieces
     */
    Map<String, Piece[]> getSavedPlayerPieces();

    /**
     * Applied for PieceMemento to get the player pieces on hand
     * @return hashmap player and pieces
     */
    Map<String, Integer> getSavedPiecesInHand();

    /**
     * Applied for PieceMemento to get the player pieces on game
     * @return hashmap player and pieces
     */
    Map<String, Integer> getSavedPiecesInGame();
}
