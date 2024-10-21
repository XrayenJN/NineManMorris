package gui;

import piece.Piece;
import piece.PieceManager;
import player.Player;
import player.PlayerManager;

import javax.swing.*;
import java.awt.*;

/**
 * Player panel to contains all the pieces on their hand
 */
public class PlayerPanel extends JPanel {
    /**
     * Background color of the panel
     */
    private Color backgroundColor;
    /**
     * Player piece color
     */
    private Color mainColor;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(backgroundColor);
        g.setColor(mainColor);

    }

    /**
     * Initialization
     * @param playerColor piece color of the player
     */
    public PlayerPanel(Color playerColor) {
        if (playerColor.toString().equals(Color.BLACK.toString())){
            backgroundColor = Color.WHITE;
//            player = PlayerManager.getInstance().getPlayer1();
        } else {
            backgroundColor = Color.BLACK;
//            player = PlayerManager.getInstance().getPlayer2();
        }
        this.mainColor = playerColor;
    }

    /**
     * Update the number of piece on the board
     */
    public void updatePieces(){
        removeAll();
        if (mainColor != null){
            for (int i = 0; i < PieceManager.getInstance().getPiecesLeftInHand(mainColor); i++){
                add(new Piece(mainColor));
            }
        }
        revalidate();
        repaint();
    }
}
