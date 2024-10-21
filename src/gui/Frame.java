package gui;


import AdvanceRequirement.Saving;
import AdvanceRequirement.UndoManager;
import action.ActionManager;
import board.Board;
import game.Game;
import piece.Piece;
import piece.PieceManager;
import player.Player;
import player.PlayerManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;

/**
 * Main frame for the running game.
 *
 * @author billgultiano, jordannathanael, yuchenwang
 * @version JDK 19
 */
public class Frame extends JFrame implements ActionListener, Serializable {
    /**
     * Static value for width
     */
    private final int SCREEN_WIDTH = 1000;
    /**
     * Static value for weight
     */
    private final int SCREEN_HEIGHT = 1000;
    /**
     * Save button to save the game state
     */
    private JButton saveButton;
    /**
     * Undo button to undo the game state
     */
    private JButton undoButton;
    /**
     * Left panel of this frame represents player 1 / left-side player
     */
    private PlayerPanel leftPanel;
    /**
     * Center panel of this frame represents the board game
     */
    private JPanel centerPanel;
    /**
     * Right panel of this frame represents player 2 / right-side player
     */
    private PlayerPanel rightPanel;
    /**
     * Bottom panel of this frame, represents the title of whose turn and what's the available current action
     */
    private JPanel bottomPanel;
    /**
     * This class instance
     */
    private static Frame instance;

    /**
     * Get this class instance
     * @return instance
     */
    public static Frame getInstance(){
        if (instance == null){
            instance = new Frame();
        }
        return instance;
    }

    /**
     * Private initialization
     */
    private Frame() {
        setTitle("Nine Men Morris Game");
        setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);


        leftPanel = new PlayerPanel(Color.BLACK);
        centerPanel = new BoardPanel();
        rightPanel = new PlayerPanel(Color.WHITE);
        bottomPanel = new BottomPanel();

        Container content = getContentPane();
        content.setLayout(new BorderLayout());
        content.add(centerPanel, BorderLayout.CENTER);
        content.add(leftPanel, BorderLayout.WEST);
        content.add(rightPanel, BorderLayout.EAST);
        content.add(bottomPanel, BorderLayout.NORTH);

        saveButton = new JButton("save");
        saveButton.addActionListener(this);
        saveButton.setPreferredSize(new Dimension(100, 50));
        GridBagConstraints saveButtonConstraints = new GridBagConstraints();
        saveButtonConstraints.gridx = 0;
        saveButtonConstraints.gridy = 0;
        saveButtonConstraints.weightx = 1.0;
        saveButtonConstraints.weighty = 1.0;
        saveButtonConstraints.anchor = GridBagConstraints.SOUTH;

        centerPanel.add(saveButton, saveButtonConstraints);

        undoButton = new JButton("undo");
        undoButton.addActionListener(this);
        undoButton.setPreferredSize(new Dimension(100, 50));
        GridBagConstraints undoButtonConstraints = new GridBagConstraints();
        undoButtonConstraints.gridx = 0;
        undoButtonConstraints.gridy = 0;
        undoButtonConstraints.weightx = 1.0;
        undoButtonConstraints.weighty = 1.0;
        undoButtonConstraints.anchor = GridBagConstraints.SOUTH;

        centerPanel.add(undoButton, undoButtonConstraints);


        leftPanel.setPreferredSize(new Dimension(150, SCREEN_HEIGHT));
        rightPanel.setPreferredSize(new Dimension(150, SCREEN_HEIGHT));
        bottomPanel.setPreferredSize(new Dimension(SCREEN_WIDTH, 50));
        leftPanel.updatePieces();
        rightPanel.updatePieces();

        setVisible(true);

        setMinimumSize(new Dimension(SCREEN_WIDTH+300, SCREEN_HEIGHT));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == saveButton) {
            Saving.getInstance().saveGameToFile(this);
            Game.getInstance().endGame();
        } else if (e.getSource() == undoButton){
            UndoManager.getInstance().undo();
            centerPanel.repaint();
            leftPanel.updatePieces();
            rightPanel.updatePieces();
            bottomPanel.repaint();
      }
    }
}