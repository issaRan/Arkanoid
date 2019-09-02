import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.Image;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @author ISSA 10/6/18.
 * a class that convert string to Image or color.
 */
public class ColorOrImage {
    private Image image;
    private Color color;

    /**
     * constructor in case we got an image.
     * @param image image.
     */
    public ColorOrImage(Image image) {
        this.image = image;
        this.color = null;
    }

    /**
     * contructor in case we got a color.
     * @param color color.
     */
    public ColorOrImage(Color color) {
        this.color = color;
        this.image = null;
    }

    /**
     * get function.
     * @return Image.
     */
    public Image getImage() {
        return this.image;
    }

    /**
     * checked if this a color.
     * @return color.
     */
    public boolean color() {
        if (this.color == null) {
            return false;
        }
        return true;
    }

    /**
     * checked if this an image.
     * @return image.
     */
    public boolean image() {
        if (this.image == null) {
            return false;
        }
        return true;
    }

    /**
     * get function for color.
     * @return color.
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * used to convert string to image.
     * @param s string path.
     * @return image.
     */
    public static Image parseImage(String s) {
        Image imageV1 = null;
        //BufferedReader reader = null;
        //File f = null;
        try {
            //f = new File(s);
            //imageV1 = ImageIO.read(f);
            imageV1 = ImageIO.read(ClassLoader.getSystemClassLoader().getResourceAsStream(s));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return imageV1;
    }

    /**
     * convert string to color.
     * @param string path.
     * @return Color.
     */
    public static Color colorFromString(String string) {
        String[] first = string.split("\\(");
        if (first[1].contains("RGB")) {
            String[] afterSplit =  first[2].split("\\)");
            String[] afterCama = afterSplit[0].split(",");
            int firstColor = Integer.parseInt(afterCama[0]);
            int secondColor = Integer.parseInt(afterCama[1]);
            int thirdColor = Integer.parseInt(afterCama[2]);
            return new Color(firstColor, secondColor, thirdColor);
        }
        if (first[1].contains("red")) {
            return Color.RED;
        }
        if (first[1].contains("blue")) {
            return Color.BLUE;
        }
        if (first[1].contains("green")) {
            return Color.GREEN;
        }
        if (first[1].contains("yellow")) {
            return Color.YELLOW;
        }
        if (first[1].contains("cyan")) {
            return Color.CYAN;
        }
        if (first[1].contains("orange")) {
            return Color.ORANGE;
        }
        if (first[1].contains("pink")) {
            return Color.PINK;
        }
        if (first[1].contains("gray")) {
            return Color.GRAY;
        }
        if (first[1].contains("lightGray")) {
            return Color.LIGHT_GRAY;
        }
        if (first[1].contains("black")) {
            return Color.BLACK;
        }
        if (first[1].contains("white")) {
            return Color.WHITE;
        }
        return null;
    }
}
