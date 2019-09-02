import biuoop.DrawSurface;

import java.awt.Color;

/**
 * used to create a back ground for the level in color case.
 */
public class ColorForBackground implements Sprite {
    private Color color;

    /**
     * counstructor.
     *
     * @param color a color.
     */
    public ColorForBackground(Color color) {
        this.color = color;
    }

    /**
     * @param d used to draw the function.
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.fillRectangle(0, 0, 800, 600);
    }

    @Override
    public void timePassed(double dt) {

    }
}
