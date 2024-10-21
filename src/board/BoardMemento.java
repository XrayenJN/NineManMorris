package board;

import undoFeatureInterface.BoardMementoInterface;

import java.io.Serializable;

/**
 * BoardMemento class to saved the board state
 * @author billgultiano
 * @version JDK19
 *
 * formatted by jordannathanael
 */
public class BoardMemento implements Serializable, BoardMementoInterface {
    /**
     * 2D board represents this game board
     */
    private Tile[][] savedBoard;

    /**
     * Initialization
     * @param board board 2D array of Tile
     */
    public BoardMemento(Tile[][] board){
        int rowLen = board.length;
        int colLen = board[0].length;
        savedBoard = new Tile[rowLen][colLen];
        for (int i = 0; i < rowLen; i++){
            for (int j = 0; j < colLen; j++){
                Tile tile = new Tile(board[i][j]);
                savedBoard[i][j] = tile;

            }
        }
        Board.getInstance().setupTiles(savedBoard);
    }

    @Override
    public Tile[][] getBoard(){
        return savedBoard;
    }
}
