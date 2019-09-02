import biuoop.DrawSurface;

import java.awt.Color;

/**
 * @author ISSA
 * 19/5/18
 * draw the background.
 */
public class Background2 implements Sprite {
    /**
     * draw the sprite to the screen.
     *
     * @param d to used to draw.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(Color.CYAN);
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(new Color(210, 202, 149));
        d.fillCircle(200, 130, 80);
        for (int i = 1; i <= 100; i++) {
            d.drawLine(200, 130, 8 * i, 280);
        }

        d.setColor(new Color(197, 194, 70));
        d.fillCircle(200, 130, 70);

        d.setColor(new Color(255, 218, 44));
        d.fillCircle(200, 130, 60);
    }
    // notify the sprite that time has passed

    /**
     * notify the sprite that time has passed.
     * @param dt delta time.
     */
    public void timePassed(double dt) {

    }
}
