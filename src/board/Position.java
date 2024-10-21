package board;

import java.io.Serializable;

/**
 * Board class represents the Nine man morris board
 *
 * @author billgultiano, jordannathanael, yuchenwang
 * @version JDK 19
 */
public class Position implements Serializable {
    /**
     * row position on the table
     */
    private int row;
    /**
     * col position on the table
     */
    private int col;

    /**
     * Initialization
     * @param row row in the table
     * @param col col in the table
     */
    public Position(int row, int col){
        this.row = row;
        this.col = col;
    }

    /**
     * Retrieve the row value
     * @return row
     */
    public int getRow(){ return row; }

    /**
     * Retrieve the col value
     * @return col
     */
    public int getCol(){ return col; }
}
