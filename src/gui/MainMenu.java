package gui;
import AdvanceRequirement.Loading;


import board.Board;
import piece.PieceManager;
import player.Player;
import player.PlayerManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.Serializable;

/**
 * Main menu frame which is initiated in the beginning of the game execution
 */
public class MainMenu extends JFrame implements ActionListener , Serializable {
    /**
     * Static value for title of this game
     */
    private final String TITLE = "Nine Men's Morris Game";
    /**
     * screen width static value
     */
    private final int SCREEN_WIDTH = 612;
    /**
     * screen height static value
     */
    private final int SCREEN_HEIGHT = 408;
    /**
     * load button to load the existing saved games
     */
    private JButton loadButton;
    /**
     * play button to play the game
     */
    private JButton playButton;

    /**
     * Initialization
     * Set up the background image
     * Set up two available button to play the game / load the existeing saved game
     */
    public MainMenu() {
        setTitle(TITLE);
        setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //set up the background
        BackgroundPanel backgroundPanel = new BackgroundPanel("/gui/img.jpg");
        backgroundPanel.setLayout(new GridBagLayout());

        //create the play button
        playButton = new JButton("Play");
        playButton.addActionListener(this);
        playButton.setPreferredSize(new Dimension(100, 50));

        //create the load button
        loadButton = new JButton("Load");
        loadButton.addActionListener(this);
        loadButton.setPreferredSize(new Dimension(100, 50));

        //apply constraints
        GridBagConstraints buttonConstraints = new GridBagConstraints();
        buttonConstraints.gridx = 0;
        buttonConstraints.gridy = 0;
        buttonConstraints.weightx = 1.0;
        buttonConstraints.weighty = 1.0;
        buttonConstraints.anchor = GridBagConstraints.CENTER;

        GridBagConstraints loadButtonConstraints = new GridBagConstraints();
        loadButtonConstraints.gridx = 0;
        loadButtonConstraints.gridy = 0;
        loadButtonConstraints.weightx = 1.0;
        loadButtonConstraints.weighty = 1.0;
        loadButtonConstraints.anchor = GridBagConstraints.SOUTH;

        //create title panel
        JLabel titleLabel = new JLabel(TITLE);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);

        // Apply constraints for titleLabel
        GridBagConstraints titleLabelConstraints = new GridBagConstraints();
        titleLabelConstraints.gridx = 0;
        titleLabelConstraints.gridy = 0;
        titleLabelConstraints.weightx = 1.0;
        titleLabelConstraints.weighty = 0.0;
        titleLabelConstraints.anchor = GridBagConstraints.NORTH;
        backgroundPanel.add(titleLabel, titleLabelConstraints);


        //add the elements onto the background panel
        backgroundPanel.add(titleLabel, titleLabelConstraints);
        backgroundPanel.add(playButton, buttonConstraints);
        backgroundPanel.add(loadButton, loadButtonConstraints);
        add(backgroundPanel);
        setVisible(true);
        setResizable(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == playButton) {
            Frame.getInstance().setVisible(true);
            dispose();
        } else if (e.getSource() == loadButton) {
            this.filePicker();
            BottomPanel.setLabel(" ");
            dispose();
        }
    }

    /**
     * Initiate the file picker GUI (in-built) to choose the availble save file
     */
    private void filePicker(){
        JFileChooser fileChooser = new JFileChooser();

        // set to chose files only
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

        // set the directory
        String directory = "./savedFiles";

        File fileDirectory = new File(directory);
        fileChooser.setCurrentDirectory(fileDirectory);

        // Show the file picker dialog
        int option = fileChooser.showOpenDialog(this);

        if (option == JFileChooser.APPROVE_OPTION) {
            // Get the selected file
            File selectedFile = fileChooser.getSelectedFile();
            System.out.println("Selected File: " + selectedFile.getName());

            Loading.getInstance().loadGameFromFile(selectedFile.getName());
            Loading.getInstance().setLoadedGameFile(selectedFile.getName());
        }
    }
}
