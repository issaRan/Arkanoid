import java.awt.Color;
import java.util.HashMap;

/**
 * @author ISSA 10/06/18.
 */
public class MakeBlock implements BlockCreator {
    private int width, height;
    private int hitPoint;
    private HashMap<Integer, ColorOrImage> fill;
    private Color stroke;

    /**
     * Sets height for block.
     *
     * @param h height of block
     */
    public void setHeight(int h) {
        this.height = h;
    }

    /**
     * Sets width for block.
     *
     * @param w width of block
     */
    public void setWidth(int w) {
        this.width = w;
    }

    /**
     * set hit point.
     * @param hitPoint1 hit point
     */
    public void setHitPoint(int hitPoint1) {
        this.hitPoint = hitPoint1;
    }

    /**
     * sets fill for block.
     *
     * @param f hasmap to the draw image or color.
     */
    public void setColorOrImage(HashMap<Integer, ColorOrImage> f) {
        this.fill = f;
    }

    /**
     * sets stroke.
     * @param s string for the collor.
     */
    public void setStroke(Color s) {
        this.stroke = s;
    }

    @Override
    public Block create(int x, int y) {
        if (this.stroke != null) {
            return new Block(x, y, this.width, this.height, this.stroke, this.fill, this.hitPoint);
        } else {
            return new Block(x, y, this.width, this.height, this.stroke, this.fill, this.hitPoint);
        }
    }
}