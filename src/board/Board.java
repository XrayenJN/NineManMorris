package board;

import action.ActionManager;
import action.ActionStatus;
import piece.Piece;
import player.PlayerManager;
import undoFeatureInterface.BoardMementoInterface;
import undoFeatureInterface.Undoable;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;

/**
 * Board class represents the Nine man morris board
 *
 * @author billgultiano, jordannathanael, yuchenwang
 * @version JDK 19
 */
public class Board implements Serializable, Undoable {
    /**
     * Board tiles
     */
    private Tile[][] board;
    /**
     * this class instance
     */
    private static Board instance = null;
    /**
     * Caretaker object for undo feature
     */
    private BoardCareTaker careTaker;

    /**
     * Initialization
     */
    public Board() {
        board = new Tile[7][7];
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                board[i][j] = new Tile(i, j);
            }
        }

        setupTiles(this.board);
        careTaker = new BoardCareTaker();
    }

    /**
     * Set this instance with existing object
     * @param board existed board instance
     */
    public void setCurrentBoard(Board board){
        instance = board;
    }

    /**
     * Set up available tiles for the board
     * @param board 2D of tiles
     */
    void setupTiles(Tile[][] board){
        // Set initial layout
        board[0][0].setValid(true);
        board[0][0].setNeighbours(new Tile[]{board[0][3], board[3][0]});

        board[0][3].setValid(true);
        board[0][3].setNeighbours(new Tile[]{board[0][0], board[1][3], board[0][6]});

        board[0][6].setValid(true);
        board[0][6].setNeighbours(new Tile[]{board[0][3], board[3][6]});

        board[1][1].setValid(true);
        board[1][1].setNeighbours(new Tile[]{board[1][3], board[3][1]});

        board[1][3].setValid(true);
        board[1][3].setNeighbours(new Tile[]{board[0][3], board[1][1], board[1][5], board[2][3]});

        board[1][5].setValid(true);
        board[1][5].setNeighbours(new Tile[]{board[1][3], board[3][5]});

        board[2][2].setValid(true);
        board[2][2].setNeighbours(new Tile[]{board[2][3], board[3][2]});

        board[2][3].setValid(true);
        board[2][3].setNeighbours(new Tile[]{board[1][3], board[2][2], board[2][4]});

        board[2][4].setValid(true);
        board[2][4].setNeighbours(new Tile[]{board[2][3], board[3][4]});

        board[3][0].setValid(true);
        board[3][0].setNeighbours(new Tile[]{board[0][0], board[3][1], board[6][0]});

        board[3][1].setValid(true);
        board[3][1].setNeighbours(new Tile[]{board[3][0], board[1][1], board[3][2],board[5][1]});

        board[3][2].setValid(true);
        board[3][2].setNeighbours(new Tile[]{board[2][2], board[3][1], board[4][2]});

        board[3][4].setValid(true);
        board[3][4].setNeighbours(new Tile[]{board[2][4], board[3][5], board[4][4]});

        board[3][5].setValid(true);
        board[3][5].setNeighbours(new Tile[]{board[3][4], board[3][6], board[1][5], board[5][5]});

        board[3][6].setValid(true);
        board[3][6].setNeighbours(new Tile[]{board[0][6], board[3][5], board[6][6]});

        board[4][2].setValid(true);
        board[4][2].setNeighbours(new Tile[]{board[3][2], board[4][3]});

        board[4][3].setValid(true);
        board[4][3].setNeighbours(new Tile[]{board[4][2], board[4][4], board[5][3]});

        board[4][4].setValid(true);
        board[4][4].setNeighbours(new Tile[]{board[3][4], board[4][3]});

        board[5][1].setValid(true);
        board[5][1].setNeighbours(new Tile[]{board[5][3], board[3][1]});

        board[5][3].setValid(true);
        board[5][3].setNeighbours(new Tile[]{board[4][3], board[5][1], board[5][5], board[6][3]});

        board[5][5].setValid(true);
        board[5][5].setNeighbours(new Tile[]{board[5][3], board[3][5]});

        board[6][0].setValid(true);
        board[6][0].setNeighbours(new Tile[]{board[3][0], board[6][3]});

        board[6][3].setValid(true);
        board[6][3].setNeighbours(new Tile[]{board[5][3], board[6][0], board[6][6]});

        board[6][6].setValid(true);
        board[6][6].setNeighbours(new Tile[]{board[6][3], board[3][6]});
    }

    /**
     * Get this class instance
     * @return this class instance
     */
    public static Board getInstance() {
        if (instance==null){
            instance = new Board();

        }
        return instance;
    }

    /**
     * Check if the tile is valid (can be placed by piece) or not
     * @param row row of the tile
     * @param col col of the tile
     * @return true if it is valid
     */
    public boolean isValidPosition(int row, int col) { return board[row][col].isValid(); }

    /**
     * Check if there is a mill horizontally
     * @param row piece's row
     * @param col piece's col
     * @param playerColor player piece color
     * @return true if it is found
     */
    private boolean checkHorizontalMill(int row, int col, Color playerColor){
        //check horizontal mill
        if(row !=3) {
            int count =0;
            int i;
            for (i = 0; i < 7; i++) {

                Piece piece = board[row][i].getPiece();
                if (piece != null){
                    if (piece.getPieceColor().toString().equals(playerColor.toString())) {
                        count++;
                    }
                    if (count == 3){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Check if there is a mill vertically
     * @param row piece's row
     * @param col piece's col
     * @param playerColor player piece color
     * @return true if it is found
     */
    private boolean checkVerticalMill(int row, int col, Color playerColor){
        // Check vertical mill
        if(col !=3) {
            int count =0;
            int i;
            for (i = 0; i < 7 ; i++) {
                Piece piece = board[i][col].getPiece();
                if (piece != null){
                    if (piece.getPieceColor().toString().equals(playerColor.toString())) {
                        count++;
                    }
                }
                if (count == 3){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Check if there is a mill in the center horizontally
     * @param row piece's row
     * @param col piece's col
     * @param playerColor player piece color
     * @return true if it is found
     */
    private boolean checkCenterMillFromRow(int row, int col, Color playerColor){
        // Check row 3
        if(row ==3) {
            int count =0;
            int i;
            if(col<3){
                for (i = 0; i < 3; i++) {
                    Piece piece = board[row][i].getPiece();
                    if (piece != null){
                        if (board[row][i].getPiece().getPieceColor().toString().equals(playerColor.toString())) {
                            count ++;
                        }
                        if (count == 3){
                            return true;
                        }
                    }
                }
            }else if(col>3){
                for (i = 4; i < 7; i++) {
                    Piece piece = board[row][i].getPiece();
                    if (piece != null){
                        if (board[row][i].getPiece().getPieceColor().toString().equals(playerColor.toString())) {
                            count ++;
                        }
                        if (count == 3){
                            return true;
                        }
                    }

                }
            }
        }
        return false;
    }

    /**
     * Check if there is a mill from center vertically
     * @param row piece's row
     * @param col piece's col
     * @param playerColor player piece color
     * @return true if it is found
     */
    private boolean checkCenterMillFromCol(int row, int col, Color playerColor){
        // Check column 3
        if(col ==3) {
            int count =0;
            int i;
            if(row<3){
                for (i = 0; i < 3; i++) {

                    Piece piece = board[i][col].getPiece();

                    if (piece != null){
                        if (board[i][col].getPiece().getPieceColor().toString().equals(playerColor.toString())) {
                            count ++;
                        }
                        if (count == 3){
                            return true;
                        }
                    }

                }
            }else if(row>3){
                for (i = 4; i < 7; i++) {

                    Piece piece = board[i][col].getPiece();
                    if (piece != null){
                        if (board[i][col].getPiece().getPieceColor().toString().equals(playerColor.toString())) {
                            count ++;
                        }
                        if (count == 3){
                            return true;
                        }
                    }

                }
            }
        }
        return false;
    }

    /**
     * Check if the tile is part of the mill or not
     * y checking horizontally, vertically, etc
     * @param tile the tile that we want to check
     * @return true if it is part of mill
     */
    public boolean isTilePartOfMill(Tile tile){
        int row = tile.getPosition().getRow();
        int col = tile.getPosition().getCol();
        Color playerColor = tile.getPiece().getPieceColor();

        boolean horizontalMillFound = checkHorizontalMill(row, col, playerColor);
        boolean verticalMillFound = checkVerticalMill(row, col, playerColor);
        boolean centerMillFoundFromCol = checkCenterMillFromCol(row, col, playerColor);
        boolean centerMillFoundFromRow = checkCenterMillFromRow(row, col, playerColor);

        if (horizontalMillFound || verticalMillFound || centerMillFoundFromCol || centerMillFoundFromRow) {
            return true;
        }
        return false;
    }

    /**
     * during a checking of a mill, follow this protocol
     * @param tile tile that want to be checked if it is part of a mill
     */
    public void millProtocol(Tile tile) {
        // check whether it forms a mill or not
        boolean millCheck = this.isTilePartOfMill(tile);

        // if it is a part of mill, notify player that they create a mill, proceed with next action (Remove)
        if (millCheck){
            ActionManager.getInstance().setInMiddleOfAction(true);
            JOptionPane.showMessageDialog(null, "You just created a mill, Feel free to delete the opponent piece.");
            ActionManager.getInstance().setStatus(ActionStatus.REMOVE);

        } else {
            PlayerManager.getInstance().changeTurn();
        }
    }

    /**
     * Check if player's piece are all part of a mill
     * @param pieceColor color that want to be checked
     * @return if it is true, then it can be removed, otherwise, those tile that part of the mill can't be removed.
     */
    public boolean areAllPlayersPiecesPartOfMill(Color pieceColor){
        for (int row = 0; row < board.length; row++){
            for (int col = 0; col < board.length; col++){
                if (board[row][col].isValid() && !board[row][col].isEmpty()){
                    if (board[row][col].getPiece().getPieceColor().toString().equals(pieceColor.toString())){
                        if (!isTilePartOfMill(board[row][col])){
                            return false;
                        }
                    }

                }
            }
        }
        return true;
    }

    /**
     * retrieve the tile by having row and col number
     * @param row row of the tile
     * @param col col of the tile
     * @return return the tile from the input row-col
     */
    public Tile getTile(int row, int col){ return board[row][col]; }

    /**
     * Retrieve board
     * @return Tile[][]
     */
    public Tile[][] getBoard() { return board; }

    @Override
    public void save(){
        careTaker.save();
    }

    @Override
    public void undo(){
        BoardMementoInterface lastMemento = careTaker.getLast();

        if (lastMemento != null){
            Tile[][] lastBoard = lastMemento.getBoard();
            int rowLen = board.length;
            int colLen = board[0].length;
            for (int i = 0; i < rowLen; i++){
                for (int j = 0; j < colLen; j++){
                    board[i][j].setPiece(lastBoard[i][j].getPiece());
                }
            }
        }
    }
}
