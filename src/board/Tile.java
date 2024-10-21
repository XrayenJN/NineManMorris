package board;

import AdvanceRequirement.UndoManager;
import action.Action;
import action.ActionFactory;
import piece.Piece;
import action.ActionManager;
import gui.Coordinate;

import javax.swing.*;
import java.awt.*;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.Serializable;

/**
 * Tile class enherits the JButton GUI
 *
 * @author billgultiano, jordannathanael, yuchenwang
 * @version JDK19
 */
public class Tile extends JButton implements Serializable, MouseListener {
    /**
     * Static value for empty tile radius
     */
    private final int EMPTY_TILE_RADIUS = 50;
    /**
     * Static value for empty tile colour
     */
    private final Color EMPTY_TILE_COLOR = Color.red;
    /**
     * Position of the tile corresponding to the board which is 2D tile
     */
    private Position position;
    /**
     * Coordinate of the tile corresponding to the frame GUI
     */
    private Coordinate coordinate;
    /**
     * Piece that this tile hold
     */
    private Piece piece;
    /**
     * if this tile is valid which means it can be placed by a piece
     */
    private boolean isValid;
    /**
     * Neighbour of this tile
     */
    private Tile[] neighbours;

    /**
     * Initialization using row and col of the tile corresponding to 2D Tile
     * @param row row in the table
     * @param col col in the table
     */
    public Tile(int row, int col){
        position = new Position(row, col);
        piece = null;
        addMouseListener(this);
        setPreferredSize(new Dimension(EMPTY_TILE_RADIUS, EMPTY_TILE_RADIUS));
    }

    /**
     * Initialization using existed tile
     * @param tile existed tile
     */
    public Tile(Tile tile){
        position = new Position(tile.getX(), tile.getY());
        if (tile.getPiece() != null){
            piece = new Piece(tile.getPiece().getPieceColor());
        } else {
            piece = null;
        }

        isValid = tile.isValid();

    }


    @Override
    public void mouseClicked(MouseEvent e) {
        Action action = ActionFactory.createAction(ActionManager.getInstance().getStatus());

        if (action == null){
            JOptionPane.showMessageDialog(this.getParent(), "You Can't do that.");
        } else {
            UndoManager.getInstance().save();
            action.doAction(this);

        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    /**
     * Get the neighbours of this tile
     * @return list of neighbours
     */
    public Tile[] getNeighbours(){
        return neighbours;
    }

    /**
     * Set up the neighbours
     * @param neighbours list of neighbours
     */
    public void setNeighbours(Tile[] neighbours){
        this.neighbours = neighbours;
    }

    /**
     * Set the coordinate
     * @param x coordinate of x
     * @param y coordinate of y
     */
    public void setCoordinate(int x, int y){
        this.coordinate = new Coordinate(x,y);
    }

    /**
     * Get this tile position
     * @return this tile posiiton
     */
    public Position getPosition() {
        return position;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        setBounds(coordinate.getX(), coordinate.getY(), EMPTY_TILE_RADIUS, EMPTY_TILE_RADIUS);

        if (piece != null){
            g2d.setColor(piece.getPieceColor());
        } else {
            g2d.setColor(EMPTY_TILE_COLOR);
        }
        g2d.fillOval(0,0, EMPTY_TILE_RADIUS, EMPTY_TILE_RADIUS);
        setBorder(null);
    }

    /**
     * Get this tile piece
     * @return piece on this tile
     */
    public Piece getPiece() {
        return piece;
    }

    /**
     * set this tile piece
     * @param piece piece that want to be placed in this tile
     */
    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    /**
     * check if tile doesn't hold any piece or not
     * @return true if it is empty
     */
    public boolean isEmpty() {
        return piece == null;
    }

    /**
     * set this tile if it is a valid tile so that it can be placed by a piece
     * @param valid true if it is valid
     */
    public void setValid(boolean valid){
        isValid = valid;
    }

    /**
     * check if this tile is valid
     * @return true if it is valid
     */
    public boolean isValid(){
        return isValid;
    }
}
