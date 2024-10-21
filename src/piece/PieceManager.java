package piece;

import player.Player;
import player.PlayerManager;
import undoFeatureInterface.Undoable;
import java.awt.*;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Piece Manager class, maintain all pieces that players have, on hand or even on board
 *
 * @author billgultiano, jordannathanael, yuchenwang
 * @version JDK 19
 */
public class PieceManager implements Serializable, Undoable {
    /**
     * this class instance
     */
    private static PieceManager instance = null;
    /**
     * hashmap for player with their own pieces
     */
    private  Map<String, Piece[]> playerPieces;
    /**
     * hashmap storing player with their pieces number on their hand
     */
    private  Map<String, Integer> piecesInHand;
    /**
     * hashmap storing player with their pieces number on the game
     */
    private  Map<String, Integer> piecesInGame;
    /**
     * caretaker class for undo feature
     */
    private PieceCareTaker careTaker;

    /**
     * Initialization
     */
    private PieceManager(){
        playerPieces = new HashMap<>();
        piecesInHand = new HashMap<>();
        piecesInGame = new HashMap<>();
        careTaker = new PieceCareTaker();
    }

    /**
     * get instance of this class
     * @return instance
     */
    public static PieceManager getInstance(){
        if (instance==null){
            instance = new PieceManager();
        }
        return instance;
    }

    /**
     * set the instance with the existed object
     * @param pieceManager existed piece manager object
     */
    public void setCurrentPieceManager(PieceManager pieceManager){ instance = pieceManager;}

    /**
     * Initialize all the piece that the player should have in the beginning of the game
     * @param player1 left-side player
     * @param player2 right-side player
     * @param numOfPieces1 total piece that left-side player has
     * @param numOfPieces2 total piece that right-side player has
     */
    public void initializePieces(Player player1, Player player2, Integer numOfPieces1, Integer numOfPieces2){
        //stores actual pieces
        playerPieces.put(player1.getPieceColor().toString(), new Piece[numOfPieces1]);
        playerPieces.put(player2.getPieceColor().toString(), new Piece[numOfPieces2]);
        //records how many pieces in hand for each player
        piecesInHand.put(player1.getPieceColor().toString(), numOfPieces1);
        piecesInHand.put(player2.getPieceColor().toString(), numOfPieces2);

        //records how many pieces in the game for each player
        piecesInGame.put(player1.getPieceColor().toString(), numOfPieces1);
        piecesInGame.put(player2.getPieceColor().toString(), numOfPieces2);

        //instantiate each piece
        Piece[] player1Pieces = playerPieces.get(player1.getPieceColor().toString());
        for (int i = 0; i < player1Pieces.length; i++){
            player1Pieces[i] = new Piece(player1.getPieceColor());
        }
        Piece[] player2Pieces = playerPieces.get(player2.getPieceColor().toString());
        for (int i = 0; i < player2Pieces.length; i++){
            player2Pieces[i] = new Piece(player2.getPieceColor());
        }
    }

    /**
     * get how many pieces left the player has
     * @param player find this player total pieces
     * @return how many pieces this player has left
     */
    public int getPiecesLeft(Player player) {
        return piecesInGame.get(player.getPieceColor().toString());
    }

    /**
     * Decrement the player pieces on the game
     * @param player player that has lost one of their piece.
     */
    public void setPiecesLeft(Player player) {
        piecesInGame.put(player.getPieceColor().toString(), piecesInGame.get(player.getPieceColor().toString())-1);
    }

    /**
     * Retrieve amount of piece with a corresponding color
     * @param color color that we want to find
     * @return amount of pieces left on the hand
     */
    public int getPiecesLeftInHand(Color color) {
        Integer piecesLeft = piecesInHand.get(color.toString());
        return (piecesLeft != null) ? piecesLeft : 0;
    }

    /**
     * Decrement the player pieces on the hand
     * @param player player that has lost one of their piece from their hand
     */
    public void setPiecesLeftInHand(Player player) {
        piecesInHand.put(player.getPieceColor().toString(),piecesInHand.get(player.getPieceColor().toString())-1);
        System.out.println("Setting piece from hand: " + piecesInHand.get(player.getPieceColor().toString()));
        System.out.println("Number of pieces from hand: " + PieceManager.getInstance().getPiecesLeftInHand(PlayerManager.getInstance().getCurrentPlayer().getPieceColor()));
        System.out.println("Number of pieces from hand: " + piecesInHand.get(player.getPieceColor().toString()));
    }

    /**
     * Retrieve the player and their pieces
     * @return hashmap player and their pieces
     */
    public Map<String, Piece[]> getPlayerPieces() { return playerPieces; }

    /**
     * Retrieve the player and their number of pieces on their hand
     * @return hashmap player and their number of pieces
     */
    public Map<String, Integer> getPiecesInHand() { return piecesInHand; }

    /**
     * Retrieve the player and their number of pieces on the game
     * @return hashmap player and their number of pieces
     */
    public Map<String, Integer> getPiecesInGame() { return piecesInGame; }

    @Override
    public void save(){
        careTaker.save();
    }

    @Override
    public void undo(){
        PieceMemento lastMemento = careTaker.getLast();

        if (lastMemento != null){
            for (Map.Entry<String, Piece[]> entry : lastMemento.getSavedPlayerPieces().entrySet()) {
                String color = entry.getKey();
                Piece[] piecesToCopy = entry.getValue();
                Piece[] pieces = new Piece[piecesToCopy.length];

                for (int i = 0; i < pieces.length; i++){
                    pieces[i] = new Piece(piecesToCopy[i].getPieceColor());
                }
                playerPieces.put(color, pieces);
            }
            for (Map.Entry<String, Integer> entry : lastMemento.getSavedPiecesInHand().entrySet()) {
                String color = entry.getKey();
                int value = entry.getValue();
                piecesInHand.put(color, value);
            }

            for (Map.Entry<String, Integer> entry : lastMemento.getSavedPiecesInGame().entrySet()) {
                String color = entry.getKey();
                int value = entry.getValue();
                piecesInGame.put(color, value);
            }
        }
    }
}
