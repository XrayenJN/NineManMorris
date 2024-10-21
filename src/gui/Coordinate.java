package gui;

import java.io.Serializable;

/**
 * Coordinate - Data Class
 * Represent the x and y on the panel (GUI)
 *
 * @author billgultiano, jordannathanael, yuchenwang
 * @version JDK 19
 */
public class Coordinate implements Serializable {
    /**
     * X coordinate on the panel
     */
    private int x;
    /**
     * Y coordinate on the panel
     */
    private int y;

    /**
     * Initialization
     * @param x x coordinate
     * @param y y coordinate
     */
    public Coordinate(int x, int y){
        this.x = x;
        this.y = y;
    }

    /**
     * Retrieve the x coordinate
     * @return x value
     */
    public int getX(){ return x; }

    /**
     * Retrieve the y coordinate
     * @return y value
     */
    public int getY(){ return y; }
}
