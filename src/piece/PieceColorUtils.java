package piece;

import java.awt.*;

/**
 * translate the Color to string instead of getting RBG from original .toString()
 *
 * @author jordannathanael
 * @version JDK19
 */
public class PieceColorUtils {
    /**
     * Retrieve the string of the piece
     * @param color colour that want to be translated into string
     * @return string of piece color
     */
    public static String colorToString(Color color){
        if (color.toString().equals(Color.BLACK.toString())){
            return "Black";
        } else {
            return "White";
        }
    }
}
