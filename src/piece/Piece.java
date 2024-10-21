package piece;

import AdvanceRequirement.UndoManager;
import action.ActionStatus;
import player.Player;
import player.PlayerManager;
import action.ActionManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;
import java.io.Serializable;

/**
 * Piece class for the game, inherit from JButton
 *
 * @author billgultiano, jordannathanael, yuchenwang
 * @version JDK 19
 */
public class Piece extends JButton implements Serializable, MouseListener {
    /**
     * static variable for radius of the button
     */
    private final int FILLED_TILE_RADIUS = 50;
    /**
     * piece color
     */
    private Color playerColor;

    /**
     * Initialization
     * @param playerColor player's color
     */
    public Piece(Color playerColor){
        this.playerColor = playerColor;

        addMouseListener(this);
        setPreferredSize(new Dimension(FILLED_TILE_RADIUS, FILLED_TILE_RADIUS));
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (ActionManager.getInstance().getStatus() == ActionStatus.PLACE){

            // Place piece
            Integer currentPlayerPiecesLeftInHand = PieceManager.getInstance().getPiecesLeftInHand(PlayerManager.getInstance().getCurrentPlayer().getPieceColor());

            if (currentPlayerPiecesLeftInHand > 0 && PlayerManager.getInstance().getCurrentPlayer().getPieceColor().toString().equals(playerColor.toString())){
                UndoManager.getInstance().save();
                if (!ActionManager.getInstance().isPieceFilled() && this.getParent() != null){
                    ActionManager.getInstance().setPiece(this);

                    Container panel = this.getParent();

                    panel.remove(this);
                    panel.getParent().revalidate();
                    panel.getParent().repaint();
                    PieceManager.getInstance().setPiecesLeftInHand(PlayerManager.getInstance().getCurrentPlayer());
                    ActionManager.getInstance().setInMiddleOfAction(true);
                }
            }
            else {
                JOptionPane.showMessageDialog(null, "Sorry, you can't use that piece because it is not your piece.");
            }
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

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        Shape circle = new Ellipse2D.Float(0, 0, FILLED_TILE_RADIUS, FILLED_TILE_RADIUS);
        g2d.setColor(playerColor);
        g2d.fill(circle);
        setBorder(null);
    }

    /**
     * Retrieve the piece color
     * @return Color
     */
    public Color getPieceColor() { return this.playerColor; }
}
