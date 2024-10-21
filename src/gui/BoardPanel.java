package gui;

import board.Board;
import board.Tile;

import javax.swing.*;
import java.awt.*;

/**
 * Board Panel class represents the GUI panel
 *
 * @author billgultiano, jordannathanael, yuchenwang
 * @version JDK 19
 */
public class BoardPanel extends JPanel {
    /**
     * Board 2D array of Tile
     */
    private Board board;

    /**
     * Initialization
     */
    public BoardPanel(){
        board = Board.getInstance();
    }

    /**
     * set the tile coordinate on the panel
     */
    private void setTileCoordinate(){
        int startX = 50;
        int startY = 5;

        //check if panel is bigger than base width
        if (getWidth() > 700){
            //align components to the middle
            startX = (getWidth()/2 - 350) + 50;
        }

        int gap = 90;
        for (int i = 0; i < 7; i++){
            for(int j = 0; j < 7; j++){
                if (board.isValidPosition(i,j)) {
                    int positionX = startX + i*gap;
                    int positionY = startY + j*gap;
                    board.getTile(i, j).setCoordinate(positionX, positionY);
                }
            }
        }
    }

    /**
     * Set the line for the board itself
     * @param g inbuilt graphics
     */
    private void setBoardLine(Graphics g){
        for (int i = 0; i < 7; i++){
            for(int j = 0; j < 7; j++){
                if (board.getTile(i, j).isValid()){
                    add(board.getTile(i, j));
                    for (Tile neighbour : board.getTile(i, j).getNeighbours()){
                        int posX = neighbour.getX();
                        int posY = neighbour.getY();

                        g.drawLine(board.getTile(i, j).getX() + 25, board.getTile(i, j).getY() + 25, posX + 25, posY + 25);
                    }
                }
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        this.setTileCoordinate();
        this.setBoardLine(g);

    }
}
