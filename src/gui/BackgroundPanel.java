package gui;
import javax.swing.*;
import java.awt.*;

/**
 * Background Panel class represents main panel when game starts
 *
 * @author billgultiano, jordannathanael, yuchenwang
 * @version JDK 19
 */
class BackgroundPanel extends JPanel {
    /**
     * Image of the background
     */
    private Image backgroundImage;

    /**
     * Initialization
     * @param imagePath path of the image
     */
    public BackgroundPanel(String imagePath) {
        ImageIcon imageIcon = new ImageIcon(getClass().getResource(imagePath));
        backgroundImage = imageIcon.getImage();
        if (backgroundImage == null) {
            System.err.println("Error loading image: " + imagePath);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}