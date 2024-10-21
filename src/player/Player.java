package player;

import behaviour.*;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Player class represents player in the game.
 * contains color for the piece color and behaviour list
 *
 * @author billgultiano, jordannathanael, yuchenwang
 * @version JDK 19
 */
public class Player implements Serializable {
    /**
     * Piece color
     */
    private Color color;
    /**
     * behaviour that will be used to determine the available action for current turn
     */
    private List<Behaviour> behaviourList = new ArrayList<>();

    /**
     * Initialization
     * @param color the piece color
     */
    public Player(Color color) {
        this.color = color;

        behaviourList.add(new PlaceBehaviour());
        behaviourList.add(new MoveBehaviour());
        behaviourList.add(new FlyBehaviour());
    }

    /**
     * Retrieve player behaviours
     * @return list of behaviours
     */
    public List<Behaviour> getBehaviourList() { return behaviourList; }

    /**
     * get player's color
     * @return piece color of the player
     */
    public Color getPieceColor() { return color;}
}

