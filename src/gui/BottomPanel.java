package gui;

import javax.swing.*;
import java.awt.*;

/**
 * BottomPanel to show the current turn situation
 * (whose turn and what's the available action)
 *
 * @author billgultiano, jordannathanael, yuchenwang
 * @version JDK 19
 */
public class BottomPanel extends JPanel {
    /**
     * Label for the panel
     */
    private static JLabel label = new JLabel();

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(Color.LIGHT_GRAY);
        g.setColor(Color.BLACK);
        add(label);
    }

    /**
     * set the label of this panel
     * @param prompt new prompt for the label
     */
    public static void setLabel(String prompt){
        label.setText(prompt);
        label.setFont(new Font("Serif", Font.BOLD, 24));
    }
}
